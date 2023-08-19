package com.darren1112.dptms.common.core.exception;

/**
 * 实体类已存在异常
 *
 * @author darren
 * @since 19/12/09 03:48
 */
public class EntityExistException extends BaseException {
    public EntityExistException(String message) {
        super(message + "已存在");
    }
}

