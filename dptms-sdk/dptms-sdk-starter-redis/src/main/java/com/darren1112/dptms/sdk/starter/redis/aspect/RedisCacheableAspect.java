package com.darren1112.dptms.sdk.starter.redis.aspect;

import com.darren1112.dptms.common.core.constants.AopOrderConstant;
import com.darren1112.dptms.common.core.util.JsonUtil;
import com.darren1112.dptms.common.core.util.StringUtil;
import com.darren1112.dptms.sdk.starter.redis.annotation.RedisCacheable;
import com.darren1112.dptms.sdk.starter.redis.core.RedisUtil;
import com.darren1112.dptms.sdk.starter.redis.properties.DptmsRedisProperties;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;

import java.util.Optional;

/**
 * redis缓存aop
 *
 * @author darren
 * @since 2023/8/25
 */
@Slf4j
@Aspect
@Order(AopOrderConstant.REDIS_CACHEABLE_AOP)
public class RedisCacheableAspect extends BaseRedisCacheAop {

    private RedisUtil redisUtil;

    private DptmsRedisProperties dptmsRedisProperties;

    public RedisCacheableAspect(RedisUtil redisUtil,
                                DptmsRedisProperties dptmsRedisProperties) {
        this.redisUtil = redisUtil;
        this.dptmsRedisProperties = dptmsRedisProperties;
    }

    /**
     * 配置切入点
     *
     * @author darren
     * @since 2023/8/25
     */
    @Pointcut("@annotation(com.darren1112.dptms.sdk.starter.redis.annotation.RedisCacheable)")
    public void pointcut() {
        // 该方法无方法体,主要为了让同类中其他方法使用此切入点
    }


    /**
     * 配置环绕通知,使用在方法pointcut()上注册的切入点
     *
     * @param joinPoint 切入点
     * @return {@link Object}
     * @author darren
     * @since 2023/8/25
     */
    @Around("pointcut()")
    public Object cacheAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        // 获取注解
        RedisCacheable classCacheable = joinPoint.getTarget().getClass().getAnnotation(RedisCacheable.class);
        RedisCacheable methodCacheable = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(RedisCacheable.class);

        // 获取缓存前缀
        String prefix = Optional.ofNullable(classCacheable)
                .map(RedisCacheable::value)
                .orElse(Optional.ofNullable(methodCacheable)
                        .map(RedisCacheable::value)
                        .orElse(""));
        prefix = StringUtil.isNotBlank(prefix) ? prefix + ":" : prefix;
        String key = keyGenerator(joinPoint);
        String redisKey = prefix + key;

        // 判断是否存在缓存
        boolean exists = redisUtil.exists(redisKey);
        if (exists) {
            // // 从缓存中获取
            Class<?> returnType = ((MethodSignature) joinPoint.getSignature()).getMethod().getReturnType();
            String resultStr = redisUtil.get(redisKey);
            result = JsonUtil.parseObject(resultStr, returnType);
        } else {
            // 调用接口获取结果
            result = joinPoint.proceed();
            String resultStr = JsonUtil.toJsonString(result);
            // 设置redis缓存
            redisUtil.setEx(redisKey, resultStr, (int) dptmsRedisProperties.getCacheTtl() * 60);
        }

        return result;
    }
}
