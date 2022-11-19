package com.darren1112.dptms.common.spi.auth.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.darren1112.dptms.common.spi.auth.entity.AuthPermissionEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 权限Dto
 *
 * @author luyuhao
 * @since 2020/12/11 01:34
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("auth_permission")
public class AuthPermissionDto extends AuthPermissionEntity {

    /**
     * 是否更新 true/false
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "是否更新")
    private Boolean isUpdate;

    /**
     * 是否已分配
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "是否已分配")
    private Boolean isAssigned;
}
