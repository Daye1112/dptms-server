package com.darren1112.dptms.common.redis.starter.masterSalve.state.impl;

import com.darren1112.dptms.common.redis.starter.masterSalve.MasterSlaveOperationCallback;
import com.darren1112.dptms.common.redis.starter.masterSalve.entry.MasterSlavePoolEntry;
import com.darren1112.dptms.common.redis.starter.masterSalve.state.MasterSlaveState;
import com.darren1112.dptms.common.redis.starter.masterSalve.state.MasterSlaveStateEnum;

/**
 * @author luyuhao
 * @since 2021/8/5
 */
public class SlaveStateImpl implements MasterSlaveState {

    @Override
    public MasterSlaveStateEnum getState() {
        return null;
    }

    @Override
    public void doMaster(MasterSlavePoolEntry current, MasterSlaveOperationCallback callback) {

    }

    @Override
    public void doSlave(MasterSlavePoolEntry current, MasterSlaveOperationCallback callback) {

    }

    @Override
    public void doInvalid(MasterSlavePoolEntry current, MasterSlaveOperationCallback callback) {

    }
}
