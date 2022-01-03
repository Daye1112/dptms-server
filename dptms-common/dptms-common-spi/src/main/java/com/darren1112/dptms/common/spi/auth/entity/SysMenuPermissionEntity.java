package com.darren1112.dptms.common.spi.auth.entity;

import com.darren1112.dptms.common.spi.common.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 菜单权限Entity
 *
 * @author baojiazhong
 * @since 2020/12/22 23:20
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SysMenuPermissionEntity extends BaseEntity {

    /**
     * 权限id
     */
    @ApiModelProperty("权限id")
    private Long perId;

    /**
     * 菜单id
     */
    @ApiModelProperty("菜单id")
    private Long menuId;
}
