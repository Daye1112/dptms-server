package com.darren1112.dptms.system.sys.controller;

import com.darren1112.dptms.common.security.starter.util.TokenUtil;
import com.darren1112.dptms.system.sys.service.SysRoleService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色Controller
 *
 * @author luyuhao
 * @date 2020/12/13 23:11
 */
@Slf4j
@Api(tags = "角色管理")
@RestController
@RequestMapping(value = "/sys/role")
public class SysRoleController {

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private SysRoleService sysRoleService;
}
