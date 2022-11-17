package com.darren1112.dptms.gateway.common.filter;

import com.darren1112.dptms.common.core.enums.MicroErrorCodeEnum;
import com.darren1112.dptms.common.core.util.IpUtil;
import com.darren1112.dptms.gateway.common.util.ZuulRequestUtil;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 网关异常拦截处理
 *
 * @author luyuhao
 * @since 2019/12/19 9:35
 */
@Slf4j
@Component
public class ZuulErrorFilter extends SendErrorFilter {
    @Override
    public String filterType() {
        return FilterConstants.ERROR_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_ERROR_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return ZuulRequestUtil.getRequestContext().getThrowable() != null;
    }

    @Override
    public Object run() {
        try {
            RequestContext ctx = ZuulRequestUtil.getRequestContext();
            if (ctx.getThrowable() != null) {
                //打印错误信息
                HttpServletRequest request = ZuulRequestUtil.getRequest();
                String serviceId = ctx.get(FilterConstants.SERVICE_ID_KEY).toString();
                String host = IpUtil.getIp(request);
                String method = request.getMethod();
                String uri = request.getRequestURI();
                log.error("请求失败，URI: {}, Method: {}, IP: {}, ServiceId: {}, ErrorMessage: {}", uri, method, host, serviceId, ctx.getThrowable().getMessage());

                //阻止SendErrorFilter
                ctx.set(SEND_ERROR_FILTER_RAN, true);

                //设置错误信息
                ZuulRequestUtil.returnError(MicroErrorCodeEnum.SERVER_DOWN);
            }
        } catch (Exception e) {
            log.error("zuul异常处理异常", e);
        }
        return null;
    }
}
