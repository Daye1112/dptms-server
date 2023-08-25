package com.darren1112.dptms.sdk.starter.redis.config;

import com.darren1112.dptms.sdk.starter.redis.aspect.RedisCacheEvictAspect;
import com.darren1112.dptms.sdk.starter.redis.aspect.RedisCacheableAspect;
import com.darren1112.dptms.sdk.starter.redis.core.MasterSlaveRedisUtil;
import com.darren1112.dptms.sdk.starter.redis.core.RedisClusterUtil;
import com.darren1112.dptms.sdk.starter.redis.core.RedisConstants;
import com.darren1112.dptms.sdk.starter.redis.core.RedisUtil;
import com.darren1112.dptms.sdk.starter.redis.properties.DptmsRedisProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * Redis配置类
 * ConditionalOnClass 自动配置
 *
 * @author darren
 * @since 2020/08/05 01:16
 */
@Slf4j
@EnableConfigurationProperties({DptmsRedisProperties.class})
public class RedisAutoConfig {

    @Autowired
    private DptmsRedisProperties dptmsRedisProperties;

    @Bean
    @ConditionalOnMissingBean
    public RedisUtil redisUtil() {
        String model = this.dptmsRedisProperties.getModel();
        if (RedisConstants.MODEL_MASTER_SLAVE.equals(model)) {
            MasterSlaveRedisUtil redisUtil = new MasterSlaveRedisUtil();
            redisUtil.setPrefix(this.dptmsRedisProperties.getPrefix());
            redisUtil.setMaxTotal(this.dptmsRedisProperties.getMaxTotal());
            redisUtil.setMaxIdle(this.dptmsRedisProperties.getMaxIdle());
            redisUtil.setMaxWaitMills((long) this.dptmsRedisProperties.getMaxWaitMillis());
            redisUtil.setIp(this.dptmsRedisProperties.getIp());
            redisUtil.setPassword(this.dptmsRedisProperties.getPassword());
            redisUtil.setDatabase(this.dptmsRedisProperties.getDatabase());
            redisUtil.setEnableHeartbeat(this.dptmsRedisProperties.isEnableHeartbeat());
            redisUtil.init();
            return redisUtil;
        } else if (RedisConstants.MODEL_CLUSTER.equals(model)) {
            RedisClusterUtil redisClusterUtil = new RedisClusterUtil();
            redisClusterUtil.setPrefix(this.dptmsRedisProperties.getPrefix());
            redisClusterUtil.setMaxTotal(this.dptmsRedisProperties.getMaxTotal());
            redisClusterUtil.setMaxIdle(this.dptmsRedisProperties.getMaxIdle());
            redisClusterUtil.setMaxWaitMills((long) this.dptmsRedisProperties.getMaxWaitMillis());
            redisClusterUtil.setIp(this.dptmsRedisProperties.getIp());
            redisClusterUtil.setPassword(this.dptmsRedisProperties.getPassword());
            redisClusterUtil.init();
            return redisClusterUtil;
        } else {
            return null;
        }
    }

    /**
     * 缓存aop
     *
     * @param redisUtil            redis工具
     * @param dptmsRedisProperties 配置类
     * @return {@link RedisCacheableAspect}
     * @author darren
     * @since 2023/8/25
     */
    @Bean
    public RedisCacheableAspect redisCacheableAspect(RedisUtil redisUtil,
                                                     DptmsRedisProperties dptmsRedisProperties) {
        return new RedisCacheableAspect(redisUtil, dptmsRedisProperties);
    }

    /**
     * 缓存清除aop
     *
     * @param redisUtil redis工具
     * @return {@link RedisCacheableAspect}
     * @author darren
     * @since 2023/8/25
     */
    @Bean
    public RedisCacheEvictAspect redisCacheEvictAspect(RedisUtil redisUtil) {
        return new RedisCacheEvictAspect(redisUtil);
    }

}
