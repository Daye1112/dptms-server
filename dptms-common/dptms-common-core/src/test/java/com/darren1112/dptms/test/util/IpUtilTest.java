package com.darren1112.dptms.test.util;

import cn.hutool.core.io.resource.ClassPathResource;
import org.junit.Test;

import java.io.File;

/**
 * @author luyuhao
 * @date 2020/11/23 00:25
 */
public class IpUtilTest {

    @Test
    public void test01(){
        String path = "ip2region/ip2region.db";
        System.out.println(new ClassPathResource(path).getAbsolutePath());
        File file = new File("D:/Mydata/Major/java/develop/dptms/dptms-server/dptms-common/dptms-common-core/target/classes/ip2region/ip2region.db");
        System.out.println(file.getPath());
    }
}
