package com.darren1112.dptms.system.service.controller;

import com.darren1112.dptms.common.core.base.BaseController;
import com.darren1112.dptms.system.service.service.ServiceApplicationService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 应用表Controller
 *
 * @author luyuhao
 * @date 2021/03/12 01:33
 */
@Slf4j
@Api(tags = "服务管理", description = "服务管理接口")
@RestController
@RequestMapping(value = "/service/application")
public class ServiceApplicationController extends BaseController {

    @Autowired
    private ServiceApplicationService serviceApplicationService;
}
