package com.darren1112.dptms.common.spi.sys.entity;

import com.darren1112.dptms.common.spi.common.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 角色菜单Entity
 *
 * @author luyuhao
 * @date 20/12/13 23:04
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SysRoleMenuEntity extends BaseEntity {

    /**
     * 菜单id
     */
    @ApiModelProperty(value = "菜单id")
    private Long menuId;

    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id")
    private Long roleId;

}
