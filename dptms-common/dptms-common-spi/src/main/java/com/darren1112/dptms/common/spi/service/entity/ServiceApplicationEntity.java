package com.darren1112.dptms.common.spi.service.entity;

import com.darren1112.dptms.common.spi.common.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 应用表Entity
 *
 * @author darren
 * @since 2021/03/12 01:27
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ServiceApplicationEntity extends BaseEntity {

    /**
     * 组织id
     */
    @ApiModelProperty(value = "组织id")
    private Long orgId;

    /**
     * 应用编号
     */
    @ApiModelProperty(value = "应用编号")
    private String appCode;

    /**
     * 应用名称
     */
    @ApiModelProperty(value = "应用名称")
    private String appName;

    /**
     * 应用类型 1：服务 2：网关
     */
    @ApiModelProperty(value = "应用类型 1：服务 2：网关")
    private Byte appType;
}
