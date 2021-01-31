package com.darren1112.dptms.common.security.starter.core;

import com.darren1112.dptms.common.core.constants.SecurityConstant;
import com.darren1112.dptms.common.core.util.StringUtil;
import com.darren1112.dptms.common.security.starter.util.DptmsSecurityUtil;
import com.darren1112.dptms.common.spi.common.dto.ActiveUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 系统token校验器
 *
 * @author luyuhao
 * @date 2021/01/16 19:01
 */
public class DptmsTokenValidator {

    private DptmsTokenStore dptmsTokenStore;

    public DptmsTokenValidator(DptmsTokenStore dptmsTokenStore) {
        this.dptmsTokenStore = dptmsTokenStore;
    }

    /**
     * token校验处理
     *
     * @param request  请求域
     * @param response 响应域
     * @return true/false
     * @author luyuhao
     * @date 2020/11/28 10:47
     */
    public boolean tokenValidHandle(HttpServletRequest request, HttpServletResponse response) {
        String accessToken = dptmsTokenStore.getAccessToken(request);
        String refreshToken = dptmsTokenStore.getRefreshToken(request);
        if (StringUtil.isBlank(accessToken) || StringUtil.isBlank(refreshToken)) {
            return false;
        }
        boolean flag;
        // token存在，校验是否合法
        String redisRefreshToken = dptmsTokenStore.getRefreshToken(accessToken);
        ActiveUser activeUser = dptmsTokenStore.getActiveUser(refreshToken);
        if (StringUtil.isNotBlank(redisRefreshToken)
                && redisRefreshToken.equals(refreshToken)
                && Objects.nonNull(activeUser)) {
            // accessToken和refreshToken均有效 => 放行
            flag = true;
        } else if (StringUtil.isBlank(redisRefreshToken) && Objects.nonNull(activeUser)) {
            // accessToken无效, refreshToken有效 => 刷新accessToken，重置cookie
            dptmsTokenStore.refreshAccessTokenAndCookie(refreshToken, response);
            flag = true;
        } else {
            // accessToken和refreshToken均无效 => 打回
            // accessToken有效, refreshToken无效 => 打回
            dptmsTokenStore.removeTokenAndCookie(request, response);
            flag = false;
        }
        if (flag) {
            DptmsSecurityUtil.set(activeUser);
            request.setAttribute(SecurityConstant.ACCESS_TOKEN_KEY, accessToken);
            request.setAttribute(SecurityConstant.REFRESH_TOKEN_KEY, refreshToken);
        }
        return flag;
    }

    /**
     * 重复登录校验
     *
     * @param request  请求域
     * @param response 响应域
     * @return true/false
     * @author luyuhao
     * @date 2021/01/31 19:08
     */
    public boolean repeatLoginValidHandle(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = dptmsTokenStore.getRefreshToken(request);
        if (StringUtil.isBlank(refreshToken)) {
            return false;
        }
        ActiveUser activeUser = dptmsTokenStore.getActiveUser(refreshToken);
        String userRefreshToken = dptmsTokenStore.getUserRefreshToken(activeUser);
        if (StringUtil.isBlank(userRefreshToken) || !userRefreshToken.equals(refreshToken)) {
            // 当前用户被挤下线，删除当前的token
            dptmsTokenStore.removeTokenAndCookie(request, response);
            return false;
        } else {
            return true;
        }
    }
}
