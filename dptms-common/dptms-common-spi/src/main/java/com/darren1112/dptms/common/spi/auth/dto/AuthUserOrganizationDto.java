package com.darren1112.dptms.common.spi.auth.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.darren1112.dptms.common.spi.auth.entity.AuthUserOrganizationEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 用户组织Dto
 *
 * @author darren
 * @since 2020/12/13 18:06
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("auth_user_organization")
public class AuthUserOrganizationDto extends AuthUserOrganizationEntity {
}
