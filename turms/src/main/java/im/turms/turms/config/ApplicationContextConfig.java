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

package im.turms.turms.config;

import im.turms.turms.compiler.CompilerOptions;
import im.turms.turms.manager.TurmsClusterManager;
import im.turms.turms.service.user.onlineuser.OnlineUserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class ApplicationContextConfig {
    private final TurmsClusterManager turmsClusterManager;
    private final OnlineUserService onlineUserService;

    public ApplicationContextConfig(OnlineUserService onlineUserService, TurmsClusterManager turmsClusterManager) {
        this.onlineUserService = onlineUserService;
        this.turmsClusterManager = turmsClusterManager;
    }

    @EventListener(classes = ContextRefreshedEvent.class)
    public void handleContextRefreshedEvent() {
        if (CompilerOptions.ENV == CompilerOptions.Env.DEV) {
            log.warn("Turms is running in dev mode. Turn it off in pom.xml");
        }
    }

    @EventListener(classes = ContextClosedEvent.class)
    public void handleContextClosedEvent() {
        turmsClusterManager.shutdown();
        onlineUserService.setIrresponsibleUsersOffline(true);
    }
}
