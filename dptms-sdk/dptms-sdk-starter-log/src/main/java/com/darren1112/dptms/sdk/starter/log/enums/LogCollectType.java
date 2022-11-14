package com.darren1112.dptms.sdk.starter.log.enums;

import com.darren1112.dptms.common.core.base.BaseCommonEnum;

/**
 * 日志采集器类型
 *
 * @author luyuhao
 * @since 2021/02/08 01:04
 */
public enum LogCollectType implements BaseCommonEnum {
    /**
     * 日志采集器类型
     */
    FEIGN(0, "feign"),
    ;

    private Integer code;
    private String name;

    LogCollectType(Integer code, String name) {
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
