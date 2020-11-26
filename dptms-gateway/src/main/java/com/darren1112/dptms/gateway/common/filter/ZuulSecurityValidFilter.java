package com.darren1112.dptms.gateway.common.filter;

import com.darren1112.dptms.common.core.constants.SecurityConstant;
import com.darren1112.dptms.common.core.util.CookieUtil;
import com.darren1112.dptms.common.core.util.StringUtil;
import com.darren1112.dptms.common.redis.starter.util.TokenUtil;
import com.darren1112.dptms.gateway.common.constants.ZuulConstant;
import com.darren1112.dptms.gateway.common.enums.GatewayErrorCodeEnum;
import com.darren1112.dptms.gateway.common.util.ZuulRequestUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 安全验证过滤器
 *
 * @author luyuhao
 * @date 2020/11/26 23:28
 */
@Slf4j
@Component
public class ZuulSecurityValidFilter extends ZuulFilter {

    @Autowired
    private TokenUtil tokenUtil;

    /**
     * 过滤器类型
     *
     * @return 过滤器类型
     * @author luyuhao
     * @date 20/08/02 14:45
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
     * @date 20/08/02 14:45
     */
    @Override
    public int filterOrder() {
        return ZuulConstant.ZUUL_SECURITY_VALID_FILTER_ORDER;
    }

    /**
     * 是否需要过滤
     *
     * @return true/false
     * @author luyuhao
     * @date 20/08/02 14:45
     */
    @Override
    public boolean shouldFilter() {
        //白名单校验
        return true;
    }

    /**
     * 执行内容
     *
     * @return Object
     * @author luyuhao
     * @date 20/08/02 14:46
     */
    @Override
    public Object run() throws ZuulException {
        HttpServletRequest request = ZuulRequestUtil.getRequest();
        // token校验
        // 优先处理cookie中携带的cookie
        String accessToken = CookieUtil.getCookie(SecurityConstant.ACCESS_TOKEN_KEY, request);
        String refreshToken = CookieUtil.getCookie(SecurityConstant.REFRESH_TOKEN_KEY, request);
        if (StringUtil.isBlank(accessToken) || StringUtil.isBlank(refreshToken)) {
            accessToken = request.getHeader(SecurityConstant.ACCESS_TOKEN_KEY);
            refreshToken = request.getHeader(SecurityConstant.REFRESH_TOKEN_KEY);
        }
        if (StringUtil.isBlank(accessToken) || StringUtil.isBlank(refreshToken)) {
            ZuulRequestUtil.returnError(GatewayErrorCodeEnum.NOT_LOGIN);
            return null;
        }
        // TODO token存在，校验是否合法
        String redisRefreshToken = tokenUtil.getRefreshToken(accessToken);
        return null;
    }
}
