package com.darren1112.dptms.gateway.common.enums;

import com.darren1112.dptms.common.core.exception.enums.BaseEnum;
import org.springframework.http.HttpStatus;

/**
 * 网关错误码
 *
 * @author luyuhao
 * @date 2020/08/02 02:44
 */
public enum GatewayErrorCodeEnum implements BaseEnum {

    /**
     * 网关错误状态码
     */
    NOT_LOGIN(HttpStatus.UNAUTHORIZED, "请先登录"),
    ;

    private Integer code;
    private String message;

    GatewayErrorCodeEnum(HttpStatus httpStatus, String message) {
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
