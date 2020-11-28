package com.darren1112.dptms.auth.common.enums;

import com.darren1112.dptms.common.core.exception.enums.BaseEnum;
import org.springframework.http.HttpStatus;

/**
 * 认证服务错误状态码
 *
 * @author luyuhao
 * @date 2020/07/26 21:22
 */
public enum AuthErrorCodeEnum implements BaseEnum {

    /**
     * 认证服务错误状态码
     */
    LOCKED(HttpStatus.BAD_REQUEST, "用户已被锁定，请联系管理员"),
    LOGIN_FAILURE(HttpStatus.BAD_REQUEST, "用户名或密码错误"),
    SAVE_TOKEN_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "token保存异常，请联系管理员"),
    USERNAME_NOT_NULL(HttpStatus.BAD_REQUEST, "账号不能为空"),
    PASSWORD_NOT_NULL(HttpStatus.BAD_REQUEST, "密码不能为空"),
    LOGIN_TYPE_NOT_NULL(HttpStatus.BAD_REQUEST, "登录类型不能为空"),
    REFRESH_TOKEN_NOT_NULL(HttpStatus.BAD_REQUEST, "refreshToken不能为空")
    ;

    private Integer code;
    private String message;

    AuthErrorCodeEnum(HttpStatus httpStatus, String message) {
        this.code = httpStatus.value();
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
