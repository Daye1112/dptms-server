package com.darren1112.dptms.common.spi.file.entity;

import com.darren1112.dptms.common.spi.common.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 文件信息Entity
 *
 * @author luyuhao
 * @since 2021/12/1
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class FileInfoEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 组织id
     */
    @ApiModelProperty(value = "组织id")
    private Long orgId;

    /**
     * 文件类型
     */
    @ApiModelProperty(value = "文件类型")
    private Integer fileType;

    /**
     * 文件名称
     */
    @ApiModelProperty(value = "文件名称")
    private String fileName;

    /**
     * 文件总大小
     */
    @ApiModelProperty(value = "文件总大小")
    private Long fileSize;

    /**
     * 文件类型名
     */
    @ApiModelProperty(value = "文件类型名")
    private String fileExt;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}
