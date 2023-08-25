package com.darren1112.dptms.sdk.starter.redis.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * redis删除缓存注解
 *
 * @author darren
 * @since 2023/8/25
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RedisCacheEvict {

    /**
     * 缓存前缀
     */
    @AliasFor("cacheNames")
    String value() default "";

    /**
     * 缓存前缀
     */
    @AliasFor("value")
    String cacheNames() default "";
}
