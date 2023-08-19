package com.darren1112.dptms.sdk.starter.security.exceptions;

import com.darren1112.dptms.sdk.starter.security.enums.SecurityErrorEnum;
import org.springframework.security.core.AuthenticationException;

/**
 * 验证码无效异常
 *
 * @author darren
 * @since 2022/11/15
 */
public class CaptchaInvalidException extends AuthenticationException {

    public CaptchaInvalidException(String msg, Throwable t) {
        super(msg, t);
    }

    public CaptchaInvalidException(String msg) {
        super(msg);
    }

    public CaptchaInvalidException() {
        super(SecurityErrorEnum.CAPTCHA_INVALID.getMessage());
    }
}
