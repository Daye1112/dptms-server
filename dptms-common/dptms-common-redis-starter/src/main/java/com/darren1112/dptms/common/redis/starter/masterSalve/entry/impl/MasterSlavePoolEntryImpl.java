package com.darren1112.dptms.common.redis.starter.masterSalve.entry.impl;

import com.darren1112.dptms.common.redis.starter.masterSalve.entry.MasterSlavePoolEntry;
import com.darren1112.dptms.common.redis.starter.masterSalve.health.MasterSlavePoolHealth;
import com.darren1112.dptms.common.redis.starter.masterSalve.health.impl.MasterSlavePoolHealthImpl;
import com.darren1112.dptms.common.redis.starter.masterSalve.state.MasterSlaveState;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author luyuhao
 * @since 2021/8/6
 */
public class MasterSlavePoolEntryImpl implements MasterSlavePoolEntry {

    private static final int DEFAULT_TIMEOUT = 15000;

    private String host;
    private int port;
    private String password;
    private int maxTotal;
    private int maxIdle;
    private long maxWaitMillis;
    private JedisPool pool;

    private MasterSlaveState state;
    private MasterSlavePoolHealth health;

    public MasterSlavePoolEntryImpl(MasterSlaveState state, String host, int port, String password, int maxTotal, int maxIdle, long maxWaitMillis) {
        this.state = state;
        this.host = host;
        this.port = port;
        this.password = password;
        this.maxTotal = maxTotal;
        this.maxIdle = maxIdle;
        this.maxWaitMillis = maxWaitMillis;
        health = new MasterSlavePoolHealthImpl(this);

        initPool();
    }

    private void initPool() {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public MasterSlaveState getState() {
        return null;
    }

    @Override
    public void setState(MasterSlaveState state) {

    }

    @Override
    public Jedis getResource() {
        return null;
    }

    @Override
    public MasterSlavePoolHealth getHealth() {
        return null;
    }

    @Override
    public void close() {

    }
}
