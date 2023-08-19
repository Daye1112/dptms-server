package com.darren1112.dptms.common.spi.service.entity;

import com.darren1112.dptms.common.spi.common.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 配置环境属性表Entity
 *
 * @author darren
 * @since 2021/03/12 01:39
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ServiceConfigProfilePropEntity extends BaseEntity {

    /**
     * 环境id 关联service_config_profile.id
     */
    @ApiModelProperty(value = "环境id 关联service_config_profile.id")
    private Long profileId;

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
