package com.darren1112.dptms.sdk.starter.redis.core;

import java.io.StringReader;
import java.util.Properties;

/**
 * @author luyuhao
 * @since 2021/8/4
 */
public class RedisInfoToPropertiesConvert {

    public static Properties convert(String source) throws Exception {
        if (source == null) {
            return null;
        } else {
            Properties info = new Properties();

            try (StringReader stringReader = new StringReader(source)) {
                info.load(stringReader);
            }
            return info;
        }
    }
}
