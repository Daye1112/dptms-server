package com.darren1112.dptms.common.core.util;

import cn.hutool.crypto.digest.MD5;

/**
 * MD5工具类
 *
 * @author luyuhao
 * @date 2020/11/22 23:14
 */
public class Md5Util {

    /**
     * 带盐加密
     *
     * @param data 加密串
     * @param salt 盐
     * @return {@link String 加密结果}
     * @author luyuhao
     * @date 20/11/22 23:15
     */
    public static String encrypt(String data, String salt) {
        MD5 md5 = new MD5(salt.getBytes());
        return md5.digestHex(data);
    }

    /**
     * 加密串匹配
     *
     * @param sourceData 源字符串
     * @param salt       盐
     * @param matchData  匹配串
     * @return false: 不同 true: 相同
     * @author luyuhao
     * @date 20/11/22 23:18
     */
    public static boolean match(String sourceData, String salt, String matchData) {
        MD5 md5 = new MD5(salt.getBytes());
        String data = md5.digestHex(sourceData);
        return data.equals(matchData);
    }
}
