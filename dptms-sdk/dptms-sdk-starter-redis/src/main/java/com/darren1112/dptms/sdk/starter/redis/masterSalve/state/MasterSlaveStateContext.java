package com.darren1112.dptms.sdk.starter.redis.masterSalve.state;

import com.darren1112.dptms.sdk.starter.redis.masterSalve.state.impl.InvalidStateImpl;
import com.darren1112.dptms.sdk.starter.redis.masterSalve.state.impl.MasterStateImpl;
import com.darren1112.dptms.sdk.starter.redis.masterSalve.state.impl.SlaveStateImpl;

/**
 * @author darren
 * @since 2021/8/5
 */
public class MasterSlaveStateContext {

    public static final MasterSlaveState STATE_MASTER = new MasterStateImpl();

    public static final MasterSlaveState STATE_SLAVE = new SlaveStateImpl();

    public static final MasterSlaveState STATE_INVALID = new InvalidStateImpl();
}
