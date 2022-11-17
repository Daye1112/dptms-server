package com.darren1112.dptms.common.core.base;

/**
 * bean抽象类
 *
 * @author luyuhao
 * @since 2022/11/17
 */
public interface BaseBean {

    /**
     * 初始方法
     *
     * @throws Exception 异常
     * @author luyuhao
     * @since 2022/11/17
     */
    default void init() throws Exception {

    }

    /**
     * 销毁方法
     *
     * @author luyuhao
     * @since 2022/11/17
     */
    default void destroy() {

    }
}
