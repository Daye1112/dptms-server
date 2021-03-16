package com.darren1112.dptms.common.security.starter.core;

import java.util.UUID;

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
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
