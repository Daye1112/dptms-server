package com.darren1112.dptms.common.core.exception;

/**
 * 服务异常
 *
 * @author luyuhao
 * @since 2019/12/7 10:52
 */
public class ServerErrorException extends RuntimeException {

    public ServerErrorException() {
    }

    public ServerErrorException(String msg) {
        super(msg);
    }


}
