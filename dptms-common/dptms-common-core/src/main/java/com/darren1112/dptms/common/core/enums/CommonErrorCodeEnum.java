package com.darren1112.dptms.common.core.enums;

import com.darren1112.dptms.common.core.exception.enums.BaseErrorEnum;
import org.springframework.http.HttpStatus;

/**
 * 通用错误码
 *
 * @author luyuhao
 * @since 2020/08/02 02:44
 */
public enum CommonErrorCodeEnum implements BaseErrorEnum {

    /**
     * 服务统一错误状态码
     */
    OBJECT_NOT_NULL(HttpStatus.BAD_REQUEST, "对象不能为空"),
    STRING_NOT_EMPTY(HttpStatus.BAD_REQUEST, "字符串不能为空"),
    STRING_TOO_LONG(HttpStatus.BAD_REQUEST, "字符串长度过长"),
    ;

    private Integer code;
    private String message;

    CommonErrorCodeEnum(HttpStatus httpStatus, String message) {
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
