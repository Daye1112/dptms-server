package com.darren1112.dptms.common.spi.auth.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.darren1112.dptms.common.spi.auth.entity.AuthMenuPermissionEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 菜单权限Dto
 *
 * @author baojiazhong
 * @since 2020/12/22 23:20
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("auth_menu_permission")
public class AuthMenuPermissionDto extends AuthMenuPermissionEntity {

    private static final long serialVersionUID = 1L;
}
