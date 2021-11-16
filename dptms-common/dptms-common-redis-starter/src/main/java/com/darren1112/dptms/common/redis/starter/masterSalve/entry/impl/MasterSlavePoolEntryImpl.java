package com.darren1112.dptms.common.redis.starter.masterSalve.entry.impl;

import com.darren1112.dptms.common.redis.starter.masterSalve.entry.MasterSlavePoolEntry;
import com.darren1112.dptms.common.redis.starter.masterSalve.health.MasterSlavePoolHealth;
import com.darren1112.dptms.common.redis.starter.masterSalve.health.impl.MasterSlavePoolHealthImpl;
import com.darren1112.dptms.common.redis.starter.masterSalve.state.MasterSlaveState;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

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
    private int database;
    private JedisPool pool;

    private MasterSlaveState state;
    private MasterSlavePoolHealth health;

    public MasterSlavePoolEntryImpl(MasterSlaveState state, String host, int port, String password, int maxTotal, int maxIdle, long maxWaitMillis, int database) {
        this.state = state;
        this.host = host;
        this.port = port;
        this.password = password;
        this.maxTotal = maxTotal;
        this.maxIdle = maxIdle;
        this.maxWaitMillis = maxWaitMillis;
        this.database = database;
        health = new MasterSlavePoolHealthImpl(this);

        initPool();
    }

    private void initPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        config.setTestOnBorrow(false);
        if (password != null) {
            pool = new JedisPool(config, host, port, DEFAULT_TIMEOUT, password, database);
        } else {
            pool = new JedisPool(config, host, port, DEFAULT_TIMEOUT, null, database);
        }
    }

    @Override
    public String getName() {
        return this.host + ":" + this.port;
    }

    @Override
    public MasterSlaveState getState() {
        return state;
    }

    @Override
    public void setState(MasterSlaveState state) {
        this.state = state;
    }

    @Override
    public Jedis getResource() {
        return pool.getResource();
    }

    @Override
    public MasterSlavePoolHealth getHealth() {
        return health;
    }

    public void setHealth(MasterSlavePoolHealth health) {
        this.health = health;
    }

    @Override
    public void close() {
        if (pool != null) {
            pool.close();
        }
    }
}
