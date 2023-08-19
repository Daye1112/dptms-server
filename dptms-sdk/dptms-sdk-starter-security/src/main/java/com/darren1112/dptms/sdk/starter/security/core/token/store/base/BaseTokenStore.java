package com.darren1112.dptms.sdk.starter.security.core.token.store.base;

import com.darren1112.dptms.common.core.constants.SecurityConstant;
import com.darren1112.dptms.common.core.util.CookieUtil;
import com.darren1112.dptms.common.core.util.JsonUtil;
import com.darren1112.dptms.common.core.util.RequestUtil;
import com.darren1112.dptms.common.core.util.StringUtil;
import com.darren1112.dptms.sdk.starter.redis.core.RedisUtil;
import com.darren1112.dptms.sdk.starter.security.base.model.BaseSecurityUser;
import com.darren1112.dptms.sdk.starter.security.properties.SecurityProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 系统token存储-基础类
 *
 * @author darren
 * @since 2021/8/3
 */
public abstract class BaseTokenStore {

    private Class<? extends BaseSecurityUser> userClass;

    private RedisUtil redisUtil;

    private SecurityProperties securityProperties;

    public BaseTokenStore(RedisUtil redisUtil, SecurityProperties securityProperties,
                          Class<? extends BaseSecurityUser> userClass) {
        this.redisUtil = redisUtil;
        this.securityProperties = securityProperties;
        this.userClass = userClass;
    }

    /**
     * 保存access token
     *
     * @param accessToken  accessToken
     * @param refreshToken refreshToken
     * @author darren
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
     * @author darren
     * @since 20/11/25 00:25
     */
    public <T extends BaseSecurityUser> void saveRefreshToken(String refreshToken, T activeUser) {
        // 设置refreshToken
        redisUtil.set(SecurityConstant.REDIS_REFRESH_TOKEN_PREFIX + refreshToken, JsonUtil.toJsonString(activeUser), securityProperties.getRefreshTokenExpired());
    }

    /**
     * 保存当前用户的最新refreshToken
     *
     * @param activeUser   用户信息
     * @param refreshToken refreshToken
     * @author darren
     * @since 2021/01/30 23:36
     */
    public <T extends BaseSecurityUser> void saveUserRefreshToken(T activeUser, String refreshToken) {
        // 设置当前用户的最新refreshToken
        redisUtil.set(SecurityConstant.REDIS_USER_REFRESH_TOKEN_PREFIX + activeUser.getUserId(), refreshToken, securityProperties.getRefreshTokenExpired());
    }

    /**
     * 获取当前用户的最新refreshToken
     *
     * @param activeUser 用户信息
     * @author darren
     * @since 2021/01/30 23:36
     */
    public <T extends BaseSecurityUser> String getUserRefreshToken(T activeUser) {
        // 设置当前用户的最新refreshToken
        return Optional.ofNullable(redisUtil.get(SecurityConstant.REDIS_USER_REFRESH_TOKEN_PREFIX + activeUser.getUserId())).map(Object::toString).orElse(null);
    }

    /**
     * 获取用户信息
     *
     * @param refreshToken refreshToken
     * @return {@link BaseSecurityUser 用户信息}
     * @author darren
     * @since 20/11/28 01:22
     */
    @SuppressWarnings("unchecked")
    public <T extends BaseSecurityUser> T getActiveUser(String refreshToken) {
        return Optional.ofNullable(redisUtil.get(SecurityConstant.REDIS_REFRESH_TOKEN_PREFIX + refreshToken))
                .map(Object::toString)
                .map(item -> (T) JsonUtil.parseObject(item, userClass))
                .orElse(null);
    }

