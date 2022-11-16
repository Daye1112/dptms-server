package com.darren1112.dptms.sdk.starter.security.filter;

import com.darren1112.dptms.common.core.enums.MicroErrorCodeEnum;
import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.ResponseUtil;
import com.darren1112.dptms.common.core.util.StringUtil;
import com.darren1112.dptms.common.core.util.UrlUtil;
import com.darren1112.dptms.sdk.starter.security.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Base64Utils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author luyuhao
 * @since 2020/11/27 00:12
 */
@Slf4j
public class ZuulHeaderValidFilter extends OncePerRequestFilter {

    private SecurityProperties securityProperties;

    public ZuulHeaderValidFilter(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    /**
     * 是否需要过滤
     *
     * @return true/false
     * @author luyuhao
     * @since 20/08/02 14:45
     */
    private boolean shouldNotFilter(String uri) {
        //白名单校验
        return UrlUtil.matchUri(uri, "/actuator/**");
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String uri = request.getRequestURI().substring(request.getContextPath().length());
        if (shouldNotFilter(uri)) {
            chain.doFilter(request, response);
            return;
        }

        String zuulTokenValue = request.getHeader(securityProperties.getHeaderKey());
        String realZuulTokenValue = new String(Base64Utils.encode(securityProperties.getHeaderValue().getBytes()));
        if (!StringUtil.equal(zuulTokenValue, realZuulTokenValue)) {
            JsonResult jsonResult = JsonResult.buildErrorEnum(MicroErrorCodeEnum.INVALID_ACCESS);
            ResponseUtil.writeJson(response, jsonResult);
            return;
        }
        chain.doFilter(request, response);
    }
}
