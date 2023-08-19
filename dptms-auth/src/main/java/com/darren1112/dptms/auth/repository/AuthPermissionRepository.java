package com.darren1112.dptms.auth.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.darren1112.dptms.auth.dao.AuthPermissionDao;
import com.darren1112.dptms.common.spi.auth.dto.AuthPermissionDto;
import org.springframework.stereotype.Repository;

/**
 * 权限Repository
 *
 * @author darren
 * @since 2022/11/19
 */
@Repository
public class AuthPermissionRepository extends ServiceImpl<AuthPermissionDao, AuthPermissionDto> {
}
