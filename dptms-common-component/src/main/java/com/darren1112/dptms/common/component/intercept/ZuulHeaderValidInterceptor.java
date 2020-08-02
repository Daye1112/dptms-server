package com.darren1112.dptms.common.component.intercept;

import com.darren1112.dptms.common.component.properties.GatewayProperties;
import com.darren1112.dptms.common.core.enums.MicroErrorCodeEnum;
import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.ResponseUtil;
import com.darren1112.dptms.common.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
@Component
public class ZuulHeaderValidInterceptor implements HandlerInterceptor {

    @Autowired
    private GatewayProperties gatewayProperties;

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
        String zuulTokenValue = request.getHeader(gatewayProperties.getZuulTokenKey());
        String realZuulTokenValue = new String(Base64Utils.encode(gatewayProperties.getZuulTokenValue().getBytes()));
        if(StringUtil.equal(zuulTokenValue, realZuulTokenValue)){
            return true;
        } else {
            JsonResult jsonResult = JsonResult.buildErrorEnum(MicroErrorCodeEnum.INVALID_ACCESS);
            ResponseUtil.setJsonResult(response, jsonResult);
            return false;
        }
    }
}
