package com.darren1112.dptms.auth.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.darren1112.dptms.auth.dao.AuthUserOrganizationDao;
import com.darren1112.dptms.common.spi.auth.dto.AuthUserOrganizationDto;
import org.springframework.stereotype.Repository;

/**
 * 用户组织Repository
 *
 * @author luyuhao
 * @since 2022/11/19
 */
@Repository
public class AuthUserOrganizationRepository extends ServiceImpl<AuthUserOrganizationDao, AuthUserOrganizationDto> {
}
