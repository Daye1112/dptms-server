package com.darren1112.dptms.common.spi.common.base;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础实体类
 *
 * @author luyuhao
 * @since 2020/07/23 02:19
 */
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
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
    @TableLogic("true")
    @ApiModelProperty("是否有效")
    @TableField(fill = FieldFill.INSERT)
    private Boolean isvalid;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date mtime;

    /**
     * 更新者
     */
    @ApiModelProperty("更新者")
    private Long updater;
}
