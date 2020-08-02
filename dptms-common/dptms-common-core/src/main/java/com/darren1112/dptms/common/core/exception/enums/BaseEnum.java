package com.darren1112.dptms.common.core.exception.enums;

/**
 * 异常基础类
 *
 * @author luyuhao
 * @date 19/12/06 03:18
 */
public interface BaseEnum {
    /**
     * 异常code
     *
     * @return int
     */
    Integer getCode();

    /**
     * 异常信息
     *
     * @return string
     */
    String getMessage();
}
