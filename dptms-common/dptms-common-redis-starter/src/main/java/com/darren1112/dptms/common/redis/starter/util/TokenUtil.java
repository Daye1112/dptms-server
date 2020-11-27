package com.darren1112.dptms.common.redis.starter.util;

import com.darren1112.dptms.common.core.constants.RedisConstant;
import com.darren1112.dptms.common.core.util.JsonUtil;
import com.darren1112.dptms.common.spi.common.entity.ActiveUser;

import java.util.Optional;

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
     * @param accessTokenExpire  accessToken过期时间
     * @param refreshTokenExpire refreshToken过期时间
     * @author luyuhao
     * @date 20/11/25 00:25
     */
    public boolean saveToken(ActiveUser activeUser, long accessTokenExpire, long refreshTokenExpire) {
        String accessToken = activeUser.getAccessToken();
        String refreshToken = activeUser.getRefreshToken();
        // 设置accessToken
        if (!redisUtil.set(RedisConstant.PREFIX + accessToken, refreshToken, accessTokenExpire)) {
            return false;
        }
        // 设置refreshToken
        return redisUtil.set(RedisConstant.PREFIX + refreshToken, JsonUtil.toJsonString(activeUser), refreshTokenExpire);
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
        return Optional.ofNullable(redisUtil.get(RedisConstant.PREFIX + accessToken)).map(Object::toString).orElse(null);
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
        return Optional.ofNullable(redisUtil.get(RedisConstant.PREFIX + refreshToken))
                .map(Object::toString)
                .map(item -> JsonUtil.parseObject(item, ActiveUser.class))
                .orElse(null);
    }
}
