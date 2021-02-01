package com.darren1112.dptms.common.log.starter.enums;

import com.darren1112.dptms.common.core.base.BaseCommonEnum;

/**
 * 日志级别
 *
 * @author luyuhao
 * @date 2021/02/02 00:48
 */
public enum LogLevel implements BaseCommonEnum {

    /**
     * 日志级别
     */
    TRACE(0, "TRACE"),
    DEBUG(1, "DEBUG"),
    INFO(2, "INFO"),
    WARN(3, "WARN"),
    ERROR(4, "ERROR")
    ;

    private Integer code;
    private String name;

    LogLevel(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }
}
