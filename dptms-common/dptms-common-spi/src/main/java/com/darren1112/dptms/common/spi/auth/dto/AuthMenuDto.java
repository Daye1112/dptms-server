package com.darren1112.dptms.common.spi.auth.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.darren1112.dptms.common.spi.auth.entity.AuthMenuEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * 菜单Dto
 *
 * @author luyuhao
 * @since 2020/12/12 17:23
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("auth_menu")
public class AuthMenuDto extends AuthMenuEntity {

    /**
     * 是否更新
     */
    @TableField(exist = false)
    @ApiModelProperty("是否更新")
    private Boolean isUpdate;

    /**
     * 是否已分配
     */
    @TableField(exist = false)
    @ApiModelProperty("是否已分配")
    private Boolean isAssigned;

    /**
     * 子节点
     */
    @TableField(exist = false)
    @ApiModelProperty("子节点")
    private List<AuthMenuDto> children;

    /**
     * 已分配的ids
     */
    @TableField(exist = false)
    private List<Long> assignedIdList;
}
