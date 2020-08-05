package com.darren1112.dptms.common.security.starter.interceptor;

import com.darren1112.dptms.common.core.enums.MicroErrorCodeEnum;
import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.ResponseUtil;
import com.darren1112.dptms.common.core.util.StringUtil;
import com.darren1112.dptms.common.security.starter.properties.SecurityProperties;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 网关header校验拦截器
 *
 * @author luyuhao
 * @date 2020/08/02 18:41
 */
public class ZuulHeaderValidInterceptor implements HandlerInterceptor {

    private SecurityProperties securityProperties;

    public ZuulHeaderValidInterceptor(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    /**
     * 处理方法
     *
     * @param request  请求域
     * @param response 响应域
     * @param handler  Object
     * @return true/false
     * @author luyuhao
     * @date 20/08/02 18:54
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String zuulTokenValue = request.getHeader(securityProperties.getHeaderKey());
        String realZuulTokenValue = new String(Base64Utils.encode(securityProperties.getHeaderValue().getBytes()));
        if (StringUtil.equal(zuulTokenValue, realZuulTokenValue)) {
            return true;
        } else {
            JsonResult jsonResult = JsonResult.buildErrorEnum(MicroErrorCodeEnum.INVALID_ACCESS);
            ResponseUtil.setJsonResult(response, jsonResult);
            return false;
        }
    }
}
