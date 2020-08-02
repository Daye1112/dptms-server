package com.darren1112.dptms.common.spi.common.entity;

import lombok.Data;

import java.util.Date;

/**
 * 基础实体类
 *
 * @author luyuhao
 * @date 2020/07/23 02:19
 */
@Data
public class BaseEntity {

    /**
     * id
     */
    private Long id;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否有效
     */
    private Boolean isvalid;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建者
     */
    private Long createBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新者
     */
    private Long updateBy;
}
