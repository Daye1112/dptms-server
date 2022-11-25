package com.darren1112.dptms.system.service.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.darren1112.dptms.common.spi.service.dto.ServiceApplicationDto;
import com.darren1112.dptms.system.service.dao.ServiceApplicationDao;
import org.springframework.stereotype.Repository;

/**
 * 应用表Repository
 *
 * @author luyuhao
 * @since 2022/11/25
 */
@Repository
public class ServiceApplicationRepository extends ServiceImpl<ServiceApplicationDao, ServiceApplicationDto> {
}
