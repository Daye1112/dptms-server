package com.darren1112.dptms.common.spi.sys.dto;

import com.darren1112.dptms.common.spi.sys.entity.SysRoleEntity;
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
     * 是否更新 true/false
     */
    private Boolean isUpdate;
}
