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

package im.turms.turms.pojo.bo;

import im.turms.common.constant.DeviceType;
import im.turms.common.model.dto.request.TurmsRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;

@Data
@AllArgsConstructor
public final class TurmsRequestWrapper {
    private TurmsRequest turmsRequest;
    private final Long userId;
    private final DeviceType deviceType;
    private final WebSocketMessage message;
    private final WebSocketSession session;
}
