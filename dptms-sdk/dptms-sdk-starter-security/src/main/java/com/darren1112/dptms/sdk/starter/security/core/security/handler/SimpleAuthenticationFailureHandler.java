package com.darren1112.dptms.sdk.starter.security.core.security.handler;

import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.IpUtil;
import com.darren1112.dptms.common.core.util.ResponseUtil;
import com.darren1112.dptms.sdk.starter.security.enums.SecurityErrorEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败处理器
 *
 * @author darren
 * @since 2022/6/2
 */
public class SimpleAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private static final Logger log = LoggerFactory.getLogger(SimpleAuthenticationEntryPoint.class);

    /**
     * 认证失败处理
     *
     * @param request   请求域
     * @param response  响应域
     * @param exception 认证异常
     * @author darren
     * @since 2022/6/2
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.warn("失败信息: 登录失败, ip: {}, 请求接口: {}", IpUtil.getIp(request), request.getRequestURI());
        ResponseUtil.writeJson(response, JsonResult.buildErrorEnum(SecurityErrorEnum.USERNAME_PASSWORD_ERROR));
    }

}

