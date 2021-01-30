package com.darren1112.dptms.common.core.constants;

/**
 * 鉴权相关常量
 *
 * @author luyuhao
 * @date 2020/11/26 22:51
 */
public class SecurityConstant {

    /**
     * access token交互的key
     */
    public static final String ACCESS_TOKEN_KEY = "DptmsAccessToken";
    /**
     * refresh token交互的key
     */
    public static final String REFRESH_TOKEN_KEY = "DptmsRefreshToken";
    /**
     * access token redis key prefix
     */
    public static final String REDIS_ACCESS_TOKEN_PREFIX = "accessToken:";
    /**
     * refresh token redis key prefix
     */
    public static final String REDIS_REFRESH_TOKEN_PREFIX = "refreshToken:";
    /**
     * user refresh token redis key prefix
     */
    public static final String REDIS_USER_REFRESH_TOKEN_PREFIX = "activeUser:";

}
