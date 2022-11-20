package com.darren1112.dptms.monitor.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.darren1112.dptms.common.spi.monitor.dto.MonitorLoginLogDto;
import com.darren1112.dptms.monitor.dao.MonitorLoginLogDao;
import org.springframework.stereotype.Repository;

/**
 * 登录日志Repository
 *
 * @author luyuhao
 * @since 2022/11/20
 */
@Repository
public class MonitorLoginLogRepository extends ServiceImpl<MonitorLoginLogDao, MonitorLoginLogDto> {
}
