package com.darren1112.dptms.sdk.starter.log.enums;

import com.darren1112.dptms.common.core.base.BaseCommonEnum;

/**
 * 日志级别
 *
 * @author luyuhao
 * @since 2021/02/02 00:48
 */
public enum LogLevel implements BaseCommonEnum {

    /**
     * 日志级别
     */
    TRACE(0, "TRACE"),
    DEBUG(1, "DEBUG"),
    INFO(2, "INFO"),
    WARN(3, "WARN"),
    ERROR(4, "ERROR");

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

    /**
     * 根据name匹配枚举对象
     *
     * @param name name
     * @return {@link LogLevel}
     * @author luyuhao
     * @since 2021/02/07 01:46
     */
    public static LogLevel matchByName(String name) {
        try {
            return LogLevel.valueOf(name);
        } catch (Exception e) {
            return INFO;
        }
    }
}
