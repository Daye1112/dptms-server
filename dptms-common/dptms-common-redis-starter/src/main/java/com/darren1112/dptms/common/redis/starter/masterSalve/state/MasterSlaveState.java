package com.darren1112.dptms.common.redis.starter.masterSalve.state;

import com.darren1112.dptms.common.redis.starter.masterSalve.MasterSlaveOperationCallback;
import com.darren1112.dptms.common.redis.starter.masterSalve.entry.MasterSlavePoolEntry;

/**
 * @author luyuhao
 * @since 2021/8/5
 */
public interface MasterSlaveState {

    MasterSlaveStateEnum getState();

    void doMaster(MasterSlavePoolEntry current, MasterSlaveOperationCallback callback);

    void doSlave(MasterSlavePoolEntry current, MasterSlaveOperationCallback callback);

    void doInvalid(MasterSlavePoolEntry current, MasterSlaveOperationCallback callback);
}
