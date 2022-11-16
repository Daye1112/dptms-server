package com.darren1112.dptms.sdk.starter.security.exceptions;

import com.darren1112.dptms.sdk.starter.security.enums.SecurityEnum;
import org.springframework.security.core.AuthenticationException;

/**
 * 验证码code错误异常
 *
 * @author luyuhao
 * @since 2022/11/15
 */
public class CaptchaCodeErrorException extends AuthenticationException {

    public CaptchaCodeErrorException(String msg, Throwable t) {
        super(msg, t);
    }

    public CaptchaCodeErrorException(String msg) {
        super(msg);
    }

    public CaptchaCodeErrorException() {
        super(SecurityEnum.CAPTCHA_CODE_ERROR.getMessage());
    }
}
