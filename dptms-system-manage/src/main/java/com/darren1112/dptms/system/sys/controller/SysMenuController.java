package com.darren1112.dptms.system.sys.controller;

import com.darren1112.dptms.system.sys.service.SysMenuService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 菜单Controller
 *
 * @author luyuhao
 * @since 2020/12/12 17:28
 */
@Slf4j
@Api(tags = "菜单管理")
@RestController
@RequestMapping(value = "/sys/menu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;
}
