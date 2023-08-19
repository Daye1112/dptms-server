package com.darren1112.dptms.common.spi.auth.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.darren1112.dptms.common.spi.auth.entity.AuthRoleEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 角色Dto
 *
 * @author darren
 * @since 2020/12/13 23:05
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("auth_role")
public class AuthRoleDto extends AuthRoleEntity {

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
}
