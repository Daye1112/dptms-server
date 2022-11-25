package com.darren1112.dptms.system.service.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.darren1112.dptms.common.spi.service.dto.ServiceConfigProfilePropDto;
import com.darren1112.dptms.system.service.dao.ServiceConfigProfilePropDao;
import org.springframework.stereotype.Repository;

/**
 * 配置环境属性表Repository
 *
 * @author luyuhao
 * @since 2022/11/25
 */
@Repository
public class ServiceConfigProfilePropRepository extends ServiceImpl<ServiceConfigProfilePropDao, ServiceConfigProfilePropDto> {
}
