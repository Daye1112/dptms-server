package com.darren1112.dptms.auth.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.darren1112.dptms.auth.dao.AuthRoleMenuDao;
import com.darren1112.dptms.common.spi.auth.dto.AuthRoleMenuDto;
import org.springframework.stereotype.Repository;

/**
 * 角色菜单Repository
 *
 * @author darren
 * @since 2022/11/19
 */
@Repository
public class AuthRoleMenuRepository extends ServiceImpl<AuthRoleMenuDao, AuthRoleMenuDto> {
}
