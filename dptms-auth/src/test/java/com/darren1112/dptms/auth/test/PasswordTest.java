package com.darren1112.dptms.auth.test;

import cn.hutool.crypto.digest.MD5;
import com.darren1112.dptms.common.core.util.Md5Util;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author luyuhao
 * @since 2020/07/26 19:39
 */
public class PasswordTest {

    private PasswordEncoder passwordEncoder;

    @Before
    public void init(){
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Test
    public void test01(){
        String password = passwordEncoder.encode("123456");
        System.out.println(password);
    }

    @Test
    public void test02(){
        String s = new MD5().digestHex("123456");
        System.out.println(s);
    }

    @Test
    public void test03(){
        long l = System.currentTimeMillis();
        System.out.println(l);
        System.out.println(Md5Util.encrypt("123456", String.valueOf(l)));
    }
}
