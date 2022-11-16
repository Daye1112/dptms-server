package com.darren1112.dptms.auth.common.enums;

import com.darren1112.dptms.common.core.exception.enums.BaseErrorEnum;
import org.springframework.http.HttpStatus;

/**
 * 认证服务错误状态码
 *
 * @author luyuhao
 * @since 2020/07/26 21:22
 */
public enum AuthErrorCodeEnum implements BaseErrorEnum {

    /**
     * 认证服务错误状态码
     */
    LOCKED(HttpStatus.BAD_REQUEST, "用户已被锁定，请联系管理员"),
    LOGIN_FAILURE(HttpStatus.BAD_REQUEST, "用户名或密码错误"),
    SAVE_TOKEN_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "token保存异常，请联系管理员"),
    USERNAME_NOT_NULL(HttpStatus.BAD_REQUEST, "账号不能为空"),
    PASSWORD_NOT_NULL(HttpStatus.BAD_REQUEST, "密码不能为空"),
    LOGIN_TYPE_NOT_NULL(HttpStatus.BAD_REQUEST, "登录类型不能为空"),
    REFRESH_TOKEN_NOT_NULL(HttpStatus.BAD_REQUEST, "refreshToken不能为空"),
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
    ROLE_ID_NOT_NULL(HttpStatus.BAD_REQUEST, "角色id不能为空"),
    MENU_ID_NOT_NULL(HttpStatus.BAD_REQUEST, "菜单id不能为空"),
    MENU_NAME_NOT_NULL(HttpStatus.BAD_REQUEST, "菜单名不能为空"),
    MENU_CODE_NOT_NULL(HttpStatus.BAD_REQUEST, "菜单码不能为空"),
    MENU_TYPE_NOT_NULL(HttpStatus.BAD_REQUEST, "菜单类型不能为空"),
    MENU_PARENT_ID_NOT_NULL(HttpStatus.BAD_REQUEST, "菜单父节点id不能为空"),
    MENU_CODE_REPEAT(HttpStatus.BAD_REQUEST, "菜单码不能重复"),
    ROLE_NAME_NOT_NULL(HttpStatus.BAD_REQUEST, "角色名称不能为空"),
    ROLE_CODE_NOT_NULL(HttpStatus.BAD_REQUEST, "角色编号不能为空"),
    ROLE_IS_ADMIN_NOT_NULL(HttpStatus.BAD_REQUEST, "是否管理员不能为空"),
    USER_USERNAME_NOT_NULL(HttpStatus.BAD_REQUEST, "用户名不能为空"),
    USER_IS_LOCKED_NOT_NULL(HttpStatus.BAD_REQUEST, "用户锁定状态不能为空"),
    USER_NOT_REPEAT(HttpStatus.BAD_REQUEST, "用户名不能重复"),
    USER_NEW_PASSWORD_NOT_NULL(HttpStatus.BAD_REQUEST, "新密码不能为空"),
    USER_OLD_PASSWORD_NOT_NULL(HttpStatus.BAD_REQUEST, "旧密码不能为空"),
    USER_NOT_EXIST(HttpStatus.BAD_REQUEST, "用户不存在"),
    USER_PASSWORD_ERROR(HttpStatus.BAD_REQUEST, "用户密码错误"),

    ROLE_NOT_REPEAT(HttpStatus.BAD_REQUEST, "角色码不能重复"),
    CAPTCHA_GENERATE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "验证码生成异常"),
    CAPTCHA_KEY_NOT_NULL(HttpStatus.BAD_REQUEST, "验证码key不能为空"),
    CAPTCHA_CODE_NOT_NULL(HttpStatus.BAD_REQUEST, "验证码code不能为空"),
    ;

    private Integer code;
    private String message;

    AuthErrorCodeEnum(HttpStatus httpStatus, String message) {
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
