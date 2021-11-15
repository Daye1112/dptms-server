package com.darren1112.dptms.common.spi.sys.dto;

import com.darren1112.dptms.common.spi.sys.entity.SysUserOrganizationEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 用户组织Dto
 *
 * @author luyuhao
 * @since 2020/12/13 18:06
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SysUserOrganizationDto extends SysUserOrganizationEntity {
}
