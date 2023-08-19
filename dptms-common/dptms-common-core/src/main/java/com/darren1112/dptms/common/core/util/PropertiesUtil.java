package com.darren1112.dptms.common.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * properties工具类
 *
 * @author darren
 * @since 2021/7/21
 */
public class PropertiesUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesUtil.class);

    /**
     * 根据字符串创建properties
     *
     * @param str 字符串
     * @return {@link Properties}
     * @author darren
     * @since 2021/7/21
     */
    public static Properties buildPropertiesByStr(String str) {
        Properties properties = new Properties();
        if (StringUtil.isBlank(str)) {
            return properties;
        }
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        try {
            inputStream = new ByteArrayInputStream(str.getBytes());
            inputStreamReader = new InputStreamReader(inputStream);
            properties.load(inputStreamReader);
            return properties;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    LOGGER.error("流关闭异常", e);
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    LOGGER.error("流关闭异常", e);
                }
            }
        }
    }
}
