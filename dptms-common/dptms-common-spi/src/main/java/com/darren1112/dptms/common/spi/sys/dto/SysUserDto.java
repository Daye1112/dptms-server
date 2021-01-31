package com.darren1112.dptms.common.spi.sys.dto;

import com.darren1112.dptms.common.spi.sys.entity.SysUserEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * 系统用户Dto
 *
 * @author luyuhao
 * @date 2020/07/23 02:22
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SysUserDto extends SysUserEntity {

    /**
     * 是否更新 true/false
     */
    private Boolean isUpdate;

    /**
     * 权限list
     */
    private List<SysPermissionDto> permissionList;
}
