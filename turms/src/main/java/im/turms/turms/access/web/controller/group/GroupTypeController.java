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

package im.turms.turms.access.web.controller.group;

import im.turms.turms.access.web.util.ResponseFactory;
import im.turms.turms.annotation.web.RequiredPermission;
import im.turms.turms.pojo.domain.GroupType;
import im.turms.turms.pojo.dto.*;
import im.turms.turms.service.group.GroupTypeService;
import im.turms.turms.util.PageUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Set;

import static im.turms.turms.constant.AdminPermission.*;

@RestController
@RequestMapping("/groups/types")
public class GroupTypeController {
    private final GroupTypeService groupTypeService;
    private final PageUtil pageUtil;

    public GroupTypeController(GroupTypeService groupTypeService, PageUtil pageUtil) {
        this.groupTypeService = groupTypeService;
        this.pageUtil = pageUtil;
    }

    @PostMapping
    @RequiredPermission(GROUP_TYPE_CREATE)
    public Mono<ResponseEntity<ResponseDTO<GroupType>>> addGroupType(@RequestBody AddGroupTypeDTO addGroupTypeDTO) {
        Mono<GroupType> addedGroupType = groupTypeService.addGroupType(
                addGroupTypeDTO.getName(),
                addGroupTypeDTO.getGroupSizeLimit(),
                addGroupTypeDTO.getInvitationStrategy(),
                addGroupTypeDTO.getJoinStrategy(),
                addGroupTypeDTO.getGroupInfoUpdateStrategy(),
                addGroupTypeDTO.getMemberInfoUpdateStrategy(),
                addGroupTypeDTO.getGuestSpeakable(),
                addGroupTypeDTO.getSelfInfoUpdatable(),
                addGroupTypeDTO.getEnableReadReceipt(),
                addGroupTypeDTO.getMessageEditable());
        return ResponseFactory.okIfTruthy(addedGroupType);
    }

    @GetMapping
    @RequiredPermission(GROUP_TYPE_QUERY)
    public Mono<ResponseEntity<ResponseDTO<Collection<GroupType>>>> queryGroupTypes(
            @RequestParam(required = false) Integer size) {
        size = pageUtil.getSize(size);
        Flux<GroupType> groupTypesFlux = groupTypeService.queryGroupTypes(0, size);
        return ResponseFactory.okIfTruthy(groupTypesFlux);
    }

    @GetMapping("/page")
    @RequiredPermission(GROUP_TYPE_QUERY)
    public Mono<ResponseEntity<ResponseDTO<PaginationDTO<GroupType>>>> queryGroupTypes(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(required = false) Integer size) {
        size = pageUtil.getSize(size);
        Mono<Long> count = groupTypeService.countGroupTypes();
        Flux<GroupType> groupTypesFlux = groupTypeService.queryGroupTypes(page, size);
        return ResponseFactory.page(count, groupTypesFlux);
    }

    @PutMapping
    @RequiredPermission(GROUP_TYPE_UPDATE)
    public Mono<ResponseEntity<ResponseDTO<AcknowledgedDTO>>> updateGroupType(
            @RequestParam Set<Long> ids,
            @RequestBody UpdateGroupTypeDTO updateGroupTypeDTO) {
        Mono<Boolean> updateMono = groupTypeService.updateGroupTypes(
                ids,
                updateGroupTypeDTO.getName(),
                updateGroupTypeDTO.getGroupSizeLimit(),
                updateGroupTypeDTO.getInvitationStrategy(),
                updateGroupTypeDTO.getJoinStrategy(),
                updateGroupTypeDTO.getGroupInfoUpdateStrategy(),
                updateGroupTypeDTO.getMemberInfoUpdateStrategy(),
                updateGroupTypeDTO.getGuestSpeakable(),
                updateGroupTypeDTO.getSelfInfoUpdatable(),
                updateGroupTypeDTO.getEnableReadReceipt(),
                updateGroupTypeDTO.getMessageEditable());
        return ResponseFactory.acknowledged(updateMono);
    }

    @DeleteMapping
    @RequiredPermission(GROUP_TYPE_DELETE)
    public Mono<ResponseEntity<ResponseDTO<AcknowledgedDTO>>> deleteGroupType(@RequestParam Set<Long> ids) {
        Mono<Boolean> deleteMono = groupTypeService.deleteGroupTypes(ids);
        return ResponseFactory.acknowledged(deleteMono);
    }
}
