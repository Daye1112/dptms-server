package com.darren1112.dptms.common.security.starter.util;

import com.darren1112.dptms.common.core.constants.SecurityConstant;
import com.darren1112.dptms.common.core.util.CookieUtil;
import com.darren1112.dptms.common.core.util.JsonUtil;
import com.darren1112.dptms.common.core.util.RequestUtil;
import com.darren1112.dptms.common.core.util.StringUtil;
import com.darren1112.dptms.common.redis.starter.util.RedisUtil;
import com.darren1112.dptms.common.spi.common.dto.ActiveUser;

import java.util.Optional;
import java.util.UUID;

/**
 * token 工具类
 *
 * @author luyuhao
 * @date 2020/11/25 00:20
 */
public class TokenUtil {

    private RedisUtil redisUtil;

    public TokenUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    /**
     * 保存token
     *
     * @param activeUser         用户信息
     * @param accessToken        accessToken
     * @param refreshToken       refreshToken
     * @param accessTokenExpire  accessToken过期时间
     * @param refreshTokenExpire refreshToken过期时间
     * @author luyuhao
     * @date 20/11/25 00:25
     */
    public boolean saveToken(ActiveUser activeUser, String accessToken, String refreshToken, long accessTokenExpire, long refreshTokenExpire) {
        try {
            // 设置accessToken
            redisUtil.set(SecurityConstant.REDIS_ACCESS_TOKEN_PREFIX + accessToken, refreshToken, accessTokenExpire);
            // 设置refreshToken
            redisUtil.set(SecurityConstant.REDIS_REFRESH_TOKEN_PREFIX + refreshToken, JsonUtil.toJsonString(activeUser), refreshTokenExpire);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取刷新token
     *
     * @param accessToken 访问token
     * @return 刷新token
     * @author luyuhao
     * @date 20/11/27 00:45
     */
    public String getRefreshToken(String accessToken) {
        return Optional.ofNullable(redisUtil.get(SecurityConstant.REDIS_ACCESS_TOKEN_PREFIX + accessToken))
                .map(Object::toString).orElse(null);
    }

    /**
     * 获取用户信息
     *
     * @param refreshToken refreshToken
     * @return {@link ActiveUser 用户信息}
     * @author luyuhao
     * @date 20/11/28 01:22
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
     * @return {@link ActiveUser 用户信息}
     * @author luyuhao
     * @date 20/11/28 01:22
     */
    public ActiveUser getActiveUser() {
        String refreshToken = getRefreshAccessTokenFromRequest();
        return Optional.ofNullable(redisUtil.get(SecurityConstant.REDIS_REFRESH_TOKEN_PREFIX + refreshToken))
                .map(Object::toString)
                .map(item -> JsonUtil.parseObject(item, ActiveUser.class))
                .orElse(null);
    }

    /**
     * 刷新accessToken
     *
     * @param refreshToken      refreshToken
     * @param accessTokenExpire accessToken过期时间
     * @return new accessToken
     * @author luyuhao
     * @date 2020/11/28 10:50
     */
    public String refreshAccessToken(String refreshToken, long accessTokenExpire) {
        String newAccessToken = UUID.randomUUID().toString().replaceAll("-", "");
        redisUtil.set(SecurityConstant.REDIS_ACCESS_TOKEN_PREFIX + newAccessToken, refreshToken, accessTokenExpire);
        return newAccessToken;
    }

    /**
     * 从request中获取accessToken
     *
     * @return {@link String accessToken}
     * @author luyuhao
     * @date 20/12/10 02:34
     */
    public String getAccessTokenFromRequest() {
        String accessToken = CookieUtil.getCookie(SecurityConstant.ACCESS_TOKEN_KEY, RequestUtil.getHttpServletRequest());
        if (StringUtil.isBlank(accessToken)) {
            accessToken = RequestUtil.getHeaderByName(SecurityConstant.ACCESS_TOKEN_KEY);
        }
        return accessToken;
    }

    /**
     * 从request中获取refreshAccessToken
     *
     * @return {@link String refreshAccessToken}
     * @author luyuhao
     * @date 20/12/10 02:34
     */
    public String getRefreshAccessTokenFromRequest() {
        String refreshAccessToken = CookieUtil.getCookie(SecurityConstant.REFRESH_TOKEN_KEY, RequestUtil.getHttpServletRequest());
        if (StringUtil.isBlank(refreshAccessToken)) {
            refreshAccessToken = RequestUtil.getHeaderByName(SecurityConstant.REFRESH_TOKEN_KEY);
        }
        return refreshAccessToken;
    }
}
