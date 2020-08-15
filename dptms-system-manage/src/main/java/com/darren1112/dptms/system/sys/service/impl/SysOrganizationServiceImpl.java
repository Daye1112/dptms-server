package com.darren1112.dptms.system.sys.service.impl;

import com.darren1112.dptms.system.sys.dao.SysOrganizationDao;
import com.darren1112.dptms.system.sys.service.SysOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 组织ServiceImpl
 *
 * @author luyuhao
 * @date 2020/08/16 01:43
 */
@Service
public class SysOrganizationServiceImpl implements SysOrganizationService {

    @Autowired
    private SysOrganizationDao sysOrganizationDao;
}
