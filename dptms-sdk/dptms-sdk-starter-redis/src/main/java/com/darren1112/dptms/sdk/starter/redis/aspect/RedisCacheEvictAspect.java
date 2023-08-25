package com.darren1112.dptms.sdk.starter.redis.aspect;

import com.darren1112.dptms.common.core.constants.AopOrderConstant;
import com.darren1112.dptms.common.core.util.CollectionUtil;
import com.darren1112.dptms.common.core.util.StringUtil;
import com.darren1112.dptms.sdk.starter.redis.annotation.RedisCacheEvict;
import com.darren1112.dptms.sdk.starter.redis.annotation.RedisCacheable;
import com.darren1112.dptms.sdk.starter.redis.core.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;

import java.util.Optional;
import java.util.Set;

/**
 * redis缓存清除aop
 *
 * @author darren
 * @since 2023/8/25
 */
@Slf4j
@Aspect
@Order(AopOrderConstant.REDIS_CACHE_EVICT_AOP)
public class RedisCacheEvictAspect extends BaseRedisCacheAop {

    private RedisUtil redisUtil;

    public RedisCacheEvictAspect(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    /**
     * 配置切入点
     *
     * @author darren
     * @since 2023/8/25
     */
    @Pointcut("@annotation(com.darren1112.dptms.sdk.starter.redis.annotation.RedisCacheEvict)")
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
    public Object cacheEvictAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取注解
        RedisCacheable classCacheable = joinPoint.getTarget().getClass().getAnnotation(RedisCacheable.class);
        RedisCacheEvict cacheEvict = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(RedisCacheEvict.class);

        // 获取缓存前缀
        String prefix = Optional.ofNullable(classCacheable)
                .map(RedisCacheable::value)
                .orElse(Optional.ofNullable(cacheEvict)
                        .map(RedisCacheEvict::value)
                        .orElse(""));
        prefix = StringUtil.isNotBlank(prefix) ? prefix + ":" : prefix;

        Set<String> keys = redisUtil.getKeys(prefix);

        if (CollectionUtil.isNotEmpty(keys)) {
            redisUtil.removeKeys(keys);
        }
        return joinPoint.proceed();
    }
}
