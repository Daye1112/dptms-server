package com.darren1112.dptms.common.spi.auth.dto;

import com.darren1112.dptms.common.spi.auth.entity.SysUserEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * 系统用户Dto
 *
 * @author luyuhao
 * @since 2020/07/23 02:22
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SysUserDto extends SysUserEntity {

    /**
     * 是否更新 true/false
     */
    private Boolean isUpdate;

    /**
     * 权限list
     */
    private List<SysPermissionDto> permissionList;

    /**
     * 旧密码
     */
    @ApiModelProperty("旧密码")
    private String oldPassword;

    /**
     * 新密码
     */
    @ApiModelProperty("新密码")
    private String newPassword;
}
