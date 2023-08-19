package com.darren1112.dptms.common.spi.service.dto;

import com.darren1112.dptms.common.spi.service.entity.ServiceConfigReleasePropEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 配置发布属性表Dto
 *
 * @author darren
 * @since 2021/03/12 01:41
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ServiceConfigReleasePropDto extends ServiceConfigReleasePropEntity {

    /**
     * 应用编号
     */
    @ApiModelProperty(value = "应用编号")
    private String appCode;

    /**
     * 环境编号
     */
    @ApiModelProperty(value = "环境编号")
    private String profileCode;
}
