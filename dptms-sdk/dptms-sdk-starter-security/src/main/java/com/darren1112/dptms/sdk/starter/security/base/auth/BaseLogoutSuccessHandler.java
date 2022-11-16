package com.darren1112.dptms.sdk.starter.security.base.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注销处理器
 *
 * @author luyuhao
 * @since 2022/06/05
 */
public abstract class BaseLogoutSuccessHandler implements LogoutSuccessHandler {

    private static final Logger log = LoggerFactory.getLogger(BaseLogoutSuccessHandler.class);

    /**
     * 注销处理逻辑
     *
     * @param request        请求域
     * @param response       响应域
     * @param authentication 用户信息
     * @author luyuhao
     * @since 2022/06/05
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        try {
            handle(authentication, request, response);
        } finally {
            finallyHandle(authentication, request, response);
        }
    }

    /**
     * 登出处理逻辑
     *
     * @param authentication 认证信息
     * @param request        请求域
     * @param response       响应域
     * @throws IOException      io异常
     * @throws ServletException servlet异常
     * @author luyuhao
     * @since 2022/6/6
     */
    protected abstract void handle(Authentication authentication, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

    /**
     * 后置加工处理
     * finally中调用
     *
     * @param authentication 认证信息
     * @param request        请求域
     * @param response       响应域
     * @author luyuhao
     * @since 2022/06/04
     */
    protected void finallyHandle(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        // do nothing
    }
}
