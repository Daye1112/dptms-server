package com.darren1112.dptms.sdk.starter.security.enums;

import com.darren1112.dptms.common.core.exception.enums.BaseErrorEnum;
import org.springframework.http.HttpStatus;

/**
 * 安全校验enum
 *
 * @author luyuhao
 * @since 2021/01/17 00:50
 */
public enum SecurityEnum implements BaseErrorEnum {

    /**
     * 安全校验错误状态码
     */
    NOT_LOGIN(HttpStatus.UNAUTHORIZED, "请先登录"),
    REPEAT_LOGIN(HttpStatus.UNAUTHORIZED, "账号已在其他地方登录"),
    TOKEN_VALID_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "token校验异常，请联系管理员"),
    PERMISSION_VALID_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "权限校验异常，请联系管理员")
    ;

    private Integer code;
    private String message;

    SecurityEnum(HttpStatus httpStatus, String message) {
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
