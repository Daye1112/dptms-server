package com.darren1112.dptms.sdk.starter.security.enums;

import com.darren1112.dptms.common.core.exception.enums.BaseErrorEnum;
import com.darren1112.dptms.sdk.starter.security.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;

import java.util.Arrays;

/**
 * 安全校验enum
 *
 * @author darren
 * @since 2021/01/17 00:50
 */
public enum SecurityErrorEnum implements BaseErrorEnum {

    /**
     * 安全校验错误状态码
     */
    NOT_LOGIN(HttpStatus.UNAUTHORIZED, "请先登录", NotLoginException.class),
    REPEAT_LOGIN(HttpStatus.UNAUTHORIZED, "账号已在其他地方登录", RepeatLoginException.class),
    TOKEN_VALID_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "访问凭证异常，请联系管理员", AuthTokenErrorException.class),
    PERMISSION_VALID_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "权限校验异常，请联系管理员", null),
    HAS_NO_PERMISSION(HttpStatus.FORBIDDEN, "权限不足", null),
    USERNAME_PASSWORD_ERROR(HttpStatus.BAD_REQUEST, "账号或密码错误", UsernamePwdErrorException.class),
    CAPTCHA_INVALID(HttpStatus.BAD_REQUEST, "验证码已失效，请刷新验证码", CaptchaInvalidException.class),
    CAPTCHA_CODE_ERROR(HttpStatus.BAD_REQUEST, "验证码错误", CaptchaCodeErrorException.class),
    LOCKED(HttpStatus.BAD_REQUEST, "用户已被锁定，请联系管理员", LockedException.class),
    ;

    private Integer code;
    private String message;

    private Class<? extends AuthenticationException> authExceptionClass;

    SecurityErrorEnum(HttpStatus httpStatus, String message, Class<? extends AuthenticationException> authExceptionClass) {
        this.code = httpStatus.value();
        this.message = message;
        this.authExceptionClass = authExceptionClass;
    }

    /**
     * 根据异常匹配枚举
     *
     * @param authenticationException 认证异常
     * @return {@link SecurityErrorEnum}
     * @author darren
     * @since 2022/11/16
     */
    public static SecurityErrorEnum matchByAuthException(AuthenticationException authenticationException) {
        if (authenticationException == null) {
            return null;
        }
        return Arrays.stream(SecurityErrorEnum.values())
                .filter(item -> item.getAuthExceptionClass() != null
                        && authenticationException.getClass().equals(item.getAuthExceptionClass()))
                .findFirst().orElse(null);
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Class<? extends AuthenticationException> getAuthExceptionClass() {
        return authExceptionClass;
    }
}
