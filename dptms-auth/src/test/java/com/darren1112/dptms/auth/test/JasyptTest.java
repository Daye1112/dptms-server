package com.darren1112.dptms.auth.test;

import com.darren1112.dptms.common.core.util.EnvironmentAwareUtil;
import org.jasypt.encryption.StringEncryptor;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author luyuhao
 * @date 2020/07/22 01:22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class JasyptTest {
    @Autowired
    StringEncryptor encryptor;

    @BeforeClass
    public static void init() {
        EnvironmentAwareUtil.adjust();
    }

    @Test
    public void test01() {
        String str1 = "xxx";
        System.out.println("str1: " + encryptor.encrypt(str1));
    }
}
