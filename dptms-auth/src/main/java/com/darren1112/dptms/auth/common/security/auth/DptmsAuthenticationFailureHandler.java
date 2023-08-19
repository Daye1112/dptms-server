package com.darren1112.dptms.auth.common.security.auth;

import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.IpUtil;
import com.darren1112.dptms.common.core.util.ResponseUtil;
import com.darren1112.dptms.sdk.starter.security.core.token.store.TokenStore;
import com.darren1112.dptms.sdk.starter.security.enums.SecurityErrorEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * 认证失败处理器
 *
 * @author darren
 * @since 2022/11/15
 */
@Component
public class DptmsAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private static final Logger log = LoggerFactory.getLogger(DptmsAuthenticationFailureHandler.class);

    @Autowired
    private TokenStore tokenStore;

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
        SecurityErrorEnum securityError = Optional.ofNullable(SecurityErrorEnum.matchByAuthException(exception))
                .orElse(SecurityErrorEnum.USERNAME_PASSWORD_ERROR);
        log.warn("请求失败, IP: {}, URL: {}, 失败原因: {}", IpUtil.getIp(request), request.getRequestURI(), securityError.getMessage());
        tokenStore.removeTokenAndCookie(request, response);
        ResponseUtil.writeJson(response, JsonResult.buildErrorEnum(securityError));
    }

}
