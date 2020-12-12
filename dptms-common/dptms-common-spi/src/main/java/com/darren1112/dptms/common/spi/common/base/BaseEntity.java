package com.darren1112.dptms.common.spi.common.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础实体类
 *
 * @author luyuhao
 * @date 2020/07/23 02:19
 */
@Data
public class BaseEntity implements Serializable {

    /**
     * id
     */
    @ApiModelProperty("id")
    private Long id;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

    /**
     * 是否有效
     */
    @ApiModelProperty("是否有效")
    private Boolean isvalid;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date ctime;

    /**
     * 创建者
     */
    @ApiModelProperty("创建者")
    private Long creater;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private Date mtime;

    /**
     * 更新者
     */
    @ApiModelProperty("更新者")
    private Long updater;
}
