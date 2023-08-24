package com.darren1112.dptms.auth.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.darren1112.dptms.auth.dao.AuthOrganizationDao;
import com.darren1112.dptms.common.spi.auth.dto.AuthOrganizationDto;
import org.springframework.stereotype.Repository;

/**
 * 组织Repository
 *
 * @author darren
 * @since 2022/11/19
 */
@Repository
public class AuthOrganizationRepository extends ServiceImpl<AuthOrganizationDao, AuthOrganizationDto> {

}
