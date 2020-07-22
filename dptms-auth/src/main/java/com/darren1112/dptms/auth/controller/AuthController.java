package com.darren1112.dptms.auth.controller;

import com.darren1112.dptms.auth.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 权限相关Controller
 *
 * @author luyuhao
 * @date 2020/07/20 00:06
 */
@Slf4j
@RestController
@RequestMapping(value = "/")
public class AuthController {

    @Autowired
    private SysUserService sysUserService;

}
