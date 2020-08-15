package com.darren1112.dptms.system.sys.controller;

import com.darren1112.dptms.system.sys.service.SysOrganizationService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 组织Controller
 *
 * @author luyuhao
 * @date 2020/08/16 01:43
 */
@Slf4j
@Api(tags = "组织管理")
@RestController
public class SysOrganizationController {

    @Autowired
    private SysOrganizationService sysOrganizationService;
}
