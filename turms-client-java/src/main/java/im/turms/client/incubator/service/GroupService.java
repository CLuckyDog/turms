package im.turms.client.incubator.service;

import im.turms.client.incubator.TurmsClient;
import im.turms.client.incubator.model.GroupWithVersion;
import im.turms.client.incubator.util.MapUtil;
import im.turms.client.incubator.util.NotificationUtil;
import im.turms.turms.common.Validator;
import im.turms.turms.constant.GroupMemberRole;
import im.turms.turms.pojo.bo.group.GroupInvitationsWithVersion;
import im.turms.turms.pojo.bo.group.GroupJoinQuestionsWithVersion;
import im.turms.turms.pojo.bo.group.GroupJoinRequestsWithVersion;
import im.turms.turms.pojo.bo.group.GroupMembersWithVersion;
import im.turms.turms.pojo.bo.user.UsersInfosWithVersion;
import im.turms.turms.pojo.request.group.*;
import im.turms.turms.pojo.request.group.blacklist.CreateGroupBlacklistedUserRequest;
import im.turms.turms.pojo.request.group.blacklist.DeleteGroupBlacklistedUserRequest;
import im.turms.turms.pojo.request.group.blacklist.QueryGroupBlacklistedUsersIdsRequest;
import im.turms.turms.pojo.request.group.blacklist.QueryGroupBlacklistedUsersInfosRequest;
import im.turms.turms.pojo.request.group.enrollment.*;
import im.turms.turms.pojo.request.group.member.CreateGroupMemberRequest;
import im.turms.turms.pojo.request.group.member.DeleteGroupMemberRequest;
import im.turms.turms.pojo.request.group.member.QueryGroupMembersRequest;
import im.turms.turms.pojo.request.group.member.UpdateGroupMemberRequest;

