package com.darren1112.dptms.common.spi.service.entity;

import com.darren1112.dptms.common.spi.common.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 配置环境表Entity
 *
 * @author darren
 * @since 2021/03/12 01:37
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ServiceConfigProfileEntity extends BaseEntity {

    /**
     * 应用id 关联service_application.id
     */
    @ApiModelProperty(value = "应用id 关联service_application.id")
    private Long applicationId;

    /**
     * 环境编号
     */
    @ApiModelProperty(value = "环境编号")
    private String profileCode;

    /**
     * 环境名称
     */
    @ApiModelProperty(value = "环境名称")
    private String profileName;
}
