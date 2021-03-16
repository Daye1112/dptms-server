package com.darren1112.dptms.system.service.controller;

import com.darren1112.dptms.system.service.service.ServiceConfigReleasePropService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 配置发布属性表Controller
 *
 * @author luyuhao
 * @since 2021/03/12 01:51
 */
@Slf4j
@Api(tags = "配置发布属性管理", description = "配置发布属性管理接口")
@RestController
@RequestMapping(value = "/service/config/release/prop")
public class ServiceConfigReleasePropController {

    @Autowired
    private ServiceConfigReleasePropService serviceConfigReleasePropService;
}
