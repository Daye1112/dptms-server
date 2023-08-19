package com.darren1112.dptms.auth.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.darren1112.dptms.auth.dao.AuthRoleDao;
import com.darren1112.dptms.common.spi.auth.dto.AuthRoleDto;
import org.springframework.stereotype.Repository;

/**
 * 角色Repository
 *
 * @author darren
 * @since 2022/11/19
 */
@Repository
public class AuthRoleRepository extends ServiceImpl<AuthRoleDao, AuthRoleDto> {


}
