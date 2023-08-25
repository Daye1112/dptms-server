package com.darren1112.dptms.common.core.constants;

/**
 * aop执行顺序常量
 *
 * @author darren
 * @since 2023/8/25
 */
public class AopOrderConstant {

    /**
     * 日志采集aop
     */
    public static final int LOG_AOP = 1;

    /**
     * redis缓存清除aop
     */
    public static final int REDIS_CACHE_EVICT_AOP = 1;

    /**
     * redis缓存aop
     */
    public static final int REDIS_CACHEABLE_AOP = 2;

}
