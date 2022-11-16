package com.darren1112.dptms.sdk.starter.security.core.token.validator;

import com.darren1112.dptms.common.core.constants.SecurityConstant;
import com.darren1112.dptms.common.core.util.StringUtil;
import com.darren1112.dptms.sdk.starter.security.base.model.BaseSecurityUser;
import com.darren1112.dptms.sdk.starter.security.core.token.store.TokenStore;
import com.darren1112.dptms.sdk.starter.security.core.token.validator.base.TokenValidator;
import com.darren1112.dptms.sdk.starter.security.model.ActiveUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 系统token校验器
 *
 * @author luyuhao
 * @since 2021/01/16 19:01
 */
public class BasicTokenValidator implements TokenValidator {

    private TokenStore tokenStore;

    public BasicTokenValidator(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    /**
     * token校验处理
     *
     * @param request  请求域
     * @param response 响应域
     * @return true/false
     * @author luyuhao
     * @since 2020/11/28 10:47
     */
    @Override
    public boolean doValidate(HttpServletRequest request, HttpServletResponse response) {
        String accessToken = tokenStore.getAccessToken(request);
        String refreshToken = tokenStore.getRefreshToken(request);
        if (StringUtil.isBlank(accessToken) || StringUtil.isBlank(refreshToken)) {
            return false;
        }
        boolean flag;
        // token存在，校验是否合法
        String redisRefreshToken = tokenStore.getRefreshToken(accessToken);
        BaseSecurityUser activeUser = tokenStore.getActiveUser(refreshToken);
        if (StringUtil.isNotBlank(redisRefreshToken)
                && redisRefreshToken.equals(refreshToken)
                && Objects.nonNull(activeUser)) {
            // accessToken和refreshToken均有效 => 放行
            flag = true;
        } else if (StringUtil.isBlank(redisRefreshToken) && Objects.nonNull(activeUser)) {
            // accessToken无效, refreshToken有效 => 刷新accessToken，重置cookie
            tokenStore.refreshAccessTokenAndCookie(refreshToken, response);
            flag = true;
        } else {
            // accessToken和refreshToken均无效 => 打回
            // accessToken有效, refreshToken无效 => 打回
            tokenStore.removeTokenAndCookie(request, response);
            flag = false;
        }
        if (flag) {
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
     * @since 2021/01/31 19:08
     */
    public boolean repeatLoginValidHandle(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = tokenStore.getRefreshToken(request);
        if (StringUtil.isBlank(refreshToken)) {
            return false;
        }
        ActiveUser activeUser = tokenStore.getActiveUser(refreshToken);
        String userRefreshToken = tokenStore.getUserRefreshToken(activeUser);
        if (StringUtil.isBlank(userRefreshToken) || !userRefreshToken.equals(refreshToken)) {
            // 当前用户被挤下线，删除当前的token
            tokenStore.removeTokenAndCookie(request, response);
            return false;
        } else {
            return true;
        }
    }
}
