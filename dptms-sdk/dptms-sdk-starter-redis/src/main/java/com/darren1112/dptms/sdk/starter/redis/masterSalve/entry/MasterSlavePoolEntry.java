package com.darren1112.dptms.sdk.starter.redis.masterSalve.entry;

import com.darren1112.dptms.sdk.starter.redis.masterSalve.health.MasterSlavePoolHealth;
import com.darren1112.dptms.sdk.starter.redis.masterSalve.state.MasterSlaveState;
import redis.clients.jedis.Jedis;

/**
 * @author luyuhao
 * @since 2021/8/5
 */
public interface MasterSlavePoolEntry {

    /**
     * 获取当前资源的名称
     *
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/5
     */
    String getName();

    /**
     * 获取当前资源的状态
     *
     * @return {@link MasterSlaveState}
     * @author luyuhao
     * @since 2021/8/5
     */
    MasterSlaveState getState();

    /**
     * 设置当前资源的状态
     *
     * @param state 状态
     * @author luyuhao
     * @since 2021/8/5
     */
    void setState(MasterSlaveState state);

    /**
     * 获取redis连接
     *
     * @return {@link Jedis}
     * @author luyuhao
     * @since 2021/8/5
     */
    Jedis getResource();

    /**
     * 获取当前资源的健康对象
     *
     * @return {@link MasterSlavePoolHealth}
     * @author luyuhao
     * @since 2021/8/5
     */
    MasterSlavePoolHealth getHealth();

    /**
     * 释放资源
     *
     * @author luyuhao
     * @since 2021/8/5
     */
    void close();
}
