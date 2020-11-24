package com.darren1112.dptms.common.security.starter.util;

import com.darren1112.dptms.common.core.constants.RedisConstant;
import com.darren1112.dptms.common.redis.starter.util.RedisUtil;
import com.darren1112.dptms.common.spi.common.entity.ActiveUser;
import lombok.AllArgsConstructor;

/**
 * token 工具类
 *
 * @author luyuhao
 * @date 2020/11/25 00:20
 */
@AllArgsConstructor
public class TokenUtil {

    private RedisUtil redisUtil;

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
        if(!redisUtil.set(RedisConstant.PREFIX + accessToken, refreshToken, accessTokenExpire)){
            return false;
        }
        // 设置refreshToken
        return redisUtil.set(RedisConstant.PREFIX + refreshToken, activeUser, refreshTokenExpire);
    }
}
