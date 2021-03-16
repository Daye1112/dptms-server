package com.darren1112.dptms.monitor.service.impl;

import com.darren1112.dptms.common.core.base.BaseService;
import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.sys.dto.SysLoginLogDto;
import com.darren1112.dptms.monitor.dao.SysLoginLogDao;
import com.darren1112.dptms.monitor.service.SysLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 登录日志ServiceImpl
 *
 * @author luyuhao
 * @since 2021/02/06 20:44
 */
@Service
@Transactional(rollbackFor = Throwable.class, readOnly = true)
public class SysLoginLogServiceImpl extends BaseService implements SysLoginLogService {

    @Autowired
    private SysLoginLogDao sysLoginLogDao;

    /**
     * 插入登录日志信息
     *
     * @param dto 日志信息
     * @author luyuhao
     * @since 2021/02/06 20:53
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void insert(SysLoginLogDto dto) {
        sysLoginLogDao.insert(dto);
    }

    /**
     * 分页查询登录日志
     *
     * @param pageParam 分页参数
     * @param dto       查询条件
     * @return {@link SysLoginLogDto}
     * @author luyuhao
     * @since 2021/02/10 00:13
     */
    @Override
    public PageBean<SysLoginLogDto> listPage(PageParam pageParam, SysLoginLogDto dto) {
        List<SysLoginLogDto> list = sysLoginLogDao.listPage(pageParam, dto);
        Long count = sysLoginLogDao.listPageCount(dto);
        return createPageBean(pageParam, count, list);
    }
}
