// package com.darren1112.dptms.sdk.starter.security.util;
//
// import com.darren1112.dptms.common.spi.common.dto.ActiveUser;
//
// /**
//  * 系统权限工具类
//  *
//  * @author darren
//  * @since 2021/01/16 17:00
//  */
// public class DptmsSecurityUtil {
//
//     private static ThreadLocal<ActiveUser> threadLocal = new ThreadLocal<>();
//
//     public static void set(ActiveUser activeUser) {
//         threadLocal.set(activeUser);
//     }
//
//     public static ActiveUser get() {
//         return threadLocal.get();
//     }
//
//     public static void remove() {
//         threadLocal.remove();
//     }
//
// }
