package com.darren1112.dptms.sdk.starter.security.core.token.store;

import com.darren1112.dptms.common.core.util.RequestUtil;
import com.darren1112.dptms.common.core.util.StringUtil;
import com.darren1112.dptms.sdk.starter.redis.core.RedisUtil;
import com.darren1112.dptms.sdk.starter.security.base.model.BaseSecurityUser;
import com.darren1112.dptms.sdk.starter.security.core.token.generator.base.TokenGenerator;
import com.darren1112.dptms.sdk.starter.security.core.token.store.base.BaseTokenStore;
import com.darren1112.dptms.sdk.starter.security.properties.SecurityProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 系统token存储类
 *
 * @author luyuhao
 * @since 2021/01/16 19:06
 */
public class TokenStore extends BaseTokenStore {

    private TokenGenerator tokenGenerator;

    public TokenStore(RedisUtil redisUtil, SecurityProperties securityProperties, TokenGenerator tokenGenerator,
                      Class<? extends BaseSecurityUser> userClass) {
        super(redisUtil, securityProperties, userClass);
        this.tokenGenerator = tokenGenerator;
    }

    /**
     * 保存token
     *
     * @param activeUser   用户信息
     * @param accessToken  accessToken
     * @param refreshToken refreshToken
     * @author luyuhao
     * @since 20/11/25 00:25
     */
    private <T extends BaseSecurityUser> void saveToken(T activeUser, String accessToken, String refreshToken) {
        // 设置accessToken
        super.saveAccessToken(accessToken, refreshToken);
        // 设置refreshToken
        super.saveRefreshToken(refreshToken, activeUser);
        // 移除旧的refreshToken
        super.removeRefreshToken(super.getUserRefreshToken(activeUser));
        // 保存当前用户的最新refreshToken
        super.saveUserRefreshToken(activeUser, refreshToken);
    }

    /**
     * 获取用户信息
     *
     * @param request 请求域
     * @return {@link T 用户信息}
     * @author luyuhao
     * @since 20/11/28 01:22
     */
    public <T extends BaseSecurityUser> T getActiveUser(HttpServletRequest request) {
        return super.getActiveUser(request, getRefreshToken(request));
    }

    /**
     * 刷新access token并更新cookie
     *
     * @param refreshToken 刷新token
     * @param response     响应域
     * @author luyuhao
     * @since 2021/01/16 19:15
     */
    public void refreshAccessTokenAndCookie(String refreshToken, HttpServletResponse response) {
        String newAccessToken = tokenGenerator.generate(null);
        // 设置access token
        super.saveAccessToken(newAccessToken, refreshToken);
        // 设置access token cookie
        super.saveAccessTokenCookie(newAccessToken, response);
    }

    /**
     * 从request中获取accessToken
     *
     * @return {@link String accessToken}
     * @author luyuhao
     * @since 20/12/10 02:34
     */
    public String getAccessToken() {
        HttpServletRequest request = RequestUtil.getHttpServletRequest();
        return super.getAccessToken(request);
    }

    /**
     * 从request中获取refresh Token
     *
     * @return {@link String refresh Token}
     * @author luyuhao
     * @since 20/12/10 02:34
     */
    public String getRefreshToken() {
        HttpServletRequest request = RequestUtil.getHttpServletRequest();
        return super.getRefreshToken(request);
    }

    /**
     * 生成token
     *
     * @param activeUser 用户信息
     * @param response   响应域
     * @author luyuhao
     * @since 2021/01/17 01:11
     */
    public <T extends BaseSecurityUser> void generateToken(T activeUser, HttpServletResponse response) {
        // 生成token
        String accessToken = tokenGenerator.generate(null);
        String refreshToken = tokenGenerator.generate(null);
        // 保存到redis
        saveToken(activeUser, accessToken, refreshToken);
        // 保存到cookie
        saveTokenCookie(accessToken, refreshToken, response);
    }

    /**
     * 保存token cookie
     *
     * @param accessToken  access token
     * @param refreshToken refresh token
     * @param response     响应域
     * @author luyuhao
     * @since 2021/01/17 01:12
     */
    private void saveTokenCookie(String accessToken, String refreshToken, HttpServletResponse response) {
        super.saveAccessTokenCookie(accessToken, response);
        super.saveRefreshTokenCookie(refreshToken, response);
    }

    /**
     * 删除token
     *
     * @param request  请求域
     * @param response 响应域
     * @author luyuhao
     * @since 2021/01/28 01:03
     */
    public void removeTokenAndCookie(HttpServletRequest request, HttpServletResponse response) {
        String accessToken = super.getAccessToken(request);
        String refreshToken = super.getRefreshToken(request);
        removeUserRefreshToken(refreshToken);
        removeToken(accessToken, refreshToken);
        removeTokenCookie(response);
    }

    /**
     * 删除cookie中的token
     *
     * @param response 响应域
     * @author luyuhao
     * @since 2021/01/28 01:07
     */
    private void removeTokenCookie(HttpServletResponse response) {
        super.removeAccessTokenCookie(response);
        super.removeRefreshTokenCookie(response);
    }

    /**
     * 删除token
     *
     * @param accessToken  accessToken
     * @param refreshToken refreshToken
     * @author luyuhao
     * @since 2021/01/28 01:05
     */
    private void removeToken(String accessToken, String refreshToken) {
        super.removeAccessToken(accessToken);
        super.removeRefreshToken(refreshToken);
    }

    /**
     * 更新用户信息
     *
     * @param activeUser 用户信息
     * @author luyuhao
     * @since 2021/01/31 20:00
     */
    public <T extends BaseSecurityUser> void updateActiveUser(T activeUser) {
        String refreshToken = getRefreshToken();
        super.updateActiveUser(activeUser, refreshToken);
    }

    /**
     * 根据refreshToken移除ActiveUser
     *
     * @param refreshToken 刷新token
     * @author luyuhao
     * @since 2021/07/28
     */
    private <T extends BaseSecurityUser> void removeUserRefreshToken(String refreshToken) {
        if (StringUtil.isNotBlank(refreshToken)) {
            T activeUser = super.getActiveUser(refreshToken);
            if (activeUser != null) {
                super.removeUserRefreshToken(activeUser);
            }
        }
    }
}
