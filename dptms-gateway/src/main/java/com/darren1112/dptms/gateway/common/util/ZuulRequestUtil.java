package com.darren1112.dptms.gateway.common.util;

import com.darren1112.dptms.common.core.exception.enums.BaseErrorEnum;
import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.ResponseUtil;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * zuul请求域工具类
 *
 * @author luyuhao
 * @since 2020/08/02 15:01
 */
public class ZuulRequestUtil {

    /**
     * 获取请求上下文
     *
     * @return RequestContext
     * @author luyuhao
     * @since 20/08/02 14:53
     */
    public static RequestContext getRequestContext() {
        return RequestContext.getCurrentContext();
    }

    /**
     * 获取请求域
     *
     * @return HttpServletRequest
     * @author luyuhao
     * @since 20/08/02 14:53
     */
    public static HttpServletRequest getRequest() {
        return getRequestContext().getRequest();
    }

    /**
     * 获取响应域
     *
     * @return HttpServletResponse
     * @author luyuhao
     * @since 20/08/02 14:53
     */
    public static HttpServletResponse getResponse() {
        return getRequestContext().getResponse();
    }

    /**
     * 返回错误信息
     *
     * @param baseErrorEnum 错误信息
     * @author luyuhao
     * @since 20/11/26 23:54
     */
    public static void returnError(BaseErrorEnum baseErrorEnum) {
        RequestContext ctx = getRequestContext();
        // 直接打回
        ctx.setSendZuulResponse(false);
        ResponseUtil.writeJson(ctx.getResponse(), JsonResult.buildErrorEnum(baseErrorEnum));
    }
}
