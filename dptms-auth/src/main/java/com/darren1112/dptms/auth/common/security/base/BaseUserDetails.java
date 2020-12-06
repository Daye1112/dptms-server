package com.darren1112.dptms.auth.common.security.base;

import com.darren1112.dptms.auth.common.security.UserDetailsService;
import com.darren1112.dptms.common.core.exception.BadRequestException;
import com.darren1112.dptms.common.core.exception.BaseException;
import com.darren1112.dptms.common.spi.common.dto.LoginParam;
import com.darren1112.dptms.common.spi.common.dto.ActiveUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户信息Base
 *
 * @author luyuhao
 * @date 2020/11/22 17:11
 */
@Slf4j
public abstract class BaseUserDetails implements UserDetailsService {

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
    @Override
    public ActiveUser loginHandler(LoginParam loginParam, HttpServletRequest request, HttpServletResponse response) {
        try {
            // 前置处理
            preHandler(loginParam, request, response);
            // 处理
            ActiveUser activeUser = handler(loginParam, request, response);
            // 后置处理
            afterHandler(activeUser, request, response);
            // 返回用户信息
            return activeUser;
        } catch (BaseException be) {
            // 已处理异常直接抛出
            throw be;
        } catch (Exception e) {
            // 未知异常处理
            log.error(e.getMessage(), e);
            throw exceptionHandler(e);
        }
    }

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
    @Override
    public abstract void preHandler(LoginParam loginParam, HttpServletRequest request, HttpServletResponse response) throws BaseException;

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
    @Override
    public abstract ActiveUser handler(LoginParam loginParam, HttpServletRequest request, HttpServletResponse response) throws BaseException;

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
    @Override
    public abstract void afterHandler(ActiveUser activeUser, HttpServletRequest request, HttpServletResponse response) throws BaseException;

    /**
     * 未知异常处理
     *
     * @param e 异常
     * @return {@link BaseException 处理后的异常}
     * @author luyuhao
     * @date 20/11/22 21:11
     */
    @Override
    public BaseException exceptionHandler(Exception e) {
        return new BadRequestException(HttpStatus.BAD_REQUEST.value(), "错误信息: " + e.getMessage());
    }
}
