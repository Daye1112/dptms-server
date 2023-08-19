package com.darren1112.dptms.common.core.exception.enums;

/**
 * 异常基础类
 *
 * @author darren
 * @since 19/12/06 03:18
 */
public interface BaseErrorEnum {
    /**
     * 异常code
     *
     * @return {@link Integer)
     * @author darren
     * @since 2021/12/5
     */
    Integer getCode();

    /**
     * 异常信息
     *
     * @return {@link String)
     * @author darren
     * @since 2021/12/5
     */
    String getMessage();
}
