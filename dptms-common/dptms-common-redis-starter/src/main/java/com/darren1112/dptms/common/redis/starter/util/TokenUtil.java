package com.darren1112.dptms.common.redis.starter.util;

import com.darren1112.dptms.common.core.util.JsonUtil;
import com.darren1112.dptms.common.spi.common.entity.ActiveUser;

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
            redisUtil.set(accessToken, refreshToken, accessTokenExpire);
            // 设置refreshToken
            redisUtil.set(refreshToken, JsonUtil.toJsonString(activeUser), refreshTokenExpire);
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
        return Optional.ofNullable(redisUtil.get(accessToken)).map(Object::toString).orElse(null);
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
        return Optional.ofNullable(redisUtil.get(refreshToken))
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
        redisUtil.set(newAccessToken, refreshToken, accessTokenExpire);
        return newAccessToken;
    }
}
