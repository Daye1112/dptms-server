package com.darren1112.dptms.auth.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author luyuhao
 * @date 2020/07/26 19:39
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
}
