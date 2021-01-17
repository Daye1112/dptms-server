package com.darren1112.dptms.auth.common.security;

import com.darren1112.dptms.common.core.exception.BaseException;
import com.darren1112.dptms.common.spi.common.dto.ActiveUser;
import com.darren1112.dptms.common.spi.common.dto.LoginParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户信息Service
 *
 * @author luyuhao
 * @date 2020/11/22 17:07
 */
public interface UserDetailsService {

    /**
     * 登录鉴权
     *
     * @param loginParam 登录参数
     * @param request    请求域
     * @param response   响应域
     * @return {@link ActiveUser 用户信息}
     * @author luyuhao
     * @date 20/11/22 17:25
     */
    ActiveUser loginHandler(LoginParam loginParam, HttpServletRequest request, HttpServletResponse response);

    /**
     * 前置处理
     *
     * @param loginParam 登录参数
     * @param request    请求域
     * @param response   响应域
     * @throws BaseException 异常
     * @author luyuhao
     * @date 20/11/22 17:26
     */
    void preHandler(LoginParam loginParam, HttpServletRequest request, HttpServletResponse response) throws BaseException;

    /**
     * 处理
     *
     * @param loginParam 登录参数
     * @param request    请求域
     * @param response   响应域
     * @return {@link ActiveUser}
     * @throws BaseException 异常
     * @author luyuhao
     * @date 20/11/22 21:07
     */
    ActiveUser handler(LoginParam loginParam, HttpServletRequest request, HttpServletResponse response) throws BaseException;

    /**
     * 后置处理
     *
     * @param activeUser 用户信息
     * @param request    请求域
     * @param response   响应域
     * @throws BaseException 异常
     * @author luyuhao
     * @date 20/11/22 17:42
     */
    void afterHandler(ActiveUser activeUser, HttpServletRequest request, HttpServletResponse response) throws BaseException;

    /**
     * 未知异常处理
     *
     * @param e 异常
     * @return {@link BaseException 处理后的异常}
     * @author luyuhao
     * @date 20/11/22 21:11
     */
    BaseException exceptionHandler(Exception e);
}
