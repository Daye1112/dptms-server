package com.darren1112.dptms.common.spi.auth.entity;

import com.darren1112.dptms.common.spi.common.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 角色Entity
 *
 * @author luyuhao
 * @since 20/12/13 23:05
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SysRoleEntity extends BaseEntity {

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String roleName;

    /**
     * 角色编号
     */
    @ApiModelProperty(value = "角色编号")
    private String roleCode;

    /**
     * 是否管理员 0：否 1：是
     */
    @ApiModelProperty(value = "是否管理员 0：否 1：是")
    private Boolean isAdmin;
}