import javax.annotation.Nullable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class GroupService {
    private TurmsClient turmsClient;

    public GroupService(TurmsClient turmsClient) {
        this.turmsClient = turmsClient;
    }

    public CompletableFuture<Long> createGroup(
            @NotNull String name,
            String intro,
            String announcement,
            String profilePictureUrl,
            Integer minimumScore,
            Date muteEndDate,
            Integer groupTypeId) {
        Validator.throwIfAnyFalsy(name);
        return this.turmsClient.getDriver()
                .send(CreateGroupRequest.newBuilder(), MapUtil.of(
                        "name", name,
                        "intro", intro,
                        "announcement", announcement,
                        "minimumScore", minimumScore,
                        "muteEndDate", muteEndDate,
                        "profilePictureUrl", profilePictureUrl,
                        "groupTypeId", groupTypeId))
                .thenApply(NotificationUtil::getFirstIdFromIds);
    }

    public CompletableFuture<Void> deleteGroup(long groupId) {
        return turmsClient.getDriver()
                .send(DeleteGroupRequest.newBuilder(), MapUtil.of("groupId", groupId))
                .thenApply(turmsNotification -> null);
    }

    public CompletableFuture<Void> updateGroup(
            long groupId,
            String groupName,
            String intro,
            String announcement,
            String profilePictureUrl,
            Integer minimumScore,
            Long groupTypeId,
            Date muteEndDate,
            Long successorId,
            Boolean quitAfterTransfer) {
        Validator.throwIfAllFalsy(groupName, intro, announcement, profilePictureUrl, minimumScore, groupTypeId,
                muteEndDate, successorId);
        return turmsClient.getDriver()
                .send(UpdateGroupRequest.newBuilder(), MapUtil.of(
                        "groupId", groupId,
                        "groupName", groupName,
                        "intro", intro,
                        "announcement", announcement,
                        "profilePictureUrl", profilePictureUrl,
                        "muteEndDate", muteEndDate,
                        "minimumScore", minimumScore,
                        "groupTypeId", groupTypeId,
                        "successorId", successorId,
                        "quitAfterTransfer", quitAfterTransfer))
                .thenApply(turmsNotification -> null);
    }

    public CompletableFuture<Void> transferOwnership(long groupId, long successorId, Boolean quitAfterTransfer) {
        if (quitAfterTransfer == null) {
            quitAfterTransfer = false;
        }
        return this.updateGroup(groupId, null, null, null, null, null, null, null, successorId, quitAfterTransfer);
    }

    public CompletableFuture<Void> muteGroup(long groupId, @NotNull Date muteEndDate) {
        Validator.throwIfAnyFalsy(muteEndDate);
        return updateGroup(groupId, null, null, null, null, null, null, muteEndDate, null, null);
    }

    public CompletableFuture<Void> unmuteGroup(long groupId) {
        return this.muteGroup(groupId, new Date(0));
    }

    public CompletableFuture<GroupWithVersion> queryGroup(long groupId, @Nullable Date lastUpdatedDate) {
        return turmsClient.getDriver()
                .send(QueryGroupRequest.newBuilder(), MapUtil.of(
                        "groupId", groupId,
                        "lastUpdatedDate", lastUpdatedDate))
                .thenApply(GroupWithVersion::from);
    }

    public CompletableFuture<List<Long>> queryJoinedGroupsIds(@Nullable Date lastUpdatedDate) {
        return turmsClient.getDriver()
                .send(QueryJoinedGroupsIdsRequest.newBuilder(), MapUtil.of(
                        "lastUpdatedDate", lastUpdatedDate))
                .thenApply(NotificationUtil::getIds);
    }

    public CompletableFuture<GroupWithVersion> queryJoinedGroupsInfos(@Nullable Date lastUpdatedDate) {
        return turmsClient.getDriver()
                .send(QueryJoinedGroupsInfosRequest.newBuilder(), MapUtil.of(
                        "lastUpdatedDate", lastUpdatedDate))
                .thenApply(GroupWithVersion::from);
    }

    public CompletableFuture<Long> addGroupJoinQuestion(
            long groupId,
            @NotNull String question,
            @NotEmpty List<String> answers,
            int score) {
        Validator.throwIfAnyFalsy(question, answers);
        return turmsClient.getDriver()
                .send(CreateGroupJoinQuestionRequest.newBuilder(), MapUtil.of(
                        "groupId", groupId,
                        "question", question,
                        "answers", answers,
                        "score", score))
                .thenApply(NotificationUtil::getFirstIdFromIds);
    }

    public CompletableFuture<Void> deleteGroupJoinQuestion(long questionId) {
        return turmsClient.getDriver()
                .send(DeleteGroupJoinQuestionRequest.newBuilder(), MapUtil.of(
                        "questionId", questionId))
                .thenApply(notification -> null);
    }

    public CompletableFuture<Void> updateGroupJoinQuestion(
            long questionId,
            @Nullable String question,
            @Nullable List<String> answers,
            @Nullable Integer score) {
        Validator.throwIfAllFalsy(question, answers, score);
        return turmsClient.getDriver()
                .send(UpdateGroupJoinQuestionRequest.newBuilder(), MapUtil.of(
                        "questionId", questionId,
                        "question", question,
                        "answers", answers,
                        "score", score))
                .thenApply(notification -> null);
    }

    // Group Blacklist
    public CompletableFuture<Void> blacklistUser(long groupId, long userId) {
        return turmsClient.getDriver()
                .send(CreateGroupBlacklistedUserRequest.newBuilder(), MapUtil.of(
                        "blacklistedUserId", userId,
                        "groupId", groupId))
                .thenApply(notification -> null);
    }

    public CompletableFuture<Void> unblacklistUser(long groupId, long userId) {
        return turmsClient.getDriver()
                .send(DeleteGroupBlacklistedUserRequest.newBuilder(), MapUtil.of(
                        "groupId", groupId,
                        "unblacklistedUserId", userId))
                .thenApply(notification -> null);
    }

    public CompletableFuture<List<Long>> queryBlacklistedUsersIds(
            long groupId,
            @Nullable Date lastUpdatedDate) {
        return turmsClient.getDriver()
                .send(QueryGroupBlacklistedUsersIdsRequest.newBuilder(), MapUtil.of(
                        "groupId", groupId,
                        "lastUpdatedDate", lastUpdatedDate))
                .thenApply(NotificationUtil::getIds);
    }

    public CompletableFuture<UsersInfosWithVersion> queryBlacklistedUsersInfos(
            long groupId,
            @Nullable Date lastUpdatedDate) {
        return turmsClient.getDriver()
                .send(QueryGroupBlacklistedUsersInfosRequest.newBuilder(), MapUtil.of(
                        "groupId", groupId,
                        "lastUpdatedDate", lastUpdatedDate))
                .thenApply(notification -> notification.getData().getUsersInfosWithVersion());
    }

    // Group Enrollment
    public CompletableFuture<Long> createInvitation(
            long groupId,
            long inviteeId,
            @NotNull String content) {
        Validator.throwIfAnyFalsy(content);
        return turmsClient.getDriver()
                .send(CreateGroupInvitationRequest.newBuilder(), MapUtil.of(
                        "groupId", groupId,
                        "inviteeId", inviteeId,
                        "content", content))
                .thenApply(NotificationUtil::getFirstIdFromIds);
    }

    public CompletableFuture<Void> deleteInvitation(long invitationId) {
        return turmsClient.getDriver()
                .send(DeleteGroupInvitationRequest.newBuilder(), MapUtil.of(
                        "invitationId", invitationId))
                .thenApply(notification -> null);
    }

    public CompletableFuture<GroupInvitationsWithVersion> queryInvitations(long groupId, @Nullable Date lastUpdatedDate) {
        return turmsClient.getDriver()
                .send(QueryGroupInvitationsRequest.newBuilder(), MapUtil.of(
                        "groupId", groupId,
                        "lastUpdatedDate", lastUpdatedDate))
                .thenApply(notification -> notification.getData().getGroupInvitationsWithVersion());
    }

    public CompletableFuture<Long> createJoinRequest(long groupId, @NotNull String content) {
        Validator.throwIfAnyFalsy(content);
        return turmsClient.getDriver()
                .send(CreateGroupJoinRequestRequest.newBuilder(), MapUtil.of(
                        "groupId", groupId,
                        "content", content))
                .thenApply(NotificationUtil::getFirstIdFromIds);
    }

    public CompletableFuture<Void> deleteJoinRequest(long requestId) {
        return turmsClient.getDriver()
                .send(DeleteGroupJoinRequestRequest.newBuilder(), MapUtil.of(
                        "requestId", requestId))
                .thenApply(notification -> null);
    }

    public CompletableFuture<GroupJoinRequestsWithVersion> queryJoinRequests(
            long groupId, @Nullable Date lastUpdatedDate) {
        return turmsClient.getDriver()
                .send(QueryGroupJoinRequestsRequest.newBuilder(), MapUtil.of(
                        "groupId", groupId,
                        "lastUpdatedDate", lastUpdatedDate))
                .thenApply(notification -> notification.getData().getGroupJoinRequestsWithVersion());
    }

    /**
     * Note: Only the owner and managers have the right to fetch questions with answers
     */
    public CompletableFuture<GroupJoinQuestionsWithVersion> queryGroupJoinQuestionsRequest(
            long groupId,
            boolean withAnswers,
            @Nullable Date lastUpdatedDate) {
        return turmsClient.getDriver()
                .send(QueryGroupJoinQuestionsRequest.newBuilder(), MapUtil.of(
                        "groupId", groupId,
                        "withAnswers", withAnswers,
                        "lastUpdatedDate", lastUpdatedDate))
                .thenApply(notification -> notification.getData().getGroupJoinQuestionsWithVersion());
    }

    public CompletableFuture<Boolean> answerGroupQuestions(Map<Long, String> questionIdAndAnswerMap) {
        Validator.throwIfEmpty(questionIdAndAnswerMap);
        return turmsClient.getDriver()
                .send(CheckGroupJoinQuestionsAnswersRequest.newBuilder(), MapUtil.of(
                        "questionIdAndAnswer", questionIdAndAnswerMap))
                .thenApply(notification -> notification.getData().getSuccess().getValue());
    }

    // Group Member
    public CompletableFuture<Void> addGroupMember(
            long groupId,
            long userId,
            @Nullable String name,
            @Nullable GroupMemberRole role,
            @Nullable Date muteEndDate) {
        return turmsClient.getDriver()
                .send(CreateGroupMemberRequest.newBuilder(), MapUtil.of(
                        "groupId", groupId,
                        "userId", userId,
                        "name", name,
                        "role", role,
                        "muteEndDate", muteEndDate))
                .thenApply(notification -> null);

    }


    public CompletableFuture<Void> quitGroup(
            long groupId,
            @Nullable Long successorId,
            @Nullable Boolean quitAfterTransfer) {
        return turmsClient.getDriver()
                .send(DeleteGroupMemberRequest.newBuilder(), MapUtil.of(
                        "groupId", groupId,
                        "groupMemberId", turmsClient.getUserService().getUserId(),
                        "successorId", successorId,
                        "quitAfterTransfer", quitAfterTransfer))
                .thenApply(notification -> null);
    }

    public CompletableFuture<Void> removeGroupMember(long groupId, long memberId) {
        return turmsClient.getDriver()
                .send(DeleteGroupMemberRequest.newBuilder(), MapUtil.of(
                        "groupId", groupId,
                        "groupMemberId", memberId))
                .thenApply(notification -> null);
    }

    public CompletableFuture<Void> updateGroupMemberInfo(
            long groupId,
            long memberId,
            @Nullable String name,
            @Nullable GroupMemberRole role,
            @Nullable Date muteEndDate) {
        Validator.throwIfAllFalsy(name, role, muteEndDate);
        return turmsClient.getDriver()
                .send(UpdateGroupMemberRequest.newBuilder(), MapUtil.of(
                        "groupId", groupId,
                        "memberId", memberId,
                        "name", name,
                        "role", role,
                        "muteEndDate", muteEndDate))
                .thenApply(notification -> null);
    }

    public CompletableFuture<Void> muteGroupMember(
            long groupId,
            long memberId,
            @NotNull Date muteEndDate) {
        Validator.throwIfAnyFalsy(groupId, memberId, muteEndDate);
        return this.updateGroupMemberInfo(groupId, memberId, null, null, muteEndDate);
    }

    public CompletableFuture<Void> unmuteGroupMember(long groupId, long memberId) {
        return this.muteGroupMember(groupId, memberId, new Date(0));
    }

    public CompletableFuture<GroupMembersWithVersion> queryGroupMembers(
            long groupId,
            boolean withStatus,
            @Nullable Date lastUpdatedDate) {
        return turmsClient.getDriver()
                .send(QueryGroupMembersRequest.newBuilder(), MapUtil.of(
                        "groupId", groupId,
                        "lastUpdatedDate", lastUpdatedDate,
                        "withStatus", withStatus))
                .thenApply(notification -> notification.getData().getGroupMembersWithVersion());
    }

    public CompletableFuture<GroupMembersWithVersion> queryGroupMembersByMembersIds(
            long groupId,
            @NotEmpty List<Long> membersIds,
            boolean withStatus) {
        Validator.throwIfAnyFalsy(membersIds);
        return turmsClient.getDriver()
                .send(QueryGroupMembersRequest.newBuilder(), MapUtil.of(
                        "groupId", groupId,
                        "groupMembersIds", membersIds,
                        "withStatus", withStatus))
                .thenApply(notification -> notification.getData().getGroupMembersWithVersion());
    }
}