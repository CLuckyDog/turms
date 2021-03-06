/*
 * Copyright (C) 2019 The Turms Project
 * https://github.com/turms-im/turms
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package im.turms.turms.service.message;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.protobuf.Int64Value;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import im.turms.common.TurmsStatusCode;
import im.turms.common.constant.MessageDeliveryStatus;
import im.turms.common.exception.TurmsBusinessException;
import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.common.model.dto.request.TurmsRequest;
import im.turms.common.util.Validator;
import im.turms.turms.builder.QueryBuilder;
import im.turms.turms.builder.UpdateBuilder;
import im.turms.turms.manager.TurmsClusterManager;
import im.turms.turms.manager.TurmsPluginManager;
import im.turms.turms.plugin.ExpiredMessageAutoDeletionNotificationHandler;
import im.turms.turms.pojo.bo.DateRange;
import im.turms.turms.pojo.domain.Message;
import im.turms.turms.pojo.domain.MessageStatus;
import im.turms.turms.property.TurmsProperties;
import im.turms.turms.service.group.GroupMemberService;
import im.turms.turms.service.user.UserService;
import im.turms.turms.util.AggregationUtil;
import im.turms.turms.util.ProtoUtil;
import lombok.Getter;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import javax.annotation.Nullable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import static im.turms.common.TurmsStatusCode.*;
import static im.turms.turms.constant.Common.*;
import static im.turms.turms.pojo.domain.MessageStatus.Fields.ID_MESSAGE_ID;
import static im.turms.turms.pojo.domain.MessageStatus.Fields.ID_RECIPIENT_ID;
import static im.turms.turms.property.business.Message.TimeType;

@Service
@Validated
public class MessageService {
    private final ReactiveMongoTemplate mongoTemplate;
    private final TurmsClusterManager turmsClusterManager;
    private final MessageStatusService messageStatusService;
    private final OutboundMessageService outboundMessageService;
    private final GroupMemberService groupMemberService;
    private final UserService userService;
    private final TurmsPluginManager turmsPluginManager;
    private final boolean pluginEnabled;
    @Getter
    private final TimeType timeType;
    private final Cache<Long, Message> sentMessageCache;

    @Autowired
    public MessageService(
            @Qualifier("messageMongoTemplate") ReactiveMongoTemplate mongoTemplate,
            TurmsProperties turmsProperties,
            TurmsClusterManager turmsClusterManager,
            MessageStatusService messageStatusService,
            GroupMemberService groupMemberService,
            UserService userService,
            OutboundMessageService outboundMessageService,
            TurmsPluginManager turmsPluginManager) {
        this.mongoTemplate = mongoTemplate;
        this.turmsClusterManager = turmsClusterManager;
        this.messageStatusService = messageStatusService;
        this.groupMemberService = groupMemberService;
        this.userService = userService;
        this.outboundMessageService = outboundMessageService;
        this.turmsPluginManager = turmsPluginManager;
        pluginEnabled = turmsClusterManager.getTurmsProperties().getPlugin().isEnabled();
        timeType = turmsProperties.getMessage().getTimeType();
        int relayedMessageCacheMaxSize = turmsProperties.getCache().getSentMessageCacheMaxSize();
        if (relayedMessageCacheMaxSize > 0 && turmsProperties.getMessage().isMessagePersistent()) {
            this.sentMessageCache = Caffeine
                    .newBuilder()
                    .maximumSize(relayedMessageCacheMaxSize)
                    .expireAfterWrite(Duration.ofSeconds(turmsProperties.getCache().getSentMessageExpireAfter()))
                    .build();
        } else {
            sentMessageCache = null;
        }
    }

    @Scheduled(cron = EXPIRED_MESSAGES_CLEANER_CRON)
    public void expiredMessagesCleaner() {
        if (turmsClusterManager.isCurrentMemberMaster()) {
            int messagesTimeToLiveHours = turmsClusterManager.getTurmsProperties()
                    .getMessage().getMessageTimeToLiveHours();
            if (messagesTimeToLiveHours != 0) {
                deleteExpiredMessagesAndStatuses(messagesTimeToLiveHours).subscribe();
            }
        }
    }

    public Mono<Boolean> isMessageSentByUser(@NotNull Long messageId, @NotNull Long senderId) {
        if (sentMessageCache != null) {
            Message message = sentMessageCache.getIfPresent(messageId);
            if (message != null) {
                return Mono.just(message.getSenderId().equals(senderId));
            }
        }
        Query query = new Query()
                .addCriteria(Criteria.where(ID).is(messageId))
                .addCriteria(Criteria.where(Message.Fields.SENDER_ID).is(senderId));
        return mongoTemplate.exists(query, Message.class);
    }

    public Mono<Boolean> isMessageSentToUser(@NotNull Long messageId, @NotNull Long recipientId) {
        if (sentMessageCache != null) {
            Message message = sentMessageCache.getIfPresent(messageId);
            if (message != null && !message.getIsGroupMessage()) {
                return Mono.just(message.getTargetId().equals(recipientId));
            }
        }
        MessageStatus.Key key = new MessageStatus.Key(messageId, recipientId);
        Query query = new Query().addCriteria(Criteria.where(ID).is(key));
        return mongoTemplate.exists(query, MessageStatus.class);
    }

    public Mono<Boolean> isMessageSentToUserOrByUser(@NotNull Long messageId, @NotNull Long userId) {
        return isMessageSentToUser(messageId, userId)
                .flatMap(isSentToUser -> {
                    if (isSentToUser) {
                        return Mono.just(true);
                    } else {
                        return isMessageSentByUser(messageId, userId);
                    }
                });
    }

    public Mono<Boolean> isMessageRecallable(@NotNull Long messageId) {
        Mono<Message> messageMono = null;
        if (sentMessageCache != null) {
            Message message = sentMessageCache.getIfPresent(messageId);
            if (message != null) {
                messageMono = Mono.just(message);
            }
        }
        if (messageMono == null) {
            Query query = new Query().addCriteria(Criteria.where(ID).is(messageId));
            query.fields().include(Message.Fields.DELIVERY_DATE);
            messageMono = mongoTemplate.findOne(query, Message.class);
        }
        return messageMono
                .map(message -> {
                    Date deliveryDate = message.getDeliveryDate();
                    if (deliveryDate != null) {
                        long elapsedTime = (deliveryDate.getTime() - System.currentTimeMillis()) / 1000;
                        return elapsedTime < turmsClusterManager.getTurmsProperties()
                                .getMessage().getAvailableRecallDurationSeconds();
                    } else {
                        return false;
                    }
                })
                .defaultIfEmpty(false);
    }

    public Flux<Message> authAndQueryCompleteMessages(
            boolean closeToDate,
            @Nullable Collection<Long> messageIds,
            @Nullable Boolean areGroupMessages,
            @Nullable Boolean areSystemMessages,
            @Nullable Long senderId,
            @Nullable Long targetId,
            @Nullable DateRange deliveryDateRange,
            @Nullable DateRange deletionDateRange,
            @Nullable MessageDeliveryStatus deliveryStatus,
            @Nullable Integer page,
            @Nullable Integer size) {
        if (deliveryStatus == MessageDeliveryStatus.READY
                || deliveryStatus == MessageDeliveryStatus.RECEIVED) {
            return queryMessages(
                    closeToDate,
                    messageIds,
                    areGroupMessages,
                    areSystemMessages,
                    senderId != null ? Set.of(senderId) : null,
                    targetId != null ? Set.of(targetId) : null,
                    deliveryDateRange,
                    deletionDateRange,
                    Set.of(deliveryStatus),
                    page,
                    size);
        } else {
            throw TurmsBusinessException.get(ILLEGAL_ARGUMENTS);
        }
    }

    public Mono<Message> queryMessage(@NotNull Long messageId) {
        Query query = new Query().addCriteria(Criteria.where(ID).is(messageId));
        return mongoTemplate.findOne(query, Message.class);
    }

    public Flux<Message> queryMessages(
            boolean closeToDate,
            @Nullable Collection<Long> messageIds,
            @Nullable Boolean areGroupMessages,
            @Nullable Boolean areSystemMessages,
            @Nullable Set<Long> senderIds,
            @Nullable Set<Long> targetIds,
            @Nullable DateRange deliveryDateRange,
            @Nullable DateRange deletionDateRange,
            @Nullable Set<MessageDeliveryStatus> deliveryStatuses,
            @Nullable Integer page,
            @Nullable Integer size) {
        QueryBuilder builder = QueryBuilder.newBuilder()
                .addIsIfNotNull(Message.Fields.IS_GROUP_MESSAGE, areGroupMessages)
                .addIsIfNotNull(Message.Fields.IS_SYSTEM_MESSAGE, areSystemMessages)
                .addInIfNotNull(Message.Fields.SENDER_ID, senderIds)
                .addInIfNotNull(Message.Fields.TARGET_ID, targetIds)
                .addBetweenIfNotNull(Message.Fields.DELIVERY_DATE, deliveryDateRange)
                .addBetweenIfNotNull(Message.Fields.DELETION_DATE, deletionDateRange);
        Sort.Direction direction = null;
        if (closeToDate) {
            direction = (deliveryDateRange != null && deliveryDateRange.getStart() != null) ? Sort.Direction.ASC : Sort.Direction.DESC;
        }
        if (deliveryStatuses != null) {
            Sort.Direction finalDirection = direction;
            return messageStatusService.queryMessagesIdsByDeliveryStatusesAndTargetIds(deliveryStatuses, areGroupMessages, targetIds)
                    .collect(Collectors.toSet())
                    .flatMapMany(ids -> {
                        if (ids.isEmpty()) {
                            return Flux.empty();
                        }
                        if (messageIds != null) {
                            ids.retainAll(messageIds);
                        }
                        builder.add(Criteria.where(ID).in(ids));
                        Query query;
                        if (finalDirection != null) {
                            query = builder.paginateIfNotNull(page, size, finalDirection, Message.Fields.DELIVERY_DATE);
                        } else {
                            query = builder.paginateIfNotNull(page, size);
                        }
                        return mongoTemplate.find(query, Message.class);
                    });
        } else {
            builder.addInIfNotNull(ID, messageIds);
            Query query;
            if (direction != null) {
                query = builder.paginateIfNotNull(page, size, direction, Message.Fields.DELIVERY_DATE);
            } else {
                query = builder.paginateIfNotNull(page, size);
            }
            return mongoTemplate.find(query, Message.class);
        }
    }

    public Mono<Message> saveMessage(
            @Nullable Long messageId,
            @NotNull Long senderId,
            @NotNull Long targetId,
            @NotNull Boolean isGroupMessage,
            @NotNull Boolean isSystemMessage,
            @Nullable String text,
            @Nullable List<byte[]> records,
            @Nullable @Min(0) Integer burnAfter,
            @Nullable @PastOrPresent Date deliveryDate,
            @Nullable Long referenceId,
            @Nullable ReactiveMongoOperations operations) {
        if (text != null && text.length() > turmsClusterManager.getTurmsProperties().getMessage().getMaxTextLimit()) {
            throw TurmsBusinessException.get(ILLEGAL_ARGUMENTS);
        }
        int maxRecordsSize = turmsClusterManager.getTurmsProperties()
                .getMessage().getMaxRecordsSizeBytes();
        if (records != null && maxRecordsSize != 0) {
            int count = 0;
            for (byte[] record : records) {
                count = record.length;
            }
            if (count > maxRecordsSize) {
                throw TurmsBusinessException.get(ILLEGAL_ARGUMENTS);
            }
        }
        if (timeType == TimeType.LOCAL_SERVER_TIME || deliveryDate == null) {
            deliveryDate = new Date();
        }
        if (!turmsClusterManager.getTurmsProperties().getMessage().isRecordsPersistent()) {
            records = null;
        }
        if (messageId == null) {
            messageId = turmsClusterManager.generateRandomId();
        }
        Message message = new Message(
                messageId,
                isGroupMessage,
                isSystemMessage,
                deliveryDate,
                null,
                text,
                senderId,
                targetId,
                records,
                burnAfter,
                referenceId);
        ReactiveMongoOperations mongoOperations = operations != null ? operations : mongoTemplate;
        return mongoOperations.insert(message);
    }

    public Mono<Boolean> saveMessageStatuses(
            @NotNull Long messageId,
            @NotNull Boolean isGroupMessage,
            @NotNull Boolean isSystemMessage,
            @NotNull Long senderId,
            @NotNull Long targetId,
            @Nullable Set<Long> auxiliaryMemberIds,
            @Nullable ReactiveMongoOperations operations) {
        ReactiveMongoOperations mongoOperations = operations != null ? operations : mongoTemplate;
        if (isGroupMessage) {
            Mono<Set<Long>> memberIdsMono;
            if (auxiliaryMemberIds != null) {
                memberIdsMono = Mono.just(auxiliaryMemberIds);
            } else {
                memberIdsMono = groupMemberService
                        .queryGroupMembersIds(targetId)
                        .collect(Collectors.toSet());
            }
            return memberIdsMono
                    .flatMap(membersIds -> {
                        if (membersIds.isEmpty()) {
                            return Mono.just(true);
                        } else {
                            List<MessageStatus> messageStatuses = new ArrayList<>(membersIds.size());
                            for (Long memberId : membersIds) {
                                messageStatuses.add(new MessageStatus(
                                        messageId,
                                        targetId,
                                        isSystemMessage,
                                        senderId,
                                        memberId,
                                        MessageDeliveryStatus.READY,
                                        null,
                                        null,
                                        null));
                            }
                            return mongoOperations.insertAll(messageStatuses).then(Mono.just(true));
                        }
                    });
        } else {
            MessageStatus messageStatus = new MessageStatus(
                    messageId,
                    null,
                    isSystemMessage,
                    senderId,
                    targetId,
                    MessageDeliveryStatus.READY,
                    null,
                    null,
                    null);
            return mongoOperations.save(messageStatus).thenReturn(true);
        }
    }

    public Mono<Message> saveMessageAndMessagesStatus(
            @Nullable Long messageId,
            @NotNull Long senderId,
            @NotNull Long targetId,
            @NotNull Boolean isGroupMessage,
            @NotNull Boolean isSystemMessage,
            @Nullable String text,
            @Nullable List<byte[]> records,
            @Nullable @Min(0) Integer burnAfter,
            @Nullable @PastOrPresent Date deliveryDate,
            @Nullable Long referenceId,
            @Nullable Set<Long> auxiliaryMemberIds) {
        Validator.throwIfAllFalsy(text, records);
        if (timeType == TimeType.LOCAL_SERVER_TIME || deliveryDate == null) {
            deliveryDate = new Date();
        }
        if (messageId == null) {
            messageId = turmsClusterManager.generateRandomId();
        }
        Date finalDeliveryDate = deliveryDate;
        Long finalMessageId = messageId;
        return mongoTemplate.inTransaction()
                .execute(operations -> saveMessage(
                        finalMessageId,
                        senderId,
                        targetId,
                        isGroupMessage,
                        isSystemMessage,
                        text,
                        records,
                        burnAfter,
                        finalDeliveryDate,
                        referenceId,
                        operations)
                        .zipWith(saveMessageStatuses(
                                finalMessageId,
                                isSystemMessage,
                                isGroupMessage,
                                senderId,
                                targetId,
                                auxiliaryMemberIds,
                                operations))
                        .map(Tuple2::getT1))
                .retryWhen(TRANSACTION_RETRY)
                .singleOrEmpty();
    }

    public Flux<Long> queryExpiredMessagesIds(@NotNull Integer timeToLiveHours) {
        Date beforeDate = Date.from(Instant.now().minus(timeToLiveHours, ChronoUnit.HOURS));
        Query query = new Query()
                .addCriteria(Criteria.where(Message.Fields.DELIVERY_DATE).lt(beforeDate));
        query.fields().include(ID);
        return mongoTemplate.find(query, Message.class)
                .map(Message::getId);
    }

    public Mono<Boolean> deleteExpiredMessagesAndStatuses(@NotNull Integer timeToLiveHours) {
        return queryExpiredMessagesIds(timeToLiveHours)
                .collectList()
                .flatMap(expiredMessagesIds -> {
                    if (expiredMessagesIds.isEmpty()) {
                        return Mono.just(true);
                    } else {
                        Query messagesQuery = new Query().addCriteria(Criteria.where(ID).in(expiredMessagesIds));
                        Query messagesStatusesQuery = new Query().addCriteria(Criteria.where(ID_MESSAGE_ID).in(expiredMessagesIds));
                        Mono<Boolean> allowedMono = Mono.just(true);
                        if (pluginEnabled) {
                            allowedMono = mongoTemplate.find(messagesQuery, Message.class)
                                    .collectList()
                                    .flatMap(messages -> {
                                        Mono<Boolean> mono = Mono.just(true);
                                        for (ExpiredMessageAutoDeletionNotificationHandler handler : turmsPluginManager.getExpiredMessageAutoDeletionNotificationHandlerList()) {
                                            mono = mono.defaultIfEmpty(true)
                                                    .flatMap(allowed -> {
                                                        if (allowed) {
                                                            return handler.allowDeleting(messages);
                                                        } else {
                                                            return Mono.just(false);
                                                        }
                                                    });
                                        }
                                        return mono;
                                    });
                        }
                        return allowedMono.flatMap(allowed -> {
                            if (allowed) {
                                return mongoTemplate.inTransaction()
                                        .execute(operations -> operations.remove(messagesQuery, Message.class)
                                                .then(operations.remove(messagesStatusesQuery, MessageStatus.class)
                                                        .thenReturn(true)))
                                        .retryWhen(TRANSACTION_RETRY)
                                        .singleOrEmpty();
                            } else {
                                return Mono.just(false);
                            }
                        });
                    }
                });
    }

    public Mono<Boolean> deleteMessages(
            @Nullable Set<Long> messageIds,
            boolean deleteMessageStatus,
            @Nullable Boolean shouldDeleteLogically) {
        Query queryMessage = QueryBuilder
                .newBuilder()
                .addInIfNotNull(ID, messageIds)
                .buildQuery();
        Query queryMessageStatus = QueryBuilder
                .newBuilder()
                .addInIfNotNull(ID_MESSAGE_ID, messageIds)
                .buildQuery();
        if (shouldDeleteLogically == null) {
            shouldDeleteLogically = turmsClusterManager.getTurmsProperties()
                    .getMessage().isDeleteMessageLogicallyByDefault();
        }
        if (shouldDeleteLogically) {
            Update update = new Update().set(Message.Fields.DELETION_DATE, new Date());
            if (deleteMessageStatus) {
                return mongoTemplate.inTransaction()
                        .execute(operations -> operations.updateMulti(queryMessage, update, Message.class)
                                .then(operations.remove(queryMessageStatus, MessageStatus.class))
                                .thenReturn(true))
                        .retryWhen(TRANSACTION_RETRY)
                        .singleOrEmpty();
            } else {
                return mongoTemplate.updateMulti(queryMessage, update, Message.class)
                        .map(UpdateResult::wasAcknowledged);
            }
        } else {
            if (deleteMessageStatus) {
                return mongoTemplate.inTransaction()
                        .execute(operations -> operations.remove(queryMessage, Message.class)
                                .then(operations.remove(queryMessageStatus, MessageStatus.class))
                                .thenReturn(true))
                        .retryWhen(TRANSACTION_RETRY)
                        .singleOrEmpty();
            }
            return mongoTemplate.remove(queryMessage, Message.class)
                    .map(DeleteResult::wasAcknowledged);
        }
    }

    public Mono<Boolean> updateMessage(
            @NotEmpty Set<Long> messageIds,
            @Nullable Boolean isSystemMessage,
            @Nullable String text,
            @Nullable List<byte[]> records,
            @Nullable @Min(0) Integer burnAfter,
            @Nullable ReactiveMongoOperations operations) {
        if (Validator.areAllNull(isSystemMessage, text, records, burnAfter)) {
            return Mono.just(true);
        }
        Query query = new Query().addCriteria(Criteria.where(ID).in(messageIds));
        Update update = UpdateBuilder.newBuilder()
                .setIfNotNull(Message.Fields.TEXT, text)
                .setIfNotNull(Message.Fields.RECORDS, records)
                .build();
        ReactiveMongoOperations mongoOperations = operations != null ? operations : mongoTemplate;
        return mongoOperations.updateMulti(query, update, Message.class)
                .map(UpdateResult::wasAcknowledged);
    }

    public Mono<Boolean> updateMessage(
            @NotNull Long messageId,
            @Nullable Boolean isSystemMessage,
            @Nullable String text,
            @Nullable List<byte[]> records,
            @Nullable @Min(0) Integer burnAfter,
            @Nullable ReactiveMongoOperations operations) {
        return updateMessage(Collections.singleton(messageId), isSystemMessage, text,
                records, burnAfter, operations);
    }

    public Mono<Long> countMessages(
            @Nullable Set<Long> messageIds,
            @Nullable Boolean areGroupMessages,
            @Nullable Boolean areSystemMessages,
            @Nullable Set<Long> senderIds,
            @Nullable Set<Long> targetIds,
            @Nullable DateRange deliveryDateRange,
            @Nullable DateRange deletionDateRange,
            @Nullable Set<MessageDeliveryStatus> deliveryStatuses) {
        QueryBuilder builder = QueryBuilder.newBuilder()
                .addIsIfNotNull(Message.Fields.IS_GROUP_MESSAGE, areGroupMessages)
                .addIsIfNotNull(Message.Fields.IS_SYSTEM_MESSAGE, areSystemMessages)
                .addIsIfNotNull(Message.Fields.SENDER_ID, senderIds)
                .addIsIfNotNull(Message.Fields.TARGET_ID, targetIds)
                .addBetweenIfNotNull(Message.Fields.DELIVERY_DATE, deliveryDateRange)
                .addBetweenIfNotNull(Message.Fields.DELETION_DATE, deletionDateRange);
        if (deliveryStatuses != null && !deliveryStatuses.isEmpty()) {
            return messageStatusService.queryMessagesIdsByDeliveryStatusesAndTargetIds(deliveryStatuses, areGroupMessages, targetIds)
                    .collect(Collectors.toSet())
                    .flatMap(ids -> {
                        if (ids.isEmpty()) {
                            return Mono.just(0L);
                        }
                        if (messageIds != null) {
                            ids.retainAll(messageIds);
                        }
                        Query query = builder.add(Criteria.where(ID).in(ids)).buildQuery();
                        return mongoTemplate.count(query, Message.class);
                    });
        } else {
            Query query = builder.addInIfNotNull(ID, messageIds).buildQuery();
            return mongoTemplate.count(query, Message.class);
        }
    }

    public Mono<Long> countUsersWhoSentMessage(
            @Nullable DateRange dateRange,
            @Nullable Boolean areGroupMessages,
            @Nullable Boolean areSystemMessages) {
        Criteria criteria = QueryBuilder.newBuilder()
                .addBetweenIfNotNull(Message.Fields.DELIVERY_DATE, dateRange)
                .addIsIfNotNull(Message.Fields.IS_GROUP_MESSAGE, areGroupMessages)
                .addIsIfNotNull(Message.Fields.IS_SYSTEM_MESSAGE, areSystemMessages)
                .buildCriteria();
        return AggregationUtil.countDistinct(
                mongoTemplate,
                criteria,
                Message.Fields.SENDER_ID,
                Message.class);
    }

    public Mono<Long> countGroupsThatSentMessages(
            @Nullable DateRange dateRange) {
        Criteria criteria = QueryBuilder.newBuilder()
                .addBetweenIfNotNull(Message.Fields.DELIVERY_DATE, dateRange)
                .add(Criteria.where(Message.Fields.IS_GROUP_MESSAGE).is(true))
                .buildCriteria();
        return AggregationUtil.countDistinct(
                mongoTemplate,
                criteria,
                Message.Fields.TARGET_ID,
                Message.class);
    }

    public Mono<Long> countUsersWhoAcknowledgedMessage(
            @Nullable DateRange dateRange,
            @Nullable Boolean areGroupMessage) {
        Criteria criteria = QueryBuilder.newBuilder()
                .addBetweenIfNotNull(MessageStatus.Fields.RECEPTION_DATE, dateRange)
                .buildCriteria();
        if (areGroupMessage != null) {
            if (areGroupMessage) {
                criteria.and(MessageStatus.Fields.GROUP_ID).ne(null);
            } else
                criteria.and(MessageStatus.Fields.GROUP_ID).is(null);
        }

        return AggregationUtil.countDistinct(
                mongoTemplate,
                criteria,
                ID_RECIPIENT_ID,
                MessageStatus.class);
    }

    public Mono<Long> countSentMessages(
            @Nullable DateRange dateRange,
            @Nullable Boolean areGroupMessages,
            @Nullable Boolean areSystemMessages) {
        Query query = QueryBuilder.newBuilder()
                .addBetweenIfNotNull(Message.Fields.DELIVERY_DATE, dateRange)
                .addIsIfNotNull(Message.Fields.IS_GROUP_MESSAGE, areGroupMessages)
                .addIsIfNotNull(Message.Fields.IS_SYSTEM_MESSAGE, areSystemMessages)
                .buildQuery();
        return mongoTemplate.count(query, Message.class);
    }

    public Mono<Long> countSentMessagesOnAverage(
            @Nullable DateRange dateRange,
            @Nullable Boolean areGroupMessages,
            @Nullable Boolean areSystemMessages) {
        return countSentMessages(dateRange, areGroupMessages, areSystemMessages)
                .flatMap(totalDeliveredMessages -> {
                    if (totalDeliveredMessages == 0) {
                        return Mono.just(0L);
                    } else {
                        return countUsersWhoSentMessage(dateRange, areGroupMessages, areSystemMessages)
                                .map(totalUsers -> {
                                    if (totalUsers == 0) {
                                        return Long.MAX_VALUE;
                                    } else {
                                        return totalDeliveredMessages / totalUsers;
                                    }
                                });
                    }
                });
    }

    public Mono<Long> countAcknowledgedMessages(
            @Nullable DateRange dateRange,
            @Nullable Boolean areGroupMessages,
            @Nullable Boolean areSystemMessages) {
        Query query = QueryBuilder.newBuilder()
                .addBetweenIfNotNull(MessageStatus.Fields.RECEPTION_DATE, dateRange)
                .addIsIfNotNull(MessageStatus.Fields.IS_SYSTEM_MESSAGE, areSystemMessages)
                .buildQuery();
        if (areGroupMessages != null) {
            if (areGroupMessages) {
                query.addCriteria(Criteria.where(MessageStatus.Fields.GROUP_ID).ne(null));
            } else {
                query.addCriteria(Criteria.where(MessageStatus.Fields.GROUP_ID).is(null));
            }
        }

        return mongoTemplate.count(query, MessageStatus.class);
    }

    public Mono<Long> countAcknowledgedMessagesOnAverage(
            @Nullable DateRange dateRange,
            @Nullable Boolean areGroupMessages,
            @Nullable Boolean areSystemMessages) {
        return countAcknowledgedMessages(dateRange, areGroupMessages, areSystemMessages)
                .flatMap(totalAcknowledgedMessages -> {
                    if (totalAcknowledgedMessages == 0) {
                        return Mono.just(0L);
                    } else {
                        return countUsersWhoAcknowledgedMessage(dateRange, areGroupMessages)
                                .map(totalUsers -> {
                                    if (totalUsers == 0) {
                                        return Long.MAX_VALUE;
                                    } else {
                                        return totalAcknowledgedMessages / totalUsers;
                                    }
                                });
                    }
                });
    }

    public Mono<Boolean> authAndUpdateMessageAndMessageStatus(
            @NotNull Long requesterId,
            @NotNull Long messageId,
            @NotNull Long recipientId,
            @Nullable String text,
            @Nullable List<byte[]> records,
            @Nullable @PastOrPresent Date recallDate,
            @Nullable @PastOrPresent Date readDate) {
        boolean updateMessageContent = text != null || (records != null && !records.isEmpty());
        if (updateMessageContent || recallDate != null) {
            if (recallDate != null && !turmsClusterManager.getTurmsProperties()
                    .getMessage().isAllowRecallingMessage()) {
                throw TurmsBusinessException.get(DISABLED_FUNCTION);
            }
            if (updateMessageContent && !turmsClusterManager.getTurmsProperties()
                    .getMessage().isAllowEditingMessageBySender()) {
                throw TurmsBusinessException.get(DISABLED_FUNCTION);
            }
            return isMessageSentByUser(messageId, requesterId)
                    .flatMap(isSentByUser -> {
                        if (isSentByUser == null || !isSentByUser) {
                            return Mono.error(TurmsBusinessException.get(UNAUTHORIZED));
                        } else if (recallDate != null) {
                            return isMessageRecallable(messageId)
                                    .flatMap(recallable -> {
                                        if (recallable == null || !recallable) {
                                            return Mono.error(TurmsBusinessException.get(EXPIRED_RESOURCE));
                                        }
                                        return updateMessageAndMessageStatus(messageId, recipientId, text, records, recallDate, readDate);
                                    });
                        } else {
                            return updateMessageAndMessageStatus(messageId, recipientId, text, records, null, readDate);
                        }
                    });
        } else {
            throw TurmsBusinessException.get(ILLEGAL_ARGUMENTS);
        }
    }

    public Mono<Boolean> updateMessageAndMessageStatus(
            @NotNull Long messageId,
            @NotNull Long recipientId,
            @Nullable String text,
            @Nullable List<byte[]> records,
            @Nullable @PastOrPresent Date recallDate,
            @Nullable @PastOrPresent Date readDate) {
        boolean readyUpdateMessage = text != null || (records != null && !records.isEmpty());
        boolean readyUpdateMessageStatus = recallDate != null || readDate != null;
        if (readyUpdateMessage || readyUpdateMessageStatus) {
            return mongoTemplate.inTransaction()
                    .execute(operations -> {
                        List<Mono<Boolean>> updateMonos = new ArrayList<>(2);
                        if (readyUpdateMessage) {
                            updateMonos.add(updateMessage(messageId, null, text, records, null, operations));
                        }
                        if (readyUpdateMessageStatus) {
                            updateMonos.add(messageStatusService.updateMessageStatus(messageId, recipientId, recallDate, readDate, null, operations));
                        }
                        return Mono.when(updateMonos).thenReturn(true);
                    })
                    .retryWhen(TRANSACTION_RETRY)
                    .singleOrEmpty();
        } else {
            return Mono.just(true);
        }
    }

    public Flux<Long> queryMessageRecipients(@NotNull Long messageId) {
        Query query = new Query().addCriteria(Criteria.where(ID_MESSAGE_ID).is(messageId));
        query.fields().include(ID_RECIPIENT_ID);
        return mongoTemplate.find(query, MessageStatus.class)
                .map(status -> status.getKey().getRecipientId());
    }

    public Mono<Long> queryMessageSenderId(@NotNull Long messageId) {
        Query query = new Query().addCriteria(Criteria.where(ID).is(messageId));
        query.fields().include(Message.Fields.SENDER_ID);
        return mongoTemplate.findOne(query, Message.class)
                .map(Message::getSenderId);
    }

    public Flux<Message> queryChatTypes(@NotEmpty Set<Long> messageIds) {
        Query query = new Query().addCriteria(Criteria.where(ID).in(messageIds));
        query.fields().include(Message.Fields.CHAT_TYPE);
        return mongoTemplate.find(query, Message.class);
    }

    // message - recipientsIds
    public Mono<Pair<Message, Set<Long>>> authAndSaveMessage(
            @Nullable Long messageId,
            @NotNull Long senderId,
            @NotNull Long targetId,
            @NotNull Boolean isGroupMessage,
            @NotNull Boolean isSystemMessage,
            @Nullable String text,
            @Nullable List<byte[]> records,
            @Nullable @Min(0) Integer burnAfter,
            @Nullable @PastOrPresent Date deliveryDate,
            @Nullable Long referenceId) {
        return userService.isAllowedToSendMessageToTarget(isGroupMessage, isSystemMessage, senderId, targetId)
                .flatMap(allowed -> {
                    if (allowed == null || !allowed) {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED));
                    }
                    Mono<Set<Long>> recipientIdsMono;
                    if (isGroupMessage) {
                        recipientIdsMono = groupMemberService.getMembersIdsByGroupId(targetId)
                                .collect(Collectors.toSet());
                    } else {
                        recipientIdsMono = Mono.just(Collections.singleton(targetId));
                    }
                    return recipientIdsMono.flatMap(recipientsIds -> {
                        if (!turmsClusterManager.getTurmsProperties().getMessage().isMessagePersistent()) {
                            if (recipientsIds.isEmpty()) {
                                return Mono.empty();
                            } else {
                                return Mono.just(Pair.of(null, recipientsIds));
                            }
                        }
                        Mono<Message> saveMono;
                        if (turmsClusterManager.getTurmsProperties().getMessage().isMessageStatusPersistent()) {
                            saveMono = saveMessageAndMessagesStatus(messageId, senderId, targetId, isGroupMessage,
                                    isSystemMessage, text, records, burnAfter, deliveryDate, referenceId, recipientsIds);
                        } else {
                            saveMono = saveMessage(null, senderId, targetId, isGroupMessage,
                                    isSystemMessage, text, records, burnAfter, deliveryDate,
                                    referenceId, null);
                        }
                        return saveMono.map(message -> {
                            if (message.getId() != null && sentMessageCache != null) {
                                addSentMessage2Cache(message);
                            }
                            return Pair.of(message, recipientsIds);
                        });
                    });
                });
    }

    /**
     * clone a new message rather than using its ID as a reference
     */
    public Mono<Pair<Message, Set<Long>>> authAndCloneAndSaveMessage(
            @NotNull Long requesterId,
            @NotNull Long referenceId,
            @NotNull Boolean isGroupMessage,
            @NotNull Boolean isSystemMessage,
            @NotNull Long targetId) {
        return queryMessage(referenceId)
                .flatMap(message -> authAndSaveMessage(
                        turmsClusterManager.generateRandomId(),
                        requesterId,
                        targetId,
                        isGroupMessage,
                        isSystemMessage,
                        message.getText(),
                        message.getRecords(),
                        message.getBurnAfter(),
                        message.getDeliveryDate(),
                        referenceId));
    }

    public Mono<Boolean> sendMessage(
            boolean shouldSend,
            @Nullable Long messageId,
            @NotNull Boolean isGroupMessage,
            @NotNull Boolean isSystemMessage,
            @Nullable String text,
            @Nullable List<byte[]> records,
            @Nullable Long senderId,
            @NotNull Long targetId,
            @Nullable @Min(0) Integer burnAfter,
            @Nullable Long referenceId) {
        Validator.throwIfAllFalsy(text, records);
        if (senderId == null) {
            if (isSystemMessage) {
                senderId = ADMIN_REQUESTER_ID;
            } else {
                throw TurmsBusinessException.get(ILLEGAL_ARGUMENTS);
            }
        }
        Date deliveryDate = new Date();
        Message message = new Message(messageId, isGroupMessage, isSystemMessage, deliveryDate, null,
                text, senderId, targetId, records, burnAfter, referenceId);
        if (shouldSend) {
            return authAndSaveMessage(
                    messageId,
                    senderId,
                    targetId,
                    isGroupMessage,
                    isSystemMessage,
                    text,
                    records,
                    burnAfter,
                    deliveryDate,
                    null)
                    .flatMap(pair -> {
                        TurmsRequest request = TurmsRequest
                                .newBuilder()
                                .setCreateMessageRequest(ProtoUtil.message2createMessageRequest(message))
                                .build();
                        byte[] responseData = TurmsNotification
                                .newBuilder()
                                .setRelayedRequest(request)
                                .setRequestId(Int64Value.newBuilder().setValue(0).build())
                                .build()
                                .toByteArray();
                        Set<Long> recipientIds = pair.getValue();
                        if (turmsClusterManager.getTurmsProperties().getMessage().isSendMessageToOtherSenderOnlineDevices()) {
                            recipientIds.add(message.getId());
                        }
                        return outboundMessageService.relayClientMessageToClients(
                                responseData,
                                recipientIds,
                                null,
                                pair.getLeft().getSenderId(),
                                true);
                    });
        } else {
            Mono<Message> messageMono;
            if (turmsClusterManager.getTurmsProperties().getMessage().isMessageStatusPersistent()) {
                messageMono = saveMessageAndMessagesStatus(messageId, senderId, targetId, isGroupMessage,
                        isSystemMessage, text, records, burnAfter, deliveryDate, null, null);
            } else {
                messageMono = saveMessage(null, senderId, targetId, isGroupMessage,
                        isSystemMessage, text, records, burnAfter, deliveryDate, referenceId, null);
            }
            return messageMono.doOnSuccess(msg -> {
                if (msg.getId() != null && sentMessageCache != null) {
                    addSentMessage2Cache(msg);
                }
            }).thenReturn(true);
        }
    }

    public Mono<Set<Long>> filterPrivateMessages(Set<Long> messagesIds) {
        if (messagesIds.isEmpty()) {
            return Mono.just(Collections.emptySet());
        } else {
            Set<Long> privateMessageIds = null;
            Set<Long> uncachedMessageIds = null;
            for (Long messagesId : messagesIds) {
                Message message = sentMessageCache.getIfPresent(messagesId);
                if (message != null) {
                    if (!message.getIsGroupMessage()) {
                        if (privateMessageIds == null) {
                            privateMessageIds = new HashSet<>();
                        }
                        privateMessageIds.add(messagesId);
                    }
                } else {
                    if (uncachedMessageIds == null) {
                        uncachedMessageIds = new HashSet<>();
                    }
                    uncachedMessageIds.add(messagesId);
                }
            }
            if (uncachedMessageIds == null) {
                return Mono.just(privateMessageIds != null ? privateMessageIds : Collections.emptySet());
            } else {
                Set<Long> finalPrivateMessageIds = privateMessageIds;
                return queryChatTypes(uncachedMessageIds)
                        .collectList()
                        .map(messages -> {
                            Set<Long> ids = null;
                            for (Message message : messages) {
                                if (!message.getIsGroupMessage()) {
                                    if (ids == null) {
                                        ids = new HashSet<>();
                                    }
                                    ids.add(message.getId());
                                }
                            }
                            if (ids == null) {
                                return finalPrivateMessageIds == null ? Collections.emptySet() : finalPrivateMessageIds;
                            } else {
                                if (finalPrivateMessageIds != null) {
                                    ids.addAll(finalPrivateMessageIds);
                                }
                                return ids;
                            }
                        });
            }
        }
    }

    private void addSentMessage2Cache(@NotNull Message message) {
        sentMessageCache.put(message.getId(), new Message(
                message.getId(),
                message.getIsGroupMessage(),
                message.getIsSystemMessage(),
                message.getDeliveryDate(),
                null,
                null,
                message.getSenderId(),
                message.getTargetId(),
                null,
                null,
                null));
    }
}