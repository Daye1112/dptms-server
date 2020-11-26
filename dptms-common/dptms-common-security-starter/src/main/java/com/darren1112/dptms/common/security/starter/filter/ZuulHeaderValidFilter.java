package com.darren1112.dptms.common.security.starter.filter;

import com.darren1112.dptms.common.core.enums.MicroErrorCodeEnum;
import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.ResponseUtil;
import com.darren1112.dptms.common.core.util.StringUtil;
import com.darren1112.dptms.common.security.starter.properties.SecurityProperties;
import org.springframework.util.Base64Utils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author luyuhao
 * @date 2020/11/27 00:12
 */
public class ZuulHeaderValidFilter extends OncePerRequestFilter {

    private SecurityProperties securityProperties;

    public ZuulHeaderValidFilter(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String zuulTokenValue = request.getHeader(securityProperties.getHeaderKey());
        String realZuulTokenValue = new String(Base64Utils.encode(securityProperties.getHeaderValue().getBytes()));
        if (!StringUtil.equal(zuulTokenValue, realZuulTokenValue)) {
            JsonResult jsonResult = JsonResult.buildErrorEnum(MicroErrorCodeEnum.INVALID_ACCESS);
            ResponseUtil.setJsonResult(response, jsonResult);
            return;
        }
        chain.doFilter(request, response);
    }
}
