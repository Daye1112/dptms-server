package com.darren1112.dptms.system.service.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.darren1112.dptms.common.spi.service.dto.ServiceConfigReleaseDto;
import com.darren1112.dptms.system.service.dao.ServiceConfigReleaseDao;
import org.springframework.stereotype.Repository;

/**
 * 配置发布表Repository
 *
 * @author darren
 * @since 2022/11/25
 */
@Repository
public class ServiceConfigReleaseRepository extends ServiceImpl<ServiceConfigReleaseDao, ServiceConfigReleaseDto> {
}
