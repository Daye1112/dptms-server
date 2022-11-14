package com.darren1112.dptms.gateway.common.filter;

import com.darren1112.dptms.common.core.util.IpUtil;
import com.darren1112.dptms.sdk.starter.security.properties.SecurityProperties;
import com.darren1112.dptms.gateway.common.constants.ZuulConstant;
import com.darren1112.dptms.gateway.common.util.ZuulRequestUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import javax.servlet.http.HttpServletRequest;

/**
 * 上下文过滤器
 *
 * @author luyuhao
 * @since 2020/08/02 14:44
 */
@Slf4j
@Component
public class ZuulRequestFilter extends ZuulFilter {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 过滤器类型
     *
     * @return 过滤器类型
     * @author luyuhao
     * @since 20/08/02 14:45
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 过滤器顺序
     *
     * @return 顺序号
     * @author luyuhao
     * @since 20/08/02 14:45
     */
    @Override
    public int filterOrder() {
        return ZuulConstant.ZUUL_REQUEST_FILTER_ORDER;
    }

    /**
     * 是否需要过滤
     *
     * @return true/false
     * @author luyuhao
     * @since 20/08/02 14:45
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 执行内容
     *
     * @return Object
     * @author luyuhao
     * @since 20/08/02 14:46
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = ZuulRequestUtil.getRequestContext();
        HttpServletRequest request = ZuulRequestUtil.getRequest();

        String serviceId = ctx.get(FilterConstants.SERVICE_ID_KEY).toString();
        String host = IpUtil.getIp(request);
        String method = request.getMethod();
        String uri = request.getRequestURI();

        log.info("请求URI：{}，请求Method：{}，请求IP：{}，ServiceId：{}", uri, method, host, serviceId);

        byte[] value = Base64Utils.encode(securityProperties.getHeaderValue().getBytes());
        ctx.addZuulRequestHeader(securityProperties.getHeaderKey(), new String(value));
        return null;
    }
}
