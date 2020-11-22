package com.darren1112.dptms.test.util;

import com.darren1112.dptms.common.core.util.Md5Util;
import org.junit.Test;

/**
 * @author luyuhao
 * @date 2020/11/23 00:37
 */
public class Md5UtilTest {

    @Test
    public void test01(){
        String salt = String.valueOf(System.currentTimeMillis());
        String encrypt = Md5Util.encrypt("123456", salt);
        System.out.println(salt);
        System.out.println(encrypt);
    }
}
