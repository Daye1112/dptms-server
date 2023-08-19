package com.darren1112.dptms.system.service.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.darren1112.dptms.common.spi.service.dto.ServiceConfigProfileDto;
import com.darren1112.dptms.system.service.dao.ServiceConfigProfileDao;
import org.springframework.stereotype.Repository;

/**
 * 配置环境表Repository
 *
 * @author darren
 * @since 2022/11/25
 */
@Repository
public class ServiceConfigProfileRepository extends ServiceImpl<ServiceConfigProfileDao, ServiceConfigProfileDto> {
}