    /**
     * 获取用户信息
     *
     * @param request      请求域
     * @param refreshToken 刷新token
     * @return {@link BaseSecurityUser 用户信息}
     * @author darren
     * @since 20/11/28 01:22
     */
    @SuppressWarnings("unchecked")
    public <T extends BaseSecurityUser> T getActiveUser(HttpServletRequest request, String refreshToken) {
        return Optional.ofNullable(redisUtil.get(SecurityConstant.REDIS_REFRESH_TOKEN_PREFIX + refreshToken))
                .map(Object::toString)
                .map(item -> (T) JsonUtil.parseObject(item, userClass))
                .orElse(null);
    }

    /**
     * 保存access token cookie
     *
     * @param accessToken accessToken
     * @param response    响应域
     * @author darren
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
     * @author darren
     * @since 20/11/25 00:25
     */
    public void saveRefreshTokenCookie(String refreshToken, HttpServletResponse response) {
        // 设置refreshToken
        CookieUtil.setCookie(SecurityConstant.REFRESH_TOKEN_KEY, refreshToken, response);
    }

    /**
     * 从request中获取accessToken
     *
     * @param request 请求域
     * @return {@link String accessToken}
     * @author darren
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
     * @param request 请求域
     * @return {@link String refresh Token}
     * @author darren
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
     * @author darren
     * @since 20/11/27 00:45
     */
    public String getRefreshToken(String accessToken) {
        return Optional.ofNullable(redisUtil.get(SecurityConstant.REDIS_ACCESS_TOKEN_PREFIX + accessToken))
                .map(Object::toString).orElse(null);
    }

    /**
     * 删除cookie中的refreshToken
     *
     * @param response 响应域
     * @author darren
     * @since 2021/01/28 01:08
     */
    public void removeRefreshTokenCookie(HttpServletResponse response) {
        CookieUtil.deleteCookie(SecurityConstant.REFRESH_TOKEN_KEY, response);
    }

    /**
     * 删除cookie中的accessToken
     *
     * @param response 响应域
     * @author darren
     * @since 2021/01/28 01:08
     */
    public void removeAccessTokenCookie(HttpServletResponse response) {
        CookieUtil.deleteCookie(SecurityConstant.ACCESS_TOKEN_KEY, response);
    }

    /**
     * 删除refreshToken
     *
     * @param refreshToken refreshToken
     * @author darren
     * @since 2021/01/28 01:06
     */
    public void removeRefreshToken(String refreshToken) {
        if (StringUtil.isNotBlank(refreshToken)) {
            redisUtil.removeKey(SecurityConstant.REDIS_REFRESH_TOKEN_PREFIX + refreshToken);
        }
    }

    /**
     * 删除accessToken
     *
     * @param accessToken accessToken
     * @author darren
     * @since 2021/01/28 01:06
     */
    public void removeAccessToken(String accessToken) {
        if (StringUtil.isNotBlank(accessToken)) {
            redisUtil.removeKey(SecurityConstant.REDIS_ACCESS_TOKEN_PREFIX + accessToken);
        }
    }

    /**
     * 更新用户信息
     *
     * @param activeUser   用户信息
     * @param refreshToken 刷新token
     * @author darren
     * @since 2021/01/31 20:00
     */
    public <T extends BaseSecurityUser> void updateActiveUser(T activeUser, String refreshToken) {
        long expired = redisUtil.ttl(SecurityConstant.REDIS_REFRESH_TOKEN_PREFIX + refreshToken);
        if (expired > 0) {
            // 设置refreshToken
            redisUtil.set(SecurityConstant.REDIS_REFRESH_TOKEN_PREFIX + refreshToken, JsonUtil.toJsonString(activeUser), (int) expired);
        }
    }

    /**
     * 查询所有用户refreshToken的keys
     *
     * @return {@link String}
     * @author darren
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
     * @author darren
     * @since 2021/01/31 23:22
     */
    public <T extends BaseSecurityUser> void removeUserRefreshToken(T activeUser) {
        redisUtil.removeKey(SecurityConstant.REDIS_USER_REFRESH_TOKEN_PREFIX + activeUser.getUserId());
    }

}
