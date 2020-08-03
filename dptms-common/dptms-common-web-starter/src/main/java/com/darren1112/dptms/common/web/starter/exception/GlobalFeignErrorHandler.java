package com.darren1112.dptms.common.web.starter.exception;

import com.darren1112.dptms.common.core.enums.MicroErrorCodeEnum;
import com.darren1112.dptms.common.core.exception.ServerErrorException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

/**
 * feign全局异常处理
 *
 * @author luyuhao
 * @date 19/12/18 22:09
 */
@Slf4j
public class GlobalFeignErrorHandler implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        return new ServerErrorException(MicroErrorCodeEnum.SERVER_DOWN.getMessage());
    }
}
