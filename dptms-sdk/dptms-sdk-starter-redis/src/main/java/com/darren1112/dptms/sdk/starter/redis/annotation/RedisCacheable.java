package com.darren1112.dptms.sdk.starter.redis.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * redis缓存注解
 *
 * @author darren
 * @since 2023/8/25
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface RedisCacheable {

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
