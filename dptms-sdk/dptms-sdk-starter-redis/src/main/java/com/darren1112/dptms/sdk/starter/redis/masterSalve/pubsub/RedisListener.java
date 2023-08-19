package com.darren1112.dptms.sdk.starter.redis.masterSalve.pubsub;

import com.darren1112.dptms.common.core.util.CollectionUtil;
import com.darren1112.dptms.sdk.starter.redis.core.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPubSub;

import java.util.Optional;
import java.util.concurrent.Executor;

/**
 * @author darren
 * @since 2021/8/6
 */
public class RedisListener implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisListener.class);

    private RedisUtil redisUtil;

    private JedisPubSub sub;

    private String[] channels;

    private boolean subSuccess = false;

    private Executor executor;

    public RedisListener(RedisUtil redisUtil, JedisPubSub sub) {
        this.redisUtil = redisUtil;
        this.sub = sub;
    }

    public void init() {
        this.executor.execute(this);
        LOGGER.info("RedisListener init");
    }

    public void close() {
        if (this.sub != null && this.sub.isSubscribed()) {
            this.sub.unsubscribe();
        }
        LOGGER.info("RedisListener close");
    }

    @Override
    public void run() {
        if (CollectionUtil.isEmpty(channels)) {
            throw new IllegalArgumentException("Invalid argument syntax");
        }
        while (true) {
            try {
                boolean subscribed = Optional.ofNullable(this.sub).map(JedisPubSub::isSubscribed).orElse(false);
                LOGGER.info("subSuccess = {}, sub.isSubscribed = {}", this.subSuccess, subscribed);
                if (this.sub != null && !this.subSuccess) {
                    this.subSuccess = true;
                    LOGGER.info("try subscribe: {}, {}", this.channels, this.channels.length);
                    redisUtil.subscribe(this.sub, this.channels);
                } else if (this.subSuccess && !subscribed) {
                    LOGGER.info("RedisListener break");
                }
            } catch (Exception e) {
                subSuccess = false;
                LOGGER.error("subscribe error!", e);
                try {
                    Thread.sleep(2000L);
                } catch (Exception e1) {
                    LOGGER.error("subscribe error!", e1);
                }
            }
        }
    }

    public RedisUtil getRedisUtil() {
        return redisUtil;
    }

    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    public JedisPubSub getSub() {
        return sub;
    }

    public void setSub(JedisPubSub sub) {
        this.sub = sub;
    }

    public String[] getChannels() {
        return channels;
    }

    public void setChannels(String[] channels) {
        this.channels = channels;
    }

    public Executor getExecutor() {
        return executor;
    }

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }
}
