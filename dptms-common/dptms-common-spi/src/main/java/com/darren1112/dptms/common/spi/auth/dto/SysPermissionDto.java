package com.darren1112.dptms.common.spi.auth.dto;

import com.darren1112.dptms.common.spi.auth.entity.SysPermissionEntity;
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
public class SysPermissionDto extends SysPermissionEntity {

    /**
     * 是否更新 true/false
     */
    @ApiModelProperty(value = "是否更新")
    private Boolean isUpdate;

    /**
     * 是否已分配
     */
    @ApiModelProperty(value = "是否已分配")
    private Boolean isAssigned;
}
