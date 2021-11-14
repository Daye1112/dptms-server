package com.darren1112.dptms.common.redis.starter.config;

import com.alibaba.fastjson.parser.ParserConfig;
import com.darren1112.dptms.common.redis.starter.core.MasterSlaveRedisUtil;
import com.darren1112.dptms.common.redis.starter.core.RedisClusterUtil;
import com.darren1112.dptms.common.redis.starter.core.RedisConstants;
import com.darren1112.dptms.common.redis.starter.core.RedisUtil;
import com.darren1112.dptms.common.redis.starter.properties.DptmsRedisProperties;
import com.darren1112.dptms.common.redis.starter.serializer.FastJsonRedisSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * Redis配置类
 * ConditionalOnClass 自动配置
 *
 * @author luyuhao
 * @since 2020/08/05 01:16
 */
@Slf4j
@EnableCaching
@ConditionalOnClass(RedisOperations.class)
@EnableConfigurationProperties({RedisProperties.class,
        // DptmsCacheProperties.class,
        DptmsRedisProperties.class})
public class RedisAutoConfig {

    // @Autowired
    // private DptmsCacheProperties dptmsCacheProperties;

    @Autowired
    private DptmsRedisProperties dptmsRedisProperties;

    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(dptmsRedisProperties.getCacheTtl()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new FastJsonRedisSerializer<>(Object.class)))
                .computePrefixWith((cacheName) -> dptmsRedisProperties.getPrefix() + cacheName + ":");
        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(config)
                .transactionAware()
                .build();
    }

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

    @Bean(name = "redisTemplate")
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
        FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        redisTemplate.setValueSerializer(fastJsonRedisSerializer);
        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);

        // 全局开启AutoType，不建议使用
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        // 建议使用这种方式，小范围指定白名单
        // for (String acceptItem : AcceptPackageConstant.ACCEPT_ITEMS) {
        //     ParserConfig.getGlobalInstance().addAccept(acceptItem);
        // }

        // 使用spring自带的StringRedisSerializer来序列化和反序列化redis的key
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        //开启事务
        redisTemplate.setEnableTransactionSupport(true);

        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    /**
     * 自定义缓存key生成策略
     * 使用方法 @Cacheable(keyGenerator="keyGenerator")
     *
     * @return KeyGenerator
     */
    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getSimpleName());
            sb.append(":");
            sb.append(method.getName());
            int index = 0;
            for (Object obj : params) {
                if (index == 0) {
                    sb.append(":");
                } else {
                    sb.append("_");
                }
                try {
                    sb.append(obj.hashCode());
                } catch (Exception e) {
                    sb.append(obj);
                }
                index++;
            }
            return sb.toString();
        };
    }

    // @Bean
    // public RedisUtil redisUtil(RedisTemplate<String, Object> redisTemplate) {
    //     return new RedisUtil(redisTemplate, dptmsCacheProperties.isUseKeyPrefix() ? dptmsCacheProperties.getKeyPrefix() : "");
    // }

}
