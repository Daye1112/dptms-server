package com.darren1112.dptms.common.spi.project.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.darren1112.dptms.common.spi.project.entity.ProjectInfoEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 项目信息Dto
 *
 * @author darren
 * @since 2023/08/13
 */
@Data
@TableName("project_info")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProjectInfoDto extends ProjectInfoEntity {

    /**
     * 是否更新 true/false
     */
    @TableField(exist = false)
    private Boolean isUpdate;

    /**
     * 查询key
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "查询key")
    private String researchKey;
}
