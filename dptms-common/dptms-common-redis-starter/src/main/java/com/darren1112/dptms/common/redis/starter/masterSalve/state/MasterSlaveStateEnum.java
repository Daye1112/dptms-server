package com.darren1112.dptms.common.redis.starter.masterSalve.state;

/**
 * @author luyuhao
 * @since 2021/8/5
 */
public enum MasterSlaveStateEnum {
    /**
     * 无效服务
     */
    INVALID("invalid"),
    /**
     * 主服务
     */
    MASTER("master"),
    /**
     * 从服务
     */
    SLAVE("slave");

    private String stateName;

    MasterSlaveStateEnum(String stateName) {
        this.stateName = stateName;
    }

    public String getStateName() {
        return stateName;
    }
}
