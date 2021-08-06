package com.darren1112.dptms.common.redis.starter.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * redis集群模式实现
 *
 * @author luyuhao
 * @since 2021/8/4
 */
public class RedisClusterUtil implements RedisUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisClusterUtil.class);

    private String prefix;

    private int maxTotal;

    private int maxIdle;

    private long maxWaitMillis;

    private String ip;

    private String password;

    private JedisCluster jedisCluster;

    /**
     * 存放集群的ip及端口
     */
    Set<HostAndPort> nodes = new LinkedHashSet<>();
}
