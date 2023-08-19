package com.darren1112.dptms.common.spi.auth.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.darren1112.dptms.common.spi.auth.entity.AuthUserRoleEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 用户角色Dto
 *
 * @author darren
 * @since 20/12/23 01:50
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("auth_user_role")
public class AuthUserRoleDto extends AuthUserRoleEntity {

    private static final long serialVersionUID = 1L;
}
