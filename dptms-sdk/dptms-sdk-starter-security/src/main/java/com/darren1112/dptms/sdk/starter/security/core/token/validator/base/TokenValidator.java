package com.darren1112.dptms.sdk.starter.security.core.token.validator.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * token校验器
 *
 * @author luyuhao
 * @since 2022/6/7
 */
public interface TokenValidator {

    /**
     * token校验
     *
     * @param request  请求域
     * @param response 响应域
     * @return {@link boolean}
     * @author luyuhao
     * @since 2022/6/7
     */
    boolean doValidate(HttpServletRequest request, HttpServletResponse response);
}
