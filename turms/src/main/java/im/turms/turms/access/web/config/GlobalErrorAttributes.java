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

package im.turms.turms.access.web.config;

import im.turms.common.TurmsStatusCode;
import im.turms.common.exception.TurmsBusinessException;
import im.turms.turms.pojo.dto.ResponseDTO;
import im.turms.turms.property.TurmsProperties;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.core.io.buffer.DataBufferLimitException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import javax.validation.ConstraintViolationException;
import java.util.Map;

@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {
    private static final String ATTR_STATUS = "status";
    private static final String ATTR_MESSAGE = "message";
    private static final String ATTR_ERROR = "error";
    private final boolean respondStackTraceWhenExceptionThrown;

    public GlobalErrorAttributes(TurmsProperties turmsProperties) {
        respondStackTraceWhenExceptionThrown = turmsProperties.getSecurity().isRespondStackTraceWhenExceptionThrown();
    }

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace, boolean includeMessage, boolean includeBindingErrors) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(request, respondStackTraceWhenExceptionThrown, includeMessage, includeBindingErrors);
        Throwable throwable = translate(super.getError(request), errorAttributes);
        if (throwable instanceof TurmsBusinessException) {
            TurmsStatusCode code = ((TurmsBusinessException) throwable).getCode();
            errorAttributes.put(ATTR_STATUS, code.getHttpStatusCode());
            errorAttributes.put(ResponseDTO.Fields.code, code.getBusinessCode());
            errorAttributes.put(ResponseDTO.Fields.reason, code.getReason());
        } else {
            if (isClientError(errorAttributes)) {
                errorAttributes.put(ATTR_STATUS, 400);
            }
            errorAttributes.putIfAbsent(ResponseDTO.Fields.code, TurmsStatusCode.FAILED.getBusinessCode());
            errorAttributes.putIfAbsent(ResponseDTO.Fields.reason, errorAttributes.get(ATTR_MESSAGE));
        }
        errorAttributes.remove(ATTR_ERROR);
        errorAttributes.remove(ATTR_MESSAGE);
        return errorAttributes;
    }

    private Throwable translate(Throwable throwable, Map<String, Object> errorAttributes) {
        if (throwable instanceof ConstraintViolationException
                || throwable instanceof NullPointerException) {
            return TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENTS, errorAttributes.get(ATTR_MESSAGE).toString());
        } else if (throwable instanceof DuplicateKeyException) {
            return TurmsBusinessException.get(TurmsStatusCode.DUPLICATE_KEY);
        } else if (throwable instanceof DataBufferLimitException) {
            return TurmsBusinessException.get(TurmsStatusCode.FILE_TOO_LARGE);
        } else {
            return throwable;
        }
    }

    private boolean isClientError(Map<String, Object> errorAttributes) {
        Integer status = (Integer) errorAttributes.get(ATTR_STATUS);
        if (status == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            Object messageObj = errorAttributes.get(ATTR_MESSAGE);
            if (messageObj instanceof String) {
                String message = (String) messageObj;
                return message.contains("WebFlux") || message.contains("cast");
            }
        }
        return false;
    }
}