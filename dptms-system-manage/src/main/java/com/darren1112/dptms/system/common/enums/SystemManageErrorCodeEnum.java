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
    ORG_ID_NOT_NULL(HttpStatus.BAD_REQUEST, "组织id不能为空"),
    APP_CODE_NOT_NULL(HttpStatus.BAD_REQUEST, "应用编号不能为空"),
    APP_NAME_NOT_NULL(HttpStatus.BAD_REQUEST, "应用名称不能为空"),
    APP_TYPE_NOT_NULL(HttpStatus.BAD_REQUEST, "应用类型不能为空"),
    APP_NOT_REPEAT(HttpStatus.BAD_REQUEST, "应用信息重复"),
    APP_ID_NOT_NULL(HttpStatus.BAD_REQUEST, "应用id不能为空"),
    PROFILE_CODE_NOT_NULL(HttpStatus.BAD_REQUEST, "环境编号不能为空"),
    PROFILE_NAME_NOT_NULL(HttpStatus.BAD_REQUEST, "环境名称不能为空"),
    PROFILE_NOT_REPEAT(HttpStatus.BAD_REQUEST, "环境信息重复"),
    PROFILE_ID_NOT_NULL(HttpStatus.BAD_REQUEST, "环境id不能为空"),
    PROP_KEY_NOT_NULL(HttpStatus.BAD_REQUEST, "属性key不能为空"),
    PROP_VALUE_NOT_NULL(HttpStatus.BAD_REQUEST, "属性value不能为空"),
    PROP_NOT_REPEAT(HttpStatus.BAD_REQUEST, "属性信息重复"),
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
