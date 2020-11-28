package com.darren1112.dptms.common.spi.sys.entity;

import com.darren1112.dptms.common.spi.common.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 组织Entity
 *
 * @author luyuhao
 * @date 20/08/16 01:35
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysOrganizationEntity extends BaseEntity {

    /**
     * 组织名称
     */
    @ApiModelProperty("组织名称")
    private String orgName;

    /**
     * 组织编号
     */
    @ApiModelProperty("组织编号")
    private String orgCode;
}
