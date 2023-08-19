package com.darren1112.dptms.monitor.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.darren1112.dptms.common.spi.monitor.dto.MonitorOperateLogDto;
import com.darren1112.dptms.monitor.dao.MonitorOperateLogDao;
import org.springframework.stereotype.Repository;

/**
 * 操作日志Repository
 *
 * @author darren
 * @since 2022/11/20
 */
@Repository
public class MonitorOperateLogRepository extends ServiceImpl<MonitorOperateLogDao, MonitorOperateLogDto> {
}
