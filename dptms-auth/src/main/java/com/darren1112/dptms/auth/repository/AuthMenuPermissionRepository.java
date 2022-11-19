package com.darren1112.dptms.auth.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.darren1112.dptms.auth.dao.AuthMenuPermissionDao;
import com.darren1112.dptms.common.spi.auth.dto.AuthMenuPermissionDto;
import org.springframework.stereotype.Repository;

/**
 * 菜单Repository
 *
 * @author luyuhao
 * @since 2022/11/19
 */
@Repository
public class AuthMenuPermissionRepository extends ServiceImpl<AuthMenuPermissionDao, AuthMenuPermissionDto> {
}
