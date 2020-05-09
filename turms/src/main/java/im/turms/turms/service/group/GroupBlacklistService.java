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

package im.turms.turms.service.group;

import com.google.protobuf.Int64Value;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import im.turms.common.TurmsStatusCode;
import im.turms.common.exception.TurmsBusinessException;
import im.turms.common.model.bo.common.Int64ValuesWithVersion;
import im.turms.common.model.bo.user.UserInfo;
import im.turms.common.model.bo.user.UsersInfosWithVersion;
import im.turms.common.util.Validator;
import im.turms.turms.builder.QueryBuilder;
import im.turms.turms.builder.UpdateBuilder;
import im.turms.turms.pojo.bo.DateRange;
import im.turms.turms.pojo.domain.GroupBlacklistedUser;
import im.turms.turms.pojo.domain.User;
import im.turms.turms.service.user.UserService;
import im.turms.turms.util.MapUtil;
import im.turms.turms.util.ProtoUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import static im.turms.turms.constant.Common.*;

@Service
@Validated
public class GroupBlacklistService {
    private final GroupMemberService groupMemberService;
    private final GroupVersionService groupVersionService;
    private final UserService userService;
    private final ReactiveMongoTemplate mongoTemplate;

    public GroupBlacklistService(
            GroupMemberService groupMemberService,
            @Qualifier("groupMongoTemplate") ReactiveMongoTemplate mongoTemplate,
            GroupVersionService groupVersionService,
            UserService userService) {
        this.groupMemberService = groupMemberService;
        this.mongoTemplate = mongoTemplate;
        this.groupVersionService = groupVersionService;
        this.userService = userService;
    }

