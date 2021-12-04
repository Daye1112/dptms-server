package com.darren1112.dptms.common.spi.file.entity;

import com.darren1112.dptms.common.spi.common.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 文件存储信息Entity
 *
 * @author luyuhao
 * @since 2021/12/1
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class FileDfsInfoEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 文件信息id file_info.id
     */
    @ApiModelProperty(value = "文件信息id file_info.id")
    private Long fileInfoId;

    /**
     * 存储组
     */
    @ApiModelProperty(value = "存储组")
    private String fileGroup;

    /**
     * 存储路径
     */
    @ApiModelProperty(value = "存储路径")
    private String filePath;

    /**
     * 存储大小
     */
    @ApiModelProperty(value = "存储大小")
    private Long fileSize;

    /**
     * 文件排序
     */
    @ApiModelProperty(value = "文件排序")
    private Integer fileOrder = 1;
}
