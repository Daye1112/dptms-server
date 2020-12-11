package com.darren1112.dptms.common.redis.starter.config;

import com.alibaba.fastjson.parser.ParserConfig;
import com.darren1112.dptms.common.redis.starter.constant.AcceptPackageConstant;
import com.darren1112.dptms.common.redis.starter.serializer.FastJsonRedisSerializer;
import com.darren1112.dptms.common.redis.starter.serializer.StringRedisSerializer;
import com.darren1112.dptms.common.redis.starter.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

/**
 * Redis配置类
 * ConditionalOnClass 自动配置
 *
 * @author luyuhao
 * @date 2020/08/05 01:16
 */
@Slf4j
@EnableCaching
@ConditionalOnClass(RedisOperations.class)
@EnableConfigurationProperties({RedisProperties.class, CacheProperties.class})
public class RedisAutoConfig extends CachingConfigurerSupport {

    @Value("${spring.cache.redis.key-prefix}")
    private String keyPrefix;

    @Value("${spring.cache.redis.use-key-prefix}")
    private Boolean useKeyPrefix;

    /**
     * 设置 redis 数据默认过期时间，默认1天
     * 设置@cacheable 序列化方式
     *
     * @return RedisCacheConfiguration
     */
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
        configuration = configuration.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(fastJsonRedisSerializer)).entryTtl(Duration.ofHours(2));
        return configuration;
    }

    @Bean(name = "redisTemplate")
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
        FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        redisTemplate.setValueSerializer(fastJsonRedisSerializer);
        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);

        // TODO 建议使用这种方式，小范围指定白名单
        for (String acceptItem : AcceptPackageConstant.ACCEPT_ITEMS) {
            ParserConfig.getGlobalInstance().addAccept(acceptItem);
        }

        //使用StringRedisSerializer来序列化和反序列化redis的key
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
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(".");
            sb.append(method.getName());
            for (Object obj : params) {
                if (obj == null) {
                    continue;
                }
                sb.append("-");
                sb.append(obj);
            }
            return sb.toString();
        };
    }

    @Bean
    public RedisUtil redisUtil(RedisTemplate<Object, Object> redisTemplate) {
        return new RedisUtil(redisTemplate, useKeyPrefix ? keyPrefix : "");
    }

}
