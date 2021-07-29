package com.darren1112.dptms.common.security.starter.core;

import com.darren1112.dptms.common.core.constants.SecurityConstant;
import com.darren1112.dptms.common.core.util.CookieUtil;
import com.darren1112.dptms.common.core.util.JsonUtil;
import com.darren1112.dptms.common.core.util.RequestUtil;
import com.darren1112.dptms.common.core.util.StringUtil;
import com.darren1112.dptms.common.redis.starter.util.RedisUtil;
import com.darren1112.dptms.common.security.starter.properties.SecurityProperties;
import com.darren1112.dptms.common.spi.common.dto.ActiveUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 系统token存储类
 *
 * @author luyuhao
 * @since 2021/01/16 19:06
 */
public class DptmsTokenStore {

    private RedisUtil redisUtil;

    private SecurityProperties securityProperties;

    public DptmsTokenStore(RedisUtil redisUtil, SecurityProperties securityProperties) {
        this.redisUtil = redisUtil;
        this.securityProperties = securityProperties;
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
    public void saveToken(ActiveUser activeUser, String accessToken, String refreshToken) {
        // 设置accessToken
        saveAccessToken(accessToken, refreshToken);
        // 设置refreshToken
        saveRefreshToken(refreshToken, activeUser);
        // 移除旧的refreshToken
        removeRefreshToken(getUserRefreshToken(activeUser));
        // 保存当前用户的最新refreshToken
        saveUserRefreshToken(activeUser, refreshToken);
    }

    /**
     * 保存access token
     *
     * @param accessToken  accessToken
     * @param refreshToken refreshToken
     * @author luyuhao
     * @since 20/11/25 00:25
     */
    public void saveAccessToken(String accessToken, String refreshToken) {
        // 设置accessToken
        redisUtil.set(SecurityConstant.REDIS_ACCESS_TOKEN_PREFIX + accessToken, refreshToken, securityProperties.getAccessTokenExpired());
    }

    /**
     * 保存refresh token
     *
     * @param refreshToken refreshToken
     * @param activeUser   用户信息
     * @author luyuhao
     * @since 20/11/25 00:25
     */
    public void saveRefreshToken(String refreshToken, ActiveUser activeUser) {
        // 设置refreshToken
        redisUtil.set(SecurityConstant.REDIS_REFRESH_TOKEN_PREFIX + refreshToken, JsonUtil.toJsonString(activeUser), securityProperties.getRefreshTokenExpired());
    }

    /**
     * 保存当前用户的最新refreshToken
     *
     * @param activeUser   用户信息
     * @param refreshToken refreshToken
     * @author luyuhao
     * @since 2021/01/30 23:36
     */
    private void saveUserRefreshToken(ActiveUser activeUser, String refreshToken) {
        // 设置当前用户的最新refreshToken
        redisUtil.set(SecurityConstant.REDIS_USER_REFRESH_TOKEN_PREFIX + activeUser.getId(), refreshToken, securityProperties.getRefreshTokenExpired());
    }

    /**
     * 获取当前用户的最新refreshToken
     *
     * @param activeUser 用户信息
     * @author luyuhao
     * @since 2021/01/30 23:36
     */
    public String getUserRefreshToken(ActiveUser activeUser) {
        // 设置当前用户的最新refreshToken
        return Optional.ofNullable(redisUtil.get(SecurityConstant.REDIS_USER_REFRESH_TOKEN_PREFIX + activeUser.getId())).map(Object::toString).orElse(null);
    }

    /**
     * 获取用户信息
     *
     * @param refreshToken refreshToken
     * @return {@link ActiveUser 用户信息}
     * @author luyuhao
     * @since 20/11/28 01:22
     */
    public ActiveUser getActiveUser(String refreshToken) {
        return Optional.ofNullable(redisUtil.get(SecurityConstant.REDIS_REFRESH_TOKEN_PREFIX + refreshToken))
                .map(Object::toString)
                .map(item -> JsonUtil.parseObject(item, ActiveUser.class))
                .orElse(null);
    }

    /**
     * 获取用户信息
     *
     * @param request 请求域
     * @return {@link ActiveUser 用户信息}
     * @author luyuhao
     * @since 20/11/28 01:22
     */
    public ActiveUser getActiveUser(HttpServletRequest request) {
        String refreshToken = getRefreshToken(request);
        return Optional.ofNullable(redisUtil.get(SecurityConstant.REDIS_REFRESH_TOKEN_PREFIX + refreshToken))
                .map(Object::toString)
                .map(item -> JsonUtil.parseObject(item, ActiveUser.class))
                .orElse(null);
    }

    /**
     * 保存access token cookie
     *
     * @param accessToken accessToken
     * @param response    响应域
     * @author luyuhao
     * @since 20/11/25 00:25
     */
    public void saveAccessTokenCookie(String accessToken, HttpServletResponse response) {
        // 设置accessToken cookie
        CookieUtil.setCookie(SecurityConstant.ACCESS_TOKEN_KEY, accessToken, response);
    }

    /**
     * 保存refresh token cookie
     *
     * @param refreshToken refreshToken
     * @param response     响应域
     * @author luyuhao
     * @since 20/11/25 00:25
     */
    public void saveRefreshTokenCookie(String refreshToken, HttpServletResponse response) {
        // 设置refreshToken
        CookieUtil.setCookie(SecurityConstant.REFRESH_TOKEN_KEY, refreshToken, response);
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
        String newAccessToken = DptmsTokenGenerator.generateDefaultToken();
        // 设置access token
        saveAccessToken(newAccessToken, refreshToken);
        // 设置access token cookie
        saveAccessTokenCookie(newAccessToken, response);
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
        return getAccessToken(request);
    }

    /**
     * 从request中获取accessToken
     *
     * @param request 请求域
     * @return {@link String accessToken}
     * @author luyuhao
     * @since 20/12/10 02:34
     */
    public String getAccessToken(HttpServletRequest request) {
        String accessToken = CookieUtil.getCookie(SecurityConstant.ACCESS_TOKEN_KEY, request);
        if (StringUtil.isBlank(accessToken)) {
            accessToken = RequestUtil.getHeaderByName(SecurityConstant.ACCESS_TOKEN_KEY);
        }
        return accessToken;
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
        return getRefreshToken(request);
    }

    /**
     * 从request中获取refresh Token
     *
     * @param request 请求域
     * @return {@link String refresh Token}
     * @author luyuhao
     * @since 20/12/10 02:34
     */
    public String getRefreshToken(HttpServletRequest request) {
        String refreshToken = CookieUtil.getCookie(SecurityConstant.REFRESH_TOKEN_KEY, request);
        if (StringUtil.isBlank(refreshToken)) {
            refreshToken = RequestUtil.getHeaderByName(SecurityConstant.REFRESH_TOKEN_KEY);
        }
        return refreshToken;
    }

    /**
     * 获取刷新token
     *
     * @param accessToken 访问token
     * @return 刷新token
     * @author luyuhao
     * @since 20/11/27 00:45
     */
    public String getRefreshToken(String accessToken) {
        return Optional.ofNullable(redisUtil.get(SecurityConstant.REDIS_ACCESS_TOKEN_PREFIX + accessToken))
                .map(Object::toString).orElse(null);
    }

    /**
     * 生成token
     *
     * @param activeUser 用户信息
     * @param response   响应域
     * @author luyuhao
     * @since 2021/01/17 01:11
     */
    public void generateToken(ActiveUser activeUser, HttpServletResponse response) {
        // 生成token
        String accessToken = DptmsTokenGenerator.generateDefaultToken();
        String refreshToken = DptmsTokenGenerator.generateDefaultToken();
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
        saveAccessTokenCookie(accessToken, response);
        saveRefreshTokenCookie(refreshToken, response);
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
        String accessToken = getAccessToken(request);
        String refreshToken = getRefreshToken(request);
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
        removeAccessTokenCookie(response);
        removeRefreshTokenCookie(response);
    }

    /**
     * 删除cookie中的refreshToken
     *
     * @param response 响应域
     * @author luyuhao
     * @since 2021/01/28 01:08
     */
    private void removeRefreshTokenCookie(HttpServletResponse response) {
        CookieUtil.deleteCookie(SecurityConstant.REFRESH_TOKEN_KEY, response);
    }

    /**
     * 删除cookie中的accessToken
     *
     * @param response 响应域
     * @author luyuhao
     * @since 2021/01/28 01:08
     */
    private void removeAccessTokenCookie(HttpServletResponse response) {
        CookieUtil.deleteCookie(SecurityConstant.ACCESS_TOKEN_KEY, response);
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
        removeAccessToken(accessToken);
        removeRefreshToken(refreshToken);
    }

    /**
     * 删除refreshToken
     *
     * @param refreshToken refreshToken
     * @author luyuhao
     * @since 2021/01/28 01:06
     */
    public void removeRefreshToken(String refreshToken) {
        if (StringUtil.isNotBlank(refreshToken)) {
            redisUtil.delete(SecurityConstant.REDIS_REFRESH_TOKEN_PREFIX + refreshToken);
        }
    }

    /**
     * 删除accessToken
     *
     * @param accessToken accessToken
     * @author luyuhao
     * @since 2021/01/28 01:06
     */
    private void removeAccessToken(String accessToken) {
        if (StringUtil.isNotBlank(accessToken)) {
            redisUtil.delete(SecurityConstant.REDIS_ACCESS_TOKEN_PREFIX + accessToken);
        }
    }

    /**
     * 更新用户信息
     *
     * @param activeUser 用户信息
     * @author luyuhao
     * @since 2021/01/31 20:00
     */
    public void updateActiveUser(ActiveUser activeUser) {
        String refreshToken = getRefreshToken();
        long expired = redisUtil.getExpire(SecurityConstant.REDIS_REFRESH_TOKEN_PREFIX + refreshToken);
        if (expired > 0) {
            // 设置refreshToken
            redisUtil.set(SecurityConstant.REDIS_REFRESH_TOKEN_PREFIX + refreshToken, JsonUtil.toJsonString(activeUser), expired);
        }
    }

    /**
     * 查询所有用户refreshToken的keys
     *
     * @return {@link String}
     * @author luyuhao
     * @since 2021/01/31 22:47
     */
    public List<String> listAllUserRefreshKeys() {
        Set<String> keys = redisUtil.getKeys(SecurityConstant.REDIS_USER_REFRESH_TOKEN_PREFIX);
        return new ArrayList<>(keys);
    }

    /**
     * 移除userRefreshToken
     *
     * @param activeUser 用户信息
     * @author luyuhao
     * @since 2021/01/31 23:22
     */
    public void removeUserRefreshToken(ActiveUser activeUser) {
        redisUtil.delete(SecurityConstant.REDIS_USER_REFRESH_TOKEN_PREFIX + activeUser.getId());
    }

    /**
     * 根据refreshToken移除ActiveUser
     *
     * @param refreshToken 刷新token
     * @author luyuhao
     * @since 2021/07/28
     */
    private void removeUserRefreshToken(String refreshToken) {
        if (StringUtil.isNotBlank(refreshToken)) {
            ActiveUser activeUser = getActiveUser(refreshToken);
            if (activeUser != null) {
                removeUserRefreshToken(activeUser);
            }
        }
    }
}
