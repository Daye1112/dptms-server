package com.darren1112.dptms.auth.common.enums;

import com.darren1112.dptms.common.exception.enums.BaseEnum;
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
    INSUFFICIENT_PERMISSION(HttpStatus.FORBIDDEN,"权限不足!"),
    AUTHORIZED_FAILURE(HttpStatus.UNAUTHORIZED,"认证失败"),
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
