package com.darren1112.dptms.system.service.controller;

import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.ResponseEntityUtil;
import com.darren1112.dptms.common.core.validate.ValidatorBuilder;
import com.darren1112.dptms.common.core.validate.validator.callback.common.NotNullValidatorCallback;
import com.darren1112.dptms.common.log.starter.annotation.Log;
import com.darren1112.dptms.common.log.starter.enums.BusinessType;
import com.darren1112.dptms.common.log.starter.enums.LogLevel;
import com.darren1112.dptms.common.spi.service.dto.ServiceConfigProfileDto;
import com.darren1112.dptms.system.common.enums.SystemManageErrorCodeEnum;
import com.darren1112.dptms.system.service.service.ServiceConfigProfilePropService;
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
 * 配置环境表Controller
 *
 * @author luyuhao
 * @date 2021/03/12 01:51
 */
@Slf4j
@Api(tags = "配置环境管理", description = "配置环境管理接口")
@RestController
@RequestMapping(value = "/service/config/profile")
public class ServiceConfigProfileController {

    @Autowired
    private ServiceConfigProfilePropService serviceConfigProfilePropService;

    /**
     * 查询服务应用list
     *
     * @param dto 筛选参数
     * @return {@link ServiceConfigProfileDto}
     * @author luyuhao
     * @date 2021/03/13 01:33
     */
    @Log(value = "查询服务应用list", logLevel = LogLevel.DEBUG, businessType = BusinessType.QUERY)
    @ApiOperation("查询服务应用list")
    @GetMapping("/list")
    public ResponseEntity<JsonResult<List<ServiceConfigProfileDto>>> list(ServiceConfigProfileDto dto) {
        ValidatorBuilder.build()
                .on(dto.getApplicationId(), new NotNullValidatorCallback(SystemManageErrorCodeEnum.APP_ID_NOT_NULL))
                .doValidate().checkResult();
        List<ServiceConfigProfileDto> list = serviceConfigProfilePropService.list(dto);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(list));
    }
}
