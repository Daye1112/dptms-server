package com.darren1112.dptms.monitor.common.enums;

import com.darren1112.dptms.common.core.exception.enums.BaseErrorEnum;
import org.springframework.http.HttpStatus;

/**
 * 监控管理错误状态码enum
 *
 * @author luyuhao
 * @since 2021/02/06 20:51
 */
public enum MonitorManageErrorCodeEnum implements BaseErrorEnum {

    /**
     * 监控管理错误状态码
     */
    ID_NOT_NULL(HttpStatus.BAD_REQUEST, "id不能为空"),
    MODULE_NOT_NULL(HttpStatus.BAD_REQUEST, "模块不能为空")
    ;

    private Integer code;
    private String message;

    MonitorManageErrorCodeEnum(HttpStatus httpStatus, String message) {
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
