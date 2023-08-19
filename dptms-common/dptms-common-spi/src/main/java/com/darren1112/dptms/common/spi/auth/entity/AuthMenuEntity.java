package com.darren1112.dptms.common.spi.auth.entity;

import com.darren1112.dptms.common.spi.common.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 菜单Entity
 *
 * @author darren
 * @since 2020/12/12 17:22
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AuthMenuEntity extends BaseEntity {

    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    /**
     * 菜单编号
     */
    @ApiModelProperty(value = "菜单编号")
    private String menuCode;

    /**
     * 菜单类型 1：普通页 2：新窗口 3：iframe
     */
    @ApiModelProperty(value = "菜单类型 1：普通页 2：新窗口 3：iframe")
    private Byte menuType;

    /**
     * 父节点id
     */
    @ApiModelProperty(value = "父节点id")
    private Long menuParentId;

}
