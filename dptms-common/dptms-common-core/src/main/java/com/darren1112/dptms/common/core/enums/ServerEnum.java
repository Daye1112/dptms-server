package com.darren1112.dptms.common.core.enums;

import com.darren1112.dptms.common.core.base.BaseCommonEnum;

import java.util.Arrays;

/**
 * 服务枚举
 *
 * @author luyuhao
 * @date 2021/02/17 22:42
 */
public enum ServerEnum implements BaseCommonEnum {
    /**
     * 日志采集器类型
     */
    DPTMS_AUTH(1, "dptms-auth"),
    DPTMS_SYSTEM_MANAGE(2, "dptms-system-manage"),
    DPTMS_MONITOR_MANAGE(3, "dptms-monitor-manage"),
    ;

    private Integer code;
    private String name;

    ServerEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 根据name匹配枚举对象
     *
     * @param code code
     * @return {@link ServerEnum}
     * @author luyuhao
     * @date 2021/02/07 01:46
     */
    public static ServerEnum matchByCode(Integer code) {
        return Arrays.stream(ServerEnum.values())
                .filter(item -> item.getCode().equals(code))
                .findFirst().orElse(null);
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
