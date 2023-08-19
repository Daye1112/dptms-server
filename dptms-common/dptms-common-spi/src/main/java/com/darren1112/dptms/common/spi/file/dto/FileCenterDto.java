package com.darren1112.dptms.common.spi.file.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.darren1112.dptms.common.spi.file.entity.FileCenterEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 文件中心Dto
 *
 * @author darren
 * @since 2021/12/18
 */
@Data
@TableName("file_center")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class FileCenterDto extends FileCenterEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 文件类型
     */
    @ApiModelProperty(value = "文件类型")
    private Integer fileInfoType;

    /**
     * 文件大小
     */
    @ApiModelProperty(value = "文件大小")
    private Long fileSize;

    /**
     * 文件大小-自适应
     */
    @ApiModelProperty(value = "文件大小-自适应")
    private String fileSizeStr;

    /**
     * 文件夹大小
     */
    @ApiModelProperty(value = "文件夹大小")
    private Long fileSizeCount;

    /**
     * 文件夹大小-自适应
     */
    @ApiModelProperty(value = "文件夹大小-自适应")
    private String fileSizeCountStr;

    /**
     * 文件类型名
     */
    @ApiModelProperty(value = "文件类型名")
    private String fileExt;

    /**
     * 创建者姓名
     */
    @ApiModelProperty(value = "创建者姓名")
    private String creatorName;
}
