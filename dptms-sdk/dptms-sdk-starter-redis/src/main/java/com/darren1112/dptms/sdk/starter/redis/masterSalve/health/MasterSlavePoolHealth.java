package com.darren1112.dptms.sdk.starter.redis.masterSalve.health;

import com.darren1112.dptms.sdk.starter.redis.masterSalve.MasterSlaveOperationCallback;

/**
 * @author darren
 * @since 2021/8/5
 */
public interface MasterSlavePoolHealth {

    void doHeartbeat(MasterSlaveOperationCallback callback);
}
