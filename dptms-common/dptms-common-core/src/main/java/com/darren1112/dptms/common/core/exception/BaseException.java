package com.darren1112.dptms.common.core.exception;

import com.darren1112.dptms.common.core.exception.enums.BaseErrorEnum;
import org.springframework.http.HttpStatus;

/**
 * 基础异常类
 *
 * @author luyuhao
 * @since 2020/01/07 23:50
 */
public class BaseException extends RuntimeException {
    private Integer status = HttpStatus.BAD_REQUEST.value();

    public BaseException(BaseErrorEnum baseErrorEnum) {
        super(baseErrorEnum.getMessage());
        this.status = baseErrorEnum.getCode();
    }

    public BaseException(Integer status) {
        this.status = status;
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(Integer status, String message) {
        super(message);
        this.status = status;
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public Integer getStatus() {
        return status;
    }
}
