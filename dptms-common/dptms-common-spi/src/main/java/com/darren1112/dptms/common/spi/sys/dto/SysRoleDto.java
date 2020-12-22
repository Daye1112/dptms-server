package com.darren1112.dptms.common.spi.sys.dto;

import com.darren1112.dptms.common.spi.sys.entity.SysRoleEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 角色Dto
 *
 * @author luyuhao
 * @date 2020/12/13 23:05
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SysRoleDto extends SysRoleEntity {

    /**
     * 是否更新
     */
    @ApiModelProperty("是否更新")
    private Boolean isUpdate;

    /**
     * 是否已分配
     */
    @ApiModelProperty("是否已分配")
    private Boolean isAssigned;
}
