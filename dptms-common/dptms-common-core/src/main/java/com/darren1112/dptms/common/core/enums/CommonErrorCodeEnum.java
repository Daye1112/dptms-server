package com.darren1112.dptms.common.core.enums;

import com.darren1112.dptms.common.core.exception.enums.BaseEnum;
import org.springframework.http.HttpStatus;

/**
 * 通用错误码
 *
 * @author luyuhao
 * @date 2020/08/02 02:44
 */
public enum CommonErrorCodeEnum implements BaseEnum {

    /**
     * 服务统一错误状态码
     */
    INVALID_TOKEN(HttpStatus.BAD_REQUEST, "token无效"),
    HAS_NO_PERMISSION(HttpStatus.FORBIDDEN, "权限不足!"),
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
