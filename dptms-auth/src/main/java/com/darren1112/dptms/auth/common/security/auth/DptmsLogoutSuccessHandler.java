package com.darren1112.dptms.auth.common.security.auth;

import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.ResponseUtil;
import com.darren1112.dptms.sdk.starter.security.base.auth.BaseLogoutSuccessHandler;
import com.darren1112.dptms.sdk.starter.security.core.token.store.TokenStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登出处理器
 *
 * @author darren
 * @since 2022/11/15
 */
@Component
public class DptmsLogoutSuccessHandler extends BaseLogoutSuccessHandler {

    private static final Logger log = LoggerFactory.getLogger(DptmsLogoutSuccessHandler.class);

    @Autowired
    private TokenStore tokenStore;

    /**
     * 登出处理逻辑
     *
     * @param authentication 认证信息
     * @param request        请求域
     * @param response       响应域
     * @throws IOException      io异常
     * @throws ServletException servlet异常
     * @author darren
     * @since 2022/6/6
     */
    @Override
    protected void handle(Authentication authentication, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        tokenStore.removeTokenAndCookie(request, response);
        ResponseUtil.writeJson(response, JsonResult.buildSuccessData("登出成功"));
    }
}
