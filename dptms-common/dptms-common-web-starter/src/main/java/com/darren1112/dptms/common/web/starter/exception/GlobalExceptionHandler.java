package com.darren1112.dptms.common.web.starter.exception;

import com.darren1112.dptms.common.core.enums.MicroErrorCodeEnum;
import com.darren1112.dptms.common.core.exception.BaseException;
import com.darren1112.dptms.common.core.exception.ServerErrorException;
import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.ResponseEntityUtil;
import com.darren1112.dptms.common.core.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import java.util.Optional;

/**
 * 统一异常处理
 *
 * @author luyuhao
 * @since 2019/12/6 9:19
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 服务异常处理
     */
    @ExceptionHandler(ServerErrorException.class)
    public ResponseEntity handleServerErrorException(ServerErrorException se) {
        log.error("系统捕捉ServerErrorException异常并处理 ==> " + MicroErrorCodeEnum.SERVER_DOWN.getMessage(), se);
        String message = StringUtil.isContainChinese(se.getMessage()) ? se.getMessage() : null;
        return ResponseEntityUtil.internalServerError(JsonResult.buildErrorMsg(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                Optional.ofNullable(message).orElse(MicroErrorCodeEnum.SERVER_DOWN.getMessage())));
    }

    /**
     * 文件上传异常处理
     */
    @ExceptionHandler(MultipartException.class)
    public ResponseEntity handleMultipartException(MultipartException me) {
        log.error("系统捕捉MultipartException异常并处理 ==> " + MicroErrorCodeEnum.FILE_UPLOAD_ERROR.getMessage(), me);
        return ResponseEntityUtil.badRequest(JsonResult.buildErrorEnum(MicroErrorCodeEnum.FILE_UPLOAD_ERROR));
    }

    /**
     * 处理系统自定义异常
     */
    @ExceptionHandler(BaseException.class)
    public ResponseEntity handleBaseException(BaseException e) {
        String message = StringUtil.isContainChinese(e.getMessage()) ? e.getMessage() : null;
        return ResponseEntityUtil.custom(HttpStatus.resolve(e.getStatus()), JsonResult.buildErrorMsg(e.getStatus(),
                Optional.ofNullable(message).orElse(MicroErrorCodeEnum.OPERATE_ERROR.getMessage())));
    }

    /**
     * Throwable
     * 接收非系统预测内的异常
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity handleThrowable(Throwable e) {
        log.error("系统捕捉Throwable异常并处理 ==> " + e.getMessage(), e);
        String message = StringUtil.isContainChinese(e.getMessage()) ? e.getMessage() : null;
        return ResponseEntityUtil.internalServerError(JsonResult.buildErrorMsg(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                Optional.ofNullable(message).orElse(MicroErrorCodeEnum.SYSTEM_ERROR.getMessage())));
    }
}
