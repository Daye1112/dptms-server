package com.darren1112.dptms.common.spi.service.entity;

import com.darren1112.dptms.common.spi.common.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * 配置发布表Entity
 *
 * @author luyuhao
 * @since 2021/03/12 01:40
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ServiceConfigReleaseEntity extends BaseEntity {

    /**
     * 应用id 关联service_application.id
     */
    @ApiModelProperty(value = "应用id 关联service_application.id")
    private Long applicationId;

    /**
     * 环境id 关联service_config_profile.id
     */
    @ApiModelProperty(value = "环境id 关联service_config_profile.id")
    private Long profileId;

    /**
     * 发布版本
     */
    @ApiModelProperty(value = "发布版本")
    private String releaseVersion;

    /**
     * 发布类型 0：普通发布 1：回滚发布
     */
    @ApiModelProperty(value = "发布类型 1：普通发布 2：回滚发布")
    private Integer releaseType;

    /**
     * 发布状态 0：失效 1：激活
     */
    @ApiModelProperty(value = "发布状态 0：失效 1：激活")
    private Integer releaseStatus;

    /**
     * 发布时间
     */
    @ApiModelProperty(value = "发布时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date releaseTime;
}
