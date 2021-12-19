package com.darren1112.dptms.common.spi.file.entity;

import com.darren1112.dptms.common.spi.common.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 文件中心Entity
 *
 * @author luyuhao
 * @since 2021/12/18
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class FileCenterEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 组织id
     */
    @ApiModelProperty(value = "组织id")
    private Long orgId;

    /**
     * 文件名称
     */
    @ApiModelProperty(value = "文件名称")
    private String fileName;

    /**
     * 文件描述
     */
    @ApiModelProperty(value = "文件描述")
    private String fileDesc;

    /**
     * 文件类型 1：文件 2：文件夹
     */
    @ApiModelProperty(value = "文件类型 1：文件 2：文件夹")
    private Byte fileType;

    /**
     * 父文件夹id，0表示根目录
     */
    @ApiModelProperty(value = "父文件夹id，0表示根目录")
    private Long fileParentId;

    /**
     * 目录id路径
     */
    @ApiModelProperty(value = "目录id路径")
    private String fileParentPath;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer fileOrder;

    /**
     * 文件id，关联file_info.id
     */
    @ApiModelProperty(value = "文件id，关联file_info.id")
    private Long fileId;

}
