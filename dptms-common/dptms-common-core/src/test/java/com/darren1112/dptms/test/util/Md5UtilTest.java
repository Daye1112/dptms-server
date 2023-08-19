package com.darren1112.dptms.test.util;

import com.darren1112.dptms.common.core.util.Md5Util;
import org.junit.Test;

/**
 * @author darren
 * @since 2020/11/23 00:37
 */
public class Md5UtilTest {

    @Test
    public void test01() {
        String salt = String.valueOf(System.currentTimeMillis());
        String encrypt = Md5Util.encrypt("123456", salt);
        System.out.println(salt);
        System.out.println(encrypt);
    }

    @Test
    public void test02() {
        String salt = "123456789";
        String encrypt1 = Md5Util.encrypt("admin", salt);
        String encrypt2 = Md5Util.encrypt(salt + "admin", "");
        System.out.println(encrypt1);
        System.out.println(encrypt2);
    }
}
