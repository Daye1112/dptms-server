package com.darren1112.dptms.gateway.common.util;

import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * zuul请求域工具类
 *
 * @author luyuhao
 * @date 2020/08/02 15:01
 */
public class ZuulRequestUtil {

    /**
     * 获取请求上下文
     *
     * @return RequestContext
     * @author luyuhao
     * @date 20/08/02 14:53
     */
    public static RequestContext getRequestContext() {
        return RequestContext.getCurrentContext();
    }

    /**
     * 获取请求域
     *
     * @return HttpServletRequest
     * @author luyuhao
     * @date 20/08/02 14:53
     */
    public static HttpServletRequest getRequest() {
        return getRequestContext().getRequest();
    }

    /**
     * 获取响应域
     *
     * @return HttpServletResponse
     * @author luyuhao
     * @date 20/08/02 14:53
     */
    public static HttpServletResponse getResponse() {
        return getRequestContext().getResponse();
    }
}
