package com.darren1112.dptms.system.common.enums;

import com.darren1112.dptms.common.core.exception.enums.BaseEnum;
import org.springframework.http.HttpStatus;

/**
 * 系统管理错误状态码enum
 *
 * @author luyuhao
 * @date 2020/11/30 23:13
 */
public enum SystemManageErrorCodeEnum implements BaseEnum {

    /**
     * 系统管理错误状态码
     */
    ID_NOT_NULL(HttpStatus.BAD_REQUEST, "id不能为空"),
    PER_NAME_NOT_NULL(HttpStatus.BAD_REQUEST, "权限名不能为空"),
    PER_CODE_NOT_NULL(HttpStatus.BAD_REQUEST, "权限码不能为空"),
    PER_GROUP_NOT_NULL(HttpStatus.BAD_REQUEST, "权限组不能为空"),
    PER_URL_NOT_NULL(HttpStatus.BAD_REQUEST, "权限url不能为空"),
    PER_NOT_REPEAT(HttpStatus.BAD_REQUEST, "权限码或权限url不能重复"),
    ORG_NAME_NOT_NULL(HttpStatus.BAD_REQUEST, "组织名称不能为空"),
    ORG_CODE_NOT_NULL(HttpStatus.BAD_REQUEST, "组织编号不能为空"),
    ORG_NOT_REPEAT(HttpStatus.BAD_REQUEST, "组织名称或组织编号不能重复"),
    USER_ID_NOT_NULL(HttpStatus.BAD_REQUEST, "用户id不能为空"),
    ORG_ID_NOT_NULL(HttpStatus.BAD_REQUEST, "组织id不能为空"),
    MENU_NAME_NOT_NULL(HttpStatus.BAD_REQUEST, "菜单名不能为空"),
    MENU_CODE_NOT_NULL(HttpStatus.BAD_REQUEST, "菜单码不能为空"),
    MENU_TYPE_NOT_NULL(HttpStatus.BAD_REQUEST, "菜单类型不能为空"),
    MENU_PARENT_ID_NOT_NULL(HttpStatus.BAD_REQUEST, "菜单父节点id不能为空"),
    MENU_CODE_REPEAT(HttpStatus.BAD_REQUEST, "菜单码不能重复")
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
