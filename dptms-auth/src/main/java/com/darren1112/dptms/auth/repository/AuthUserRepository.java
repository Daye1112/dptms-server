package com.darren1112.dptms.auth.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.darren1112.dptms.auth.dao.AuthUserDao;
import com.darren1112.dptms.common.spi.auth.dto.AuthUserDto;
import org.springframework.stereotype.Repository;

/**
 * 系统用户Repository
 *
 * @author darren
 * @since 2022/11/19
 */
@Repository
public class AuthUserRepository extends ServiceImpl<AuthUserDao, AuthUserDto> {
}
