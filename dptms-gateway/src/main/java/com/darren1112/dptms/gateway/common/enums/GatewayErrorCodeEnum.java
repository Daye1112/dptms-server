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
    FORBIDDEN(HttpStatus.FORBIDDEN, "无访问权限，请联系管理员申请权限"),
    USER_ALREADY_DELETE(HttpStatus.UNAUTHORIZED, "账号已被删除，请联系管理员"),
    USER_IS_LOCKED(HttpStatus.UNAUTHORIZED, "账号已被锁定，请联系管理员"),
    PASSWORD_UPDATE(HttpStatus.FORBIDDEN, "密码已修改，请重新登录"),
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
