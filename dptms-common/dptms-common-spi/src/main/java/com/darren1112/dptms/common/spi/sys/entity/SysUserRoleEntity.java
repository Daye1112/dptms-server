package com.darren1112.dptms.common.spi.sys.entity;

import com.darren1112.dptms.common.spi.common.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 用户角色Entity
 *
 * @author luyuhao
 * @since 20/12/23 01:50
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SysUserRoleEntity extends BaseEntity {

    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id")
    private Long roleId;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long userId;
}
