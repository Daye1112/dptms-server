package com.darren1112.dptms.sdk.starter.security.exceptions;

import com.darren1112.dptms.sdk.starter.security.enums.SecurityEnum;
import org.springframework.security.core.AuthenticationException;

/**
 * 账号或密码错误异常
 *
 * @author luyuhao
 * @since 2022/11/15
 */
public class UsernamePwdErrorException extends AuthenticationException {

    public UsernamePwdErrorException(String msg, Throwable t) {
        super(msg, t);
    }

    public UsernamePwdErrorException(String msg) {
        super(msg);
    }

    public UsernamePwdErrorException() {
        super(SecurityEnum.USERNAME_PASSWORD_ERROR.getMessage());
    }
}
