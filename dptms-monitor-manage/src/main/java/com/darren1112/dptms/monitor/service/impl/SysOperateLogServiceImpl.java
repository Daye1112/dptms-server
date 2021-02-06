package com.darren1112.dptms.monitor.service.impl;

import com.darren1112.dptms.common.core.base.BaseService;
import com.darren1112.dptms.common.spi.sys.dto.SysOperateLogDto;
import com.darren1112.dptms.monitor.dao.SysOperateLogDao;
import com.darren1112.dptms.monitor.service.SysOperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 操作日志ServiceImpl
 *
 * @author luyuhao
 * @date 2021/02/06 20:44
 */
@Service
public class SysOperateLogServiceImpl extends BaseService implements SysOperateLogService {

    @Autowired
    private SysOperateLogDao sysOperateLogDao;

    /**
     * 插入操作日志信息
     *
     * @param dto 日志信息
     * @author luyuhao
     * @date 2021/02/06 21:08
     */
    @Override
    public void insert(SysOperateLogDto dto) {
        sysOperateLogDao.insert(dto);
    }
}
