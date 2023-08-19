package com.darren1112.dptms.common.spi.auth.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.darren1112.dptms.common.spi.auth.entity.AuthUserEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * 系统用户Dto
 *
 * @author darren
 * @since 2020/07/23 02:22
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("auth_user")
public class AuthUserDto extends AuthUserEntity {

    /**
     * 是否更新 true/false
     */
    @TableField(exist = false)
    private Boolean isUpdate;

    /**
     * 权限list
     */
    @TableField(exist = false)
    private List<AuthPermissionDto> permissionList;

    /**
     * 旧密码
     */
    @TableField(exist = false)
    @ApiModelProperty("旧密码")
    private String oldPassword;

    /**
     * 新密码
     */
    @TableField(exist = false)
    @ApiModelProperty("新密码")
    private String newPassword;
}
