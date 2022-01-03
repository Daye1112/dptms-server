package com.darren1112.dptms.common.spi.auth.dto;

import com.darren1112.dptms.common.spi.auth.entity.SysRoleMenuEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 角色菜单Dto
 *
 * @author luyuhao
 * @since 2020/12/13 23:06
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SysRoleMenuDto extends SysRoleMenuEntity {
}
