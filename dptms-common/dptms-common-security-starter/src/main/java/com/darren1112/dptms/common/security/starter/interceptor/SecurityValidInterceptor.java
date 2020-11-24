package com.darren1112.dptms.common.security.starter.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限校验拦截器
 *
 * @author luyuhao
 * @date 2020/11/25 01:30
 */
public class SecurityValidInterceptor implements HandlerInterceptor {

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
        // TODO token校验
        return true;
    }
}
