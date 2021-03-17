package com.darren1112.dptms.system.service.controller;

import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.ResponseEntityUtil;
import com.darren1112.dptms.common.core.validate.ValidatorBuilder;
import com.darren1112.dptms.common.core.validate.validator.callback.common.NotNullValidatorCallback;
import com.darren1112.dptms.common.log.starter.annotation.Log;
import com.darren1112.dptms.common.log.starter.enums.BusinessType;
import com.darren1112.dptms.common.log.starter.enums.LogLevel;
import com.darren1112.dptms.common.spi.service.dto.ServiceConfigReleasePropDto;
import com.darren1112.dptms.system.common.enums.SystemManageErrorCodeEnum;
import com.darren1112.dptms.system.service.service.ServiceConfigReleasePropService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    /**
     * 查询配置发布属性list
     *
     * @param dto 筛选参数
     * @return {@link ServiceConfigReleasePropDto}
     * @author luyuhao
     * @since 2021/03/13 01:33
     */
    @Log(value = "查询配置发布属性list", logLevel = LogLevel.DEBUG, businessType = BusinessType.QUERY)
    @ApiOperation("查询配置发布属性list")
    @GetMapping("/list")
    public ResponseEntity<JsonResult<List<ServiceConfigReleasePropDto>>> list(ServiceConfigReleasePropDto dto) {
        ValidatorBuilder.build()
                .on(dto.getReleaseId(), new NotNullValidatorCallback(SystemManageErrorCodeEnum.RELEASE_ID_NOT_NULL))
                .doValidate().checkResult();
        List<ServiceConfigReleasePropDto> list = serviceConfigReleasePropService.list(dto);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(list));
    }
}
