package com.darren1112.dptms.system.common.enums;

import com.darren1112.dptms.common.core.exception.enums.BaseErrorEnum;
import org.springframework.http.HttpStatus;

/**
 * 系统管理错误状态码enum
 *
 * @author luyuhao
 * @date 2020/11/30 23:13
 */
public enum SystemManageErrorCodeEnum implements BaseErrorEnum {

    /**
     * 系统管理错误状态码
     */
    ID_NOT_NULL(HttpStatus.BAD_REQUEST, "id不能为空"),
    REDIS_KEY_NOT_NULL(HttpStatus.BAD_REQUEST, "缓存键值不能为空"),
    REDIS_KEY_PREFIX_NOT_NULL(HttpStatus.BAD_REQUEST, "缓存前缀不能为空"),
    REDIS_VALUE_NOT_NULL(HttpStatus.BAD_REQUEST, "缓存值不能为空"),
    REDIS_KEY_EXPIRED_NOT_NULL(HttpStatus.BAD_REQUEST, "缓存有效期不能为空"),
    ;

    private Integer code;
    private String message;

    SystemManageErrorCodeEnum(HttpStatus httpStatus, String message) {
        this.code = httpStatus.value();
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
