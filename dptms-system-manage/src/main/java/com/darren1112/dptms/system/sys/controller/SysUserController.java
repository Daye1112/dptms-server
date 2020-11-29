package com.darren1112.dptms.system.sys.controller;

import com.darren1112.dptms.system.sys.service.SysUserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统用户Controller
 *
 * @author luyuhao
 * @date 2020/07/25 02:14
 */
@Slf4j
@Api(tags = "用户管理")
@RestController
@RequestMapping(value = "/sys/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

}
