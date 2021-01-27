package com.darren1112.dptms.common.core.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie工具类
 *
 * @author luyuhao
 * @date 2020/11/26 22:46
 */
public class CookieUtil {

    /**
     * 默认cookie最大有效期
     */
    private static final int COOKIE_DEFAULE_MAX_AGE = 356 * 24 * 60 * 60;

    /**
     * 设置cookie
     *
     * @param key      key
     * @param value    value
     * @param maxAge   有效期
     * @param response 响应域
     * @author luyuhao
     * @date 20/11/26 22:57
     */
    public static void setCookie(String key, String value, int maxAge, HttpServletResponse response) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 设置cookie
     *
     * @param key      key
     * @param value    value
     * @param response 响应域
     * @author luyuhao
     * @date 20/11/26 22:57
     */
    public static void setCookie(String key, String value, HttpServletResponse response) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(COOKIE_DEFAULE_MAX_AGE);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 获取cookie
     *
     * @param cookieName cookie名
     * @param request    请求域
     * @return cookie值
     * @author luyuhao
     * @date 20/11/26 22:47
     */
    public static String getCookie(String cookieName, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (CollectionUtil.isNotEmpty(cookies)) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 删除cookie
     *
     * @param cookieName cookie名
     * @param response   响应域
     * @author luyuhao
     * @date 2021/01/28 01:10
     */
    public static void deleteCookie(String cookieName, HttpServletResponse response) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