    public Mono<Boolean> blacklistUser(
            @NotNull Long requesterId,
            @NotNull Long groupId,
            @NotNull Long blacklistedUserId,
            @Nullable ReactiveMongoOperations operations) {
        return groupMemberService.isOwnerOrManager(requesterId, groupId)
                .flatMap(authenticated -> {
                    if (authenticated != null && authenticated) {
                        return groupMemberService.isGroupMember(groupId, blacklistedUserId);
                    } else {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED));
                    }
                })
                .flatMap(isGroupMember -> {
                    GroupBlacklistedUser blacklistedUser = new GroupBlacklistedUser(
                            groupId, blacklistedUserId, new Date(), requesterId);
                    if (isGroupMember != null && isGroupMember) {
                        Mono<Boolean> updateVersion = groupVersionService.updateVersion(
                                groupId,
                                false,
                                true,
                                true,
                                false,
                                false);
                        if (operations != null) {
                            return groupMemberService.deleteGroupMembers(groupId, Set.of(blacklistedUserId), operations, false)
                                    .then(operations.insert(blacklistedUser))
                                    .then(updateVersion)
                                    .thenReturn(true);
                        } else {
                            return mongoTemplate
                                    .inTransaction()
                                    .execute(newOperations -> groupMemberService.deleteGroupMembers(groupId, Set.of(blacklistedUserId), newOperations, false)
                                            .then(newOperations.insert(blacklistedUser))
                                            .then(updateVersion)
                                            .thenReturn(true))
                                    .retryWhen(TRANSACTION_RETRY)
                                    .singleOrEmpty();
                        }
                    } else {
                        Mono<Boolean> updateVersion = groupVersionService.updateBlacklistVersion(groupId);
                        ReactiveMongoOperations mongoOperations = operations != null ? operations : mongoTemplate;
                        return mongoOperations.insert(blacklistedUser)
                                .then(updateVersion)
                                .thenReturn(true);
                    }
                });
    }

    public Mono<Boolean> unblacklistUser(
            @NotNull Long requesterId,
            @NotNull Long groupId,
            @NotNull Long unblacklistedUserId,
            @Nullable ReactiveMongoOperations operations,
            boolean updateBlacklistVersion) {
        return groupMemberService
                .isOwnerOrManager(requesterId, groupId)
                .flatMap(authenticated -> {
                    if (authenticated != null && authenticated) {
                        ReactiveMongoOperations mongoOperations = operations != null ? operations : mongoTemplate;
                        Query query = new Query()
                                .addCriteria(Criteria.where(ID_GROUP_ID).is(groupId))
                                .addCriteria(Criteria.where(ID_USER_ID).is(unblacklistedUserId));
                        return mongoOperations.remove(query, GroupBlacklistedUser.class)
                                .flatMap(result -> {
                                    if (result.wasAcknowledged()) {
                                        if (updateBlacklistVersion) {
                                            return groupVersionService.updateBlacklistVersion(groupId)
                                                    .thenReturn(true);
                                        } else {
                                            return Mono.just(true);
                                        }
                                    } else {
                                        return Mono.just(false);
                                    }
                                });
                    } else {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED));
                    }
                });
    }

    public Flux<Long> queryGroupBlacklistedUsersIds(@NotNull Long groupId) {
        Query query = new Query().addCriteria(Criteria.where(ID_GROUP_ID).is(groupId));
        query.fields().include(ID_USER_ID);
        return mongoTemplate
                .find(query, GroupBlacklistedUser.class)
                .map(groupBlacklistedUser -> groupBlacklistedUser.getKey().getUserId());
    }

    public Flux<GroupBlacklistedUser> queryBlacklistedUsers(
            @Nullable Set<Long> groupIds,
            @Nullable Set<Long> userIds,
            @Nullable DateRange blockDateRange,
            @Nullable Set<Long> requesterIds,
            @Nullable Integer page,
            @Nullable Integer size) {
        Query query = QueryBuilder
                .newBuilder()
                .addInIfNotNull(ID_USER_ID, userIds)
                .addInIfNotNull(ID_GROUP_ID, groupIds)
                .addInIfNotNull(GroupBlacklistedUser.Fields.requesterId, requesterIds)
                .addBetweenIfNotNull(GroupBlacklistedUser.Fields.blockDate, blockDateRange)
                .paginateIfNotNull(page, size);
        return mongoTemplate.find(query, GroupBlacklistedUser.class);
    }

    public Mono<Long> countBlacklistedUsers(
            @Nullable Set<Long> groupIds,
            @Nullable Set<Long> userIds,
            @Nullable DateRange blockDateRange,
            @Nullable Set<Long> requesterIds) {
        Query query = QueryBuilder
                .newBuilder()
                .addInIfNotNull(ID_USER_ID, userIds)
                .addInIfNotNull(ID_GROUP_ID, groupIds)
                .addInIfNotNull(GroupBlacklistedUser.Fields.requesterId, requesterIds)
                .addBetweenIfNotNull(GroupBlacklistedUser.Fields.blockDate, blockDateRange)
                .buildQuery();
        return mongoTemplate.count(query, GroupBlacklistedUser.class);
    }

    public Mono<Int64ValuesWithVersion> queryGroupBlacklistedUsersIdsWithVersion(
            @NotNull Long groupId,
            @Nullable Date lastUpdatedDate) {
        return groupVersionService
                .queryBlacklistVersion(groupId)
                .flatMap(version -> {
                    if (lastUpdatedDate == null || lastUpdatedDate.before(version)) {
                        return queryGroupBlacklistedUsersIds(groupId)
                                .collect(Collectors.toSet())
                                .map(ids -> {
                                    if (ids.isEmpty()) {
                                        throw TurmsBusinessException.get(TurmsStatusCode.NO_CONTENT);
                                    }
                                    return Int64ValuesWithVersion
                                            .newBuilder()
                                            .setLastUpdatedDate(Int64Value.newBuilder().setValue(version.getTime()).build())
                                            .addAllValues(ids)
                                            .build();
                                });
                    } else {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE));
                    }
                })
                .switchIfEmpty(Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE)));
    }

    public Mono<UsersInfosWithVersion> queryGroupBlacklistedUsersInfosWithVersion(
            @NotNull Long groupId,
            @Nullable Date lastUpdatedDate) {
        return groupVersionService
                .queryBlacklistVersion(groupId)
                .flatMap(version -> {
                    if (lastUpdatedDate == null || lastUpdatedDate.before(version)) {
                        return queryGroupBlacklistedUsersIds(groupId)
                                .collect(Collectors.toSet())
                                .flatMapMany(ids -> {
                                    if (ids.isEmpty()) {
                                        throw TurmsBusinessException.get(TurmsStatusCode.NO_CONTENT);
                                    } else {
                                        return userService.queryUsersProfiles(ids, false);
                                    }
                                })
                                .collect(Collectors.toSet())
                                .map(users -> {
                                    if (users.isEmpty()) {
                                        throw TurmsBusinessException.get(TurmsStatusCode.NO_CONTENT);
                                    }
                                    UsersInfosWithVersion.Builder builder = UsersInfosWithVersion.newBuilder();
                                    builder.setLastUpdatedDate(Int64Value.newBuilder().setValue(version.getTime()).build());
                                    for (User user : users) {
                                        UserInfo userInfo = ProtoUtil.userProfile2proto(user).build();
                                        builder.addUserInfos(userInfo);
                                    }
                                    return builder.build();
                                });
                    } else {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE));
                    }
                })
                .switchIfEmpty(Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE)));
    }

    public Mono<GroupBlacklistedUser> addBlacklistedUser(
            @NotNull Long groupId,
            @NotNull Long userId,
            @NotNull Long requesterId,
            @Nullable @PastOrPresent Date blockDate) {
        if (blockDate == null) {
            blockDate = new Date();
        }
        GroupBlacklistedUser user = new GroupBlacklistedUser(groupId, userId, blockDate, requesterId);
        return mongoTemplate.insert(user);
    }

    public Mono<Boolean> updateBlacklistedUsers(
            @NotNull Long groupId,
            @NotEmpty Set<Long> userIds,
            @Nullable @PastOrPresent Date blockDate,
            @Nullable Long requesterId) {
        if (Validator.areAllNull(blockDate, requesterId)) {
            return Mono.just(true);
        }
        Query query = new Query()
                .addCriteria(Criteria.where(ID_GROUP_ID).is(groupId))
                .addCriteria(Criteria.where(ID_USER_ID).in(userIds));
        Update update = UpdateBuilder
                .newBuilder()
                .setIfNotNull(GroupBlacklistedUser.Fields.blockDate, blockDate)
                .setIfNotNull(GroupBlacklistedUser.Fields.requesterId, requesterId)
                .build();
        return mongoTemplate.updateMulti(query, update, GroupBlacklistedUser.class)
                .map(UpdateResult::wasAcknowledged);
    }

    public Mono<Boolean> updateBlacklistedUsers(
            @NotEmpty Set<GroupBlacklistedUser.Key> keys,
            @Nullable @PastOrPresent Date blockDate,
            @Nullable Long requesterId) {
        if (Validator.areAllNull(blockDate, requesterId)) {
            return Mono.just(true);
        }
        return MapUtil.fluxMerge(multimap -> {
            for (GroupBlacklistedUser.Key key : keys) {
                multimap.put(key.getGroupId(), key.getUserId());
            }
            return null;
        }, (monos, key, values) -> {
            monos.add(updateBlacklistedUsers(
                    key,
                    values,
                    blockDate,
                    requesterId));
            return null;
        });
    }

    public Mono<Boolean> deleteBlacklistedUsers(@NotEmpty Set<GroupBlacklistedUser.Key> keys) {
        return MapUtil.fluxMerge(multimap -> {
            for (GroupBlacklistedUser.Key key : keys) {
                multimap.put(key.getGroupId(), key.getUserId());
            }
            return null;
        }, (monos, key, values) -> {
            monos.add(deleteBlacklistedUsers(key, values));
            return null;
        });
    }

    public Mono<Boolean> deleteBlacklistedUsers(@NotNull Long groupId, @NotEmpty Set<Long> userIds) {
        Query query = new Query()
                .addCriteria(Criteria.where(ID_GROUP_ID).is(groupId))
                .addCriteria(Criteria.where(ID_USER_ID).in(userIds));
        return mongoTemplate.remove(query, GroupBlacklistedUser.class)
                .map(DeleteResult::wasAcknowledged);
    }
}
