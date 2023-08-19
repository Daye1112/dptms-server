package com.darren1112.dptms.common.core.util;

import com.darren1112.dptms.common.core.constants.CommonConstant;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 编码工具类
 *
 * @author darren
 * @since 2021/7/21
 */
public class EncodeUtil {

    /**
     * url编码
     *
     * @param str 待编码串
     * @return {@link String}
     * @author darren
     * @since 2021/7/21
     */
    public static String encodeUrl(String str) {
        if (StringUtil.isEmpty(str)) {
            return "";
        }
        try {
            return URLEncoder.encode(str, CommonConstant.UTF_8);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * url解码
     *
     * @param str 待解码串
     * @return {@link String}
     * @author darren
     * @since 2021/7/21
     */
    public static String decodeUrl(String str) {
        if (StringUtil.isEmpty(str)) {
            return "";
        }
        try {
            return URLDecoder.decode(str, CommonConstant.UTF_8);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
