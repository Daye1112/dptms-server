package com.darren1112.dptms.common.spi.sys.entity;

import com.darren1112.dptms.common.spi.common.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 用户组织Entity
 *
 * @author luyuhao
 * @date 20/12/13 18:05
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SysUserOrganizationEntity extends BaseEntity {

    /**
     * 组织id
     */
    @ApiModelProperty(value = "组织id")
    private Long orgId;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long userId;

}
