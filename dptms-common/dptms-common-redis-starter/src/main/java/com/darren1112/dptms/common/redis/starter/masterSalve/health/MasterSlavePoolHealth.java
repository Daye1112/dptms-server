package com.darren1112.dptms.common.redis.starter.masterSalve.health;

import com.darren1112.dptms.common.redis.starter.masterSalve.MasterSlaveOperationCallback;

/**
 * @author luyuhao
 * @since 2021/8/5
 */
public interface MasterSlavePoolHealth {

    void doHeartbeat(MasterSlaveOperationCallback callback);
}
