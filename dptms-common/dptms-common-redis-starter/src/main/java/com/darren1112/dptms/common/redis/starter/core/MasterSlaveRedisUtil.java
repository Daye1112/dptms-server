package com.darren1112.dptms.common.redis.starter.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * redis主从模式实现
 *
 * @author luyuhao
 * @since 2021/8/4
 */
public class MasterSlaveRedisUtil implements RedisUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(MasterSlaveRedisUtil.class);

    private static final long TTL_EXPRIED = 0L;

    private static final long TTL_PERMANENT = -1L;

    private static final long TTL_NOT_EXISTS = -2L;

    private String prefix;

    private int maxTotal;

    private int maxIdle;

    private long maxWaitMillis;

    private String ip;

    private String password;

    private boolean enableHeartbeat = true;
}
