package com.darren1112.dptms.common.core.util;

import java.util.UUID;

/**
 * uuid工具类
 *
 * @author luyuhao
 * @since 2021/11/17
 */
public class UuidUtil {

    /**
     * uuid默认生成方法
     *
     * @return {@link String}
     * @author luyuhao
     * @since 2021/11/17
     */
    public static String generateDefault() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
