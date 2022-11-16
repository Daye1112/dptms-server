package com.darren1112.dptms.sdk.starter.security.exceptions;

import org.springframework.security.core.AuthenticationException;

/**
 * 认证token异常
 *
 * @author luyuhao
 * @since 2022/6/7
 */
public class AuthTokenErrorException extends AuthenticationException {

    public AuthTokenErrorException(String msg, Throwable t) {
        super(msg, t);
    }

    public AuthTokenErrorException(String msg) {
        super(msg);
    }

    public AuthTokenErrorException() {
        super("认证token异常");
    }
}
