package com.darren1112.dptms.sdk.starter.log.annotation;

import com.darren1112.dptms.sdk.starter.log.enums.BusinessType;
import com.darren1112.dptms.sdk.starter.log.enums.LogLevel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 日志注解
 *
 * @author darren
 * @since 2021/02/02 00:42
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {

    /**
     * 操作记录
     */
    String value() default "";

    /**
     * 日志级别，用户自定义，异常时自动变为ERROR
     */
    LogLevel logLevel() default LogLevel.INFO;

    /**
     * 业务操作类型
     */
    BusinessType businessType() default BusinessType.OTHER;

}
