package com.darren1112.dptms.auth.common.security.auth;

import com.darren1112.dptms.auth.service.AuthUserService;
import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.IpUtil;
import com.darren1112.dptms.common.core.util.ResponseUtil;
import com.darren1112.dptms.common.core.util.WebUtil;
import com.darren1112.dptms.common.spi.monitor.dto.MonitorLoginLogDto;
import com.darren1112.dptms.sdk.starter.log.collect.LogCollectService;
import com.darren1112.dptms.sdk.starter.security.base.auth.BaseAuthenticationSuccessHandler;
import com.darren1112.dptms.sdk.starter.security.core.token.store.TokenStore;
import com.darren1112.dptms.sdk.starter.security.model.ActiveUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * 登录成功处理器
 *
 * @author darren
 * @since 2022/11/15
 */
@Component
public class DptmsAuthenticationSuccessHandler extends BaseAuthenticationSuccessHandler {

    private static final Logger log = LoggerFactory.getLogger(DptmsAuthenticationSuccessHandler.class);

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private LogCollectService logCollectService;

    @Autowired
    private AuthUserService authUserService;

    /**
     * 登录成功处理逻辑
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
        log.info("登录成功, 用户信息: {}", authentication);
        ActiveUser activeUser = (ActiveUser) authentication.getPrincipal();
        // 后置处理
        setExtAttr(activeUser, request);
        // 更新登录时间
        authUserService.updateLoginTime(activeUser.getId());
        // 生成token并保存到redis和cookie中
        tokenStore.generateToken(activeUser, response);
        // 登录日志收集
        loginLogCollect(activeUser);
        ResponseUtil.writeJson(response, JsonResult.buildSuccessData("登录成功"));
    }

    /**
     * 设置额外属性
     *
     * @param activeUser 用户信息
     * @param request    请求域
     * @author darren
     * @since 2022/11/15
     */
    private void setExtAttr(ActiveUser activeUser, HttpServletRequest request) {
        activeUser.setIp(IpUtil.getIp(request));
        activeUser.setIpAddress(IpUtil.getCityInfo(activeUser.getIp()));
        activeUser.setBrowser(WebUtil.getBrowser());
        activeUser.setOs(WebUtil.getOs());
        activeUser.setLoginTime(new Date());
    }

    /**
     * 登录日志采集
     *
     * @param activeUser 登录用户信息
     * @author darren
     * @since 2021/2/9 14:09
     */
    private void loginLogCollect(ActiveUser activeUser) {
        MonitorLoginLogDto loginLogDto = new MonitorLoginLogDto();
        loginLogDto.setUsername(activeUser.getUsername());
        loginLogDto.setOs(activeUser.getOs());
        loginLogDto.setBrowser(activeUser.getBrowser());
        loginLogDto.setIp(activeUser.getIp());
        loginLogDto.setIpAddress(activeUser.getIpAddress());
        loginLogDto.setCtime(new Date());
        loginLogDto.setMtime(new Date());
        loginLogDto.setCreater(activeUser.getId());
        loginLogDto.setUpdater(activeUser.getId());
        logCollectService.loginLogCollect(loginLogDto);
    }
}
