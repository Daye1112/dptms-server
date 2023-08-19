package com.darren1112.dptms.gateway.common.constants;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

/**
 * zuul 相关常量
 *
 * @author darren
 * @since 2020/11/26 23:31
 */
public class ZuulConstant {

    /**
     * zuul请求过滤器order
     */
    public static final int ZUUL_REQUEST_FILTER_ORDER = FilterConstants.PRE_DECORATION_FILTER_ORDER + 1;
}
