package com.darren1112.dptms.sdk.starter.redis.masterSalve.state.impl;

import com.darren1112.dptms.sdk.starter.redis.masterSalve.MasterSlaveOperationCallback;
import com.darren1112.dptms.sdk.starter.redis.masterSalve.entry.MasterSlavePoolEntry;
import com.darren1112.dptms.sdk.starter.redis.masterSalve.state.MasterSlaveState;
import com.darren1112.dptms.sdk.starter.redis.masterSalve.state.MasterSlaveStateEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author darren
 * @since 2021/8/5
 */
public class MasterStateImpl implements MasterSlaveState {

    private static final Logger LOGGER = LoggerFactory.getLogger(MasterStateImpl.class);

    @Override
    public MasterSlaveStateEnum getState() {
        return MasterSlaveStateEnum.MASTER;
    }

    @Override
    public void doMaster(MasterSlavePoolEntry current, MasterSlaveOperationCallback callback) {

    }

    @Override
    public void doSlave(MasterSlavePoolEntry current, MasterSlaveOperationCallback callback) {
        callback.delistMaster(current.getName());
        callback.enlistSlave(current.getName());
    }

    @Override
    public void doInvalid(MasterSlavePoolEntry current, MasterSlaveOperationCallback callback) {
        callback.delistMaster(current.getName());
    }
}
