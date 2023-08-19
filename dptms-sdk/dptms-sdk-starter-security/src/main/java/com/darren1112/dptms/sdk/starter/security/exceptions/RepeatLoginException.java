package com.darren1112.dptms.sdk.starter.security.exceptions;

import com.darren1112.dptms.sdk.starter.security.enums.SecurityErrorEnum;
import org.springframework.security.core.AuthenticationException;

/**
 * 重复登录异常
 *
 * @author darren
 * @since 2022/11/15
 */
public class RepeatLoginException extends AuthenticationException {

    public RepeatLoginException(String msg, Throwable t) {
        super(msg, t);
    }

    public RepeatLoginException(String msg) {
        super(msg);
    }

    public RepeatLoginException() {
        super(SecurityErrorEnum.REPEAT_LOGIN.getMessage());
    }
}
