package com.darren1112.dptms.sdk.starter.security.core.security;

import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.IpUtil;
import com.darren1112.dptms.common.core.util.ResponseUtil;
import com.darren1112.dptms.sdk.starter.security.enums.SecurityEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 未认证处理器
 *
 * @author luyuhao
 * @since 2022/6/2
 */
public class SimpleAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger log = LoggerFactory.getLogger(SimpleAuthenticationEntryPoint.class);

    /**
     * 未登录的处理逻辑
     *
     * @param request       请求域
     * @param response      响应域
     * @param authException 认证异常
     * @author luyuhao
     * @since 2022/6/2
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.warn("失败信息: 未登录, ip: {}, 请求接口: {}", IpUtil.getIp(request), request.getRequestURI());
        ResponseUtil.writeJson(response, JsonResult.buildErrorEnum(SecurityEnum.NOT_LOGIN));
    }
}
