package com.darren1112.dptms.common.spi.auth.dto;

import com.darren1112.dptms.common.spi.auth.entity.AuthUserOrganizationEntity;
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
public class AuthUserOrganizationDto extends AuthUserOrganizationEntity {
}
