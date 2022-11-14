package com.darren1112.dptms.sdk.starter.redis.masterSalve.state.impl;

import com.darren1112.dptms.sdk.starter.redis.masterSalve.MasterSlaveOperationCallback;
import com.darren1112.dptms.sdk.starter.redis.masterSalve.entry.MasterSlavePoolEntry;
import com.darren1112.dptms.sdk.starter.redis.masterSalve.state.MasterSlaveState;
import com.darren1112.dptms.sdk.starter.redis.masterSalve.state.MasterSlaveStateEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author luyuhao
 * @since 2021/8/5
 */
public class SlaveStateImpl implements MasterSlaveState {

    private static final Logger LOGGER = LoggerFactory.getLogger(SlaveStateImpl.class);

    @Override
    public MasterSlaveStateEnum getState() {
        return MasterSlaveStateEnum.SLAVE;
    }

    @Override
    public void doMaster(MasterSlavePoolEntry current, MasterSlaveOperationCallback callback) {
        callback.delistSlave(current.getName());
        callback.enlistMaster(current.getName());
    }

    @Override
    public void doSlave(MasterSlavePoolEntry current, MasterSlaveOperationCallback callback) {

    }

    @Override
    public void doInvalid(MasterSlavePoolEntry current, MasterSlaveOperationCallback callback) {
        callback.delistSlave(current.getName());
    }
}
