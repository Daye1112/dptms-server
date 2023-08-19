package com.darren1112.dptms.auth.test;

import org.junit.Test;

import java.util.UUID;

/**
 * @author darren
 * @since 2020/11/23 00:04
 */
public class RandomTest {

    @Test
    public void test01() {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
        System.out.println(uuid.getLeastSignificantBits());
        System.out.println(uuid.getMostSignificantBits());
        System.out.println(uuid.toString());
        System.out.println(uuid.toString().replaceAll("-", ""));
    }
}
