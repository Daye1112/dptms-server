package com.darren1112.dptms.test.util;

import com.darren1112.dptms.common.core.util.EncodeUtil;
import com.darren1112.dptms.common.core.util.PropertiesUtil;
import org.junit.Test;

import java.util.Properties;
import java.util.Set;

/**
 * 编码工具测试类
 *
 * @author luyuhao
 * @since 2021/7/23
 */
public class EncodeUtilTest {

    @Test
    public void test01() {
        String text = EncodeUtil.decodeUrl("%E6%88%91%3D%E4%BD%A0%0Ak1%3D%E4%BB%96");
        System.out.println(text);
        System.out.println("===================");
        Properties properties = PropertiesUtil.buildPropertiesByStr(text);
        Set<String> keys = properties.stringPropertyNames();
        for (String key : keys) {
            System.out.println(key + "->" + properties.getProperty(key));
        }
    }
}
