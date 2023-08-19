package com.darren1112.dptms.auth.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.darren1112.dptms.auth.dao.AuthUserRoleDao;
import com.darren1112.dptms.common.spi.auth.dto.AuthUserRoleDto;
import org.springframework.stereotype.Repository;

/**
 * 用户角色Repository
 *
 * @author darren
 * @since 2022/11/19
 */
@Repository
public class AuthUserRoleRepository extends ServiceImpl<AuthUserRoleDao, AuthUserRoleDto> {
}
