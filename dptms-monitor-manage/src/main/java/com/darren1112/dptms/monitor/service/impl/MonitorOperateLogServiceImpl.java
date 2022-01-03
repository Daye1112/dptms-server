package com.darren1112.dptms.monitor.service.impl;

import com.darren1112.dptms.common.core.base.BaseService;
import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.monitor.dto.MonitorOperateLogDto;
import com.darren1112.dptms.monitor.dao.MonitorOperateLogDao;
import com.darren1112.dptms.monitor.service.MonitorOperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 操作日志ServiceImpl
 *
 * @author luyuhao
 * @since 2021/02/06 20:44
 */
@Service
@Transactional(rollbackFor = Throwable.class, readOnly = true)
public class MonitorOperateLogServiceImpl extends BaseService implements MonitorOperateLogService {

    @Autowired
    private MonitorOperateLogDao monitorOperateLogDao;

    /**
     * 插入操作日志信息
     *
     * @param dto 日志信息
     * @author luyuhao
     * @since 2021/02/06 21:08
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void insert(MonitorOperateLogDto dto) {
        monitorOperateLogDao.insert(dto);
    }

    /**
     * 分页查询操作日志
     *
     * @param dto       筛选参数
     * @param pageParam 分页参数
     * @return {@link MonitorOperateLogDto}
     * @author luyuhao
     * @since 20/12/10 01:08
     */
    @Override
    public PageBean<MonitorOperateLogDto> listPage(PageParam pageParam, MonitorOperateLogDto dto) {
        List<MonitorOperateLogDto> list = monitorOperateLogDao.listPage(pageParam, dto);
        Long count = monitorOperateLogDao.listPageCount(dto);
        return createPageBean(pageParam, count, list);
    }
}
