package com.darren1112.dptms.sdk.starter.security.exceptions;

import com.darren1112.dptms.sdk.starter.security.enums.SecurityErrorEnum;
import org.springframework.security.core.AuthenticationException;

/**
 * 未登录异常
 *
 * @author darren
 * @since 2022/11/15
 */
public class NotLoginException extends AuthenticationException {

    public NotLoginException(String msg, Throwable t) {
        super(msg, t);
    }

    public NotLoginException(String msg) {
        super(msg);
    }

    public NotLoginException() {
        super(SecurityErrorEnum.NOT_LOGIN.getMessage());
    }
}
