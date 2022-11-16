package com.darren1112.dptms.sdk.starter.security.exceptions;

import com.darren1112.dptms.sdk.starter.security.enums.SecurityEnum;
import org.springframework.security.core.AuthenticationException;

/**
 * 验证码无效异常
 *
 * @author luyuhao
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
        super(SecurityEnum.CAPTCHA_INVALID.getMessage());
    }
}
