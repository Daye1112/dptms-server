package com.darren1112.dptms.common.redis.starter.masterSalve.health.impl;

import com.darren1112.dptms.common.redis.starter.masterSalve.MasterSlaveOperationCallback;
import com.darren1112.dptms.common.redis.starter.masterSalve.entry.MasterSlavePoolEntry;
import com.darren1112.dptms.common.redis.starter.masterSalve.health.MasterSlavePoolHealth;

/**
 * @author luyuhao
 * @since 2021/8/6
 */
public class MasterSlavePoolHealthImpl implements MasterSlavePoolHealth {

    private static final int HEALTH_RETRY_COUNT = 3;

    private int currentCount = 0;

    private MasterSlavePoolEntry masterSlavePoolEntry;

    public MasterSlavePoolHealthImpl(MasterSlavePoolEntry masterSlavePoolEntry) {
        this.masterSlavePoolEntry = masterSlavePoolEntry;
    }

    @Override
    public void doHeartbeat(MasterSlaveOperationCallback callback) {

    }
}
