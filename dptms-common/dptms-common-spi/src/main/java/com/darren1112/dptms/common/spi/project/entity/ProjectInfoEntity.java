package com.darren1112.dptms.common.spi.project.entity;

import com.darren1112.dptms.common.spi.common.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 项目信息Entity
 *
 * @author darren
 * @since 2023/08/13
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProjectInfoEntity extends BaseEntity {

    /**
     * 组织id
     */
    @ApiModelProperty(value = "组织id")
    private Long orgId;

    /**
     * 项目名称
     */
    @ApiModelProperty(value = "项目名称")
    private String projectName;

    /**
     * 项目描述
     */
    @ApiModelProperty(value = "项目描述")
    private String projectDesc;

    /**
     * 项目appKey
     */
    @ApiModelProperty(value = "项目appKey")
    private String projectAppKey;

}
