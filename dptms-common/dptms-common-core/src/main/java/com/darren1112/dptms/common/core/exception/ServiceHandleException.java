package com.darren1112.dptms.common.core.exception;

import com.darren1112.dptms.common.core.exception.enums.BaseErrorEnum;
import lombok.Getter;

/**
 * 业务处理异常
 *
 * @author luyuhao
 * @since 2021/12/6
 */
@Getter
public class ServiceHandleException extends BaseException {

    public ServiceHandleException(String msg) {
        super(msg);
    }

    public ServiceHandleException(Integer status, String message) {
        super(status, message);
    }

    public ServiceHandleException(BaseErrorEnum baseErrorEnum) {
        super(baseErrorEnum);
    }
}
