package com.darren1112.dptms.common.core.util;

import java.io.ByteArrayInputStream;
import java.util.Properties;

/**
 * properties工具类
 *
 * @author luyuhao
 * @since 2021/7/21
 */
public class PropertiesUtil {

    /**
     * 根据字符串创建properties
     *
     * @param str 字符串
     * @return {@link Properties}
     * @author luyuhao
     * @since 2021/7/21
     */
    public static Properties buildPropertiesByStr(String str) {
        Properties properties = new Properties();
        if (StringUtil.isBlank(str)) {
            return properties;
        }
        ByteArrayInputStream inputStream = null;
        try {
            inputStream = new ByteArrayInputStream(str.getBytes());
            properties.load(inputStream);
            return properties;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
