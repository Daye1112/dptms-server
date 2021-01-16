package com.darren1112.dptms.common.core.constants;

/**
 * 过滤器顺序常量
 *
 * @author luyuhao
 * @date 2021/01/17 01:15
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
     * permission校验过滤器
     */
    public static final int PERMISSION_VALID_FILTER = 11;

    /**
     * 添加用户信息filter
     */
    public static final int ADD_USER_FILTER = 12;

}
