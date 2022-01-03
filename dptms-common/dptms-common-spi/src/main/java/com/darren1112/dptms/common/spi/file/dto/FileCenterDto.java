package com.darren1112.dptms.common.spi.file.dto;

import com.darren1112.dptms.common.spi.file.entity.FileCenterEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 文件中心Dto
 *
 * @author luyuhao
 * @since 2021/12/18
 */
@Data
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
     * 文件总大小
     */
    @ApiModelProperty(value = "文件总大小")
    private Long fileSize;

    /**
     * 文件类型名
     */
    @ApiModelProperty(value = "文件类型名")
    private String fileExt;
}
