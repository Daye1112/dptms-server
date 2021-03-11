package com.darren1112.dptms.common.spi.service.entity;

import com.darren1112.dptms.common.spi.common.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 配置发布属性表Entity
 *
 * @author luyuhao
 * @date 2021/03/12 01:41
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ServiceConfigReleasePropEntity extends BaseEntity {

    /**
     * 发布id 关联service_config_release.id
     */
    @ApiModelProperty(value = "发布id 关联service_config_release.id")
    private Long releaseId;

    /**
     * 属性键
     */
    @ApiModelProperty(value = "属性键")
    private String propKey;

    /**
     * 属性值
     */
    @ApiModelProperty(value = "属性值")
    private String propValue;

    /**
     * 属性描述
     */
    @ApiModelProperty(value = "属性描述")
    private String propDesc;
}
