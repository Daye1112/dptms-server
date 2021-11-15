package com.darren1112.dptms.common.core.util;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;

/**
 * web相关工具类
 *
 * @author luyuhao
 * @since 2021/01/31 18:51
 */
public class WebUtil {

    /**
     * 获取用户使用的操作系统
     *
     * @return 操作信息
     * @author luyuhao
     * @since 2021/01/31 19:02
     */
    public static String getOs() {
        String userAgentStr = RequestUtil.getHeaderByName("User-Agent");
        // 解析agent字符串
        UserAgent userAgent = UserAgent.parseUserAgentString(userAgentStr);
        OperatingSystem operatingSystem = userAgent.getOperatingSystem();
        return operatingSystem.getName();
    }

    /**
     * 获取用户使用的浏览器
     *
     * @return 浏览器+版本
     * @author luyuhao
     * @since 2021/01/31 19:01
     */
    public static String getBrowser() {
        String userAgentStr = RequestUtil.getHeaderByName("User-Agent");
        // 解析agent字符串
        UserAgent userAgent = UserAgent.parseUserAgentString(userAgentStr);
        // 获取浏览器对象
        Browser browser = userAgent.getBrowser();
        return browser.getName();
    }
}
