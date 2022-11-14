package com.darren1112.dptms.sdk.starter.security.core;

import com.darren1112.dptms.common.core.util.UuidUtil;

/**
 * token生成类
 *
 * @author luyuhao
 * @since 2021/01/16 17:10
 */
public class DptmsTokenGenerator {

    /**
     * 生成默认token
     *
     * @return {@link String token}
     * @author luyuhao
     * @since 2021/01/16 17:11
     */
    public static String generateDefaultToken() {
        return UuidUtil.generateDefault();
    }
}
