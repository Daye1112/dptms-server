package com.darren1112.dptms.sdk.starter.security.core.token.validator.base;

import com.darren1112.dptms.sdk.starter.security.enums.SecurityErrorEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * token校验器
 *
 * @author darren
 * @since 2022/6/7
 */
public interface TokenValidator {

    /**
     * token校验
     *
     * @param request  请求域
     * @param response 响应域
     * @return {@link boolean}
     * @author darren
     * @since 2022/6/7
     */
    boolean doValidate(HttpServletRequest request, HttpServletResponse response);

    /**
     * 校验失败时的错误码
     *
     * @return {@link SecurityErrorEnum}
     * @author darren
     * @since 2022/11/17
     */
    SecurityErrorEnum validateError();

    /**
     * 校验器执行顺序
     *
     * @return {@link Integer}
     * @author darren
     * @since 2022/11/17
     */
    Integer getOrder();
}
