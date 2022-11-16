package com.darren1112.dptms.common.core.constants;

/**
 * 过滤器顺序常量
 *
 * @author luyuhao
 * @since 2021/01/17 01:15
 */
public class FilterOrderConstant {

    /**
     * zuul头过滤器
     */
    public static final int ZUUL_HEADER_VALID_FILTER = 0;

    /**
     * token校验过滤器
     */
    public static final int DPTMS_TOKEN_VALID_FILTER = 10;

    /**
     * 用户信息过滤器
     */
    public static final int SECURITY_USER_FILTER = 11;

    /**
     * permission校验过滤器
     */
    public static final int PERMISSION_VALID_FILTER = 12;

    /**
     * 登录流程过滤器
     */
    public static final int LOGIN_PROCESSING_FILTER = 13;

}
