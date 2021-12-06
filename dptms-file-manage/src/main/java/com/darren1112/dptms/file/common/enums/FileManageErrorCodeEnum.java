package com.darren1112.dptms.file.common.enums;

import com.darren1112.dptms.common.core.exception.enums.BaseErrorEnum;
import org.springframework.http.HttpStatus;

/**
 * 文件错误异常信息
 *
 * @author luyuhao
 * @since 2021/12/05
 */
public enum FileManageErrorCodeEnum implements BaseErrorEnum {
    /**
     * 文件管理错误信息
     */
    FILE_NOT_NULL(HttpStatus.BAD_REQUEST, "文件不能为空"),
    FILE_UPLOAD_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "文件上传失败")
    ;

    private Integer code;
    private String message;

    FileManageErrorCodeEnum(HttpStatus httpStatus, String message) {
        this.code = httpStatus.value();
        this.message = message;
    }


    /**
     * 异常code
     *
     * @return {@link Integer)
     * @author luyuhao
     * @since 2021/12/5
     */
    @Override
    public Integer getCode() {
        return code;
    }

    /**
     * 异常信息
     *
     * @return {@link String)
     * @author luyuhao
     * @since 2021/12/5
     */
    @Override
    public String getMessage() {
        return message;
    }
}
