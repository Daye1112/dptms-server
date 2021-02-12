package com.darren1112.dptms.test;

import com.darren1112.dptms.common.core.util.JasyptUtil;
import org.junit.Test;

/**
 * @author luyuhao
 * @date 2020/07/22 01:22
 */
public class JasyptTest {

    @Test
    public void test01() {
        String secret = "xxx";
        String str1 = "yyy";

        System.out.println(str1 + ": " + JasyptUtil.defaultEncrypt(str1, secret));
    }

    @Test
    public void test02() {
        String secret = "xxx";
        String str1 = "yyy";

        System.out.println(str1 + ": " + JasyptUtil.defaultDecrypt(str1, secret));
    }
}
