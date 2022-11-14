package com.darren1112.dptms.system.service.controller;

import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.ResponseEntityUtil;
import com.darren1112.dptms.common.core.validate.ValidatorBuilder;
import com.darren1112.dptms.common.core.validate.validator.callback.common.NotEmptyValidatorCallback;
import com.darren1112.dptms.common.core.validate.validator.callback.common.NotNullValidatorCallback;
import com.darren1112.dptms.sdk.starter.log.annotation.Log;
import com.darren1112.dptms.sdk.starter.log.enums.BusinessType;
import com.darren1112.dptms.sdk.starter.log.enums.LogLevel;
import com.darren1112.dptms.sdk.starter.security.util.DptmsSecurityUtil;
import com.darren1112.dptms.common.spi.common.dto.ActiveUser;
import com.darren1112.dptms.common.spi.service.dto.ServiceConfigProfileDto;
import com.darren1112.dptms.system.common.enums.SystemManageErrorCodeEnum;
import com.darren1112.dptms.system.service.service.ServiceConfigProfileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 配置环境表Controller
 *
 * @author luyuhao
 * @since 2021/03/12 01:51
 */
@Slf4j
@Api(tags = "配置环境管理", description = "配置环境管理接口")
@RestController
@RequestMapping(value = "/service/config/profile")
public class ServiceConfigProfileController {

    @Autowired
    private ServiceConfigProfileService serviceConfigProfileService;

    /**
     * 查询配置环境list
     *
     * @param dto 筛选参数
     * @return {@link ServiceConfigProfileDto}
     * @author luyuhao
     * @since 2021/03/13 01:33
     */
    @Log(value = "查询配置环境list", logLevel = LogLevel.DEBUG, businessType = BusinessType.QUERY)
    @ApiOperation("查询配置环境list")
    @GetMapping("/list")
    public ResponseEntity<JsonResult<List<ServiceConfigProfileDto>>> list(ServiceConfigProfileDto dto) {
        ValidatorBuilder.build()
                .on(dto.getApplicationId(), new NotNullValidatorCallback(SystemManageErrorCodeEnum.APP_ID_NOT_NULL))
                .doValidate().checkResult();
        List<ServiceConfigProfileDto> list = serviceConfigProfileService.list(dto);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(list));
    }

    /**
     * 插入配置环境
     *
     * @param dto 配置环境信息
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/03/14 02:03
     */
    @Log(value = "插入配置环境", businessType = BusinessType.INSERT)
    @ApiOperation("插入配置环境")
    @PostMapping("/insert")
    public ResponseEntity<JsonResult<Long>> insert(ServiceConfigProfileDto dto) {
        ValidatorBuilder.build()
                .on(dto.getApplicationId(), new NotNullValidatorCallback(SystemManageErrorCodeEnum.APP_ID_NOT_NULL))
                .on(dto.getProfileCode(), new NotEmptyValidatorCallback(SystemManageErrorCodeEnum.PROFILE_CODE_NOT_NULL))
                .on(dto.getProfileName(), new NotEmptyValidatorCallback(SystemManageErrorCodeEnum.PROFILE_NAME_NOT_NULL))
                .doValidate().checkResult();
        // 设置创建者信息
        ActiveUser activeUser = DptmsSecurityUtil.get();
        dto.setCreater(activeUser.getId());
        dto.setUpdater(activeUser.getId());
        Long id = serviceConfigProfileService.insert(dto);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(id));
    }

    /**
     * 更新配置环境
     *
     * @param dto 配置环境信息
     * @return {@link JsonResult}
     * @author luyuhao
     * @since 2021/03/14 02:03
     */
    @Log(value = "更新配置环境", businessType = BusinessType.UPDATE)
    @ApiOperation("更新配置环境")
    @PostMapping("/update")
    public ResponseEntity<JsonResult> update(ServiceConfigProfileDto dto) {
        ValidatorBuilder.build()
                .on(dto.getId(), new NotNullValidatorCallback(SystemManageErrorCodeEnum.ID_NOT_NULL))
                .on(dto.getApplicationId(), new NotNullValidatorCallback(SystemManageErrorCodeEnum.APP_ID_NOT_NULL))
                .on(dto.getProfileCode(), new NotEmptyValidatorCallback(SystemManageErrorCodeEnum.PROFILE_CODE_NOT_NULL))
                .on(dto.getProfileName(), new NotEmptyValidatorCallback(SystemManageErrorCodeEnum.PROFILE_NAME_NOT_NULL))
                .doValidate().checkResult();
        // 设置创建者信息
        ActiveUser activeUser = DptmsSecurityUtil.get();
        dto.setCreater(activeUser.getId());
        dto.setUpdater(activeUser.getId());
        serviceConfigProfileService.update(dto);
        return ResponseEntityUtil.ok(JsonResult.buildSuccess());
    }

    /**
     * 根据id删除配置环境
     *
     * @param id 配置环境id
     * @return {@link JsonResult}
     * @author luyuhao
     * @since 2021/03/14 02:03
     */
    @Log(value = "删除配置环境", businessType = BusinessType.DELETE)
    @ApiOperation("删除配置环境")
    @GetMapping("/deleteById")
    public ResponseEntity<JsonResult> deleteById(@RequestParam(value = "id", required = false) Long id) {
        ValidatorBuilder.build()
                .on(id, new NotNullValidatorCallback(SystemManageErrorCodeEnum.ID_NOT_NULL))
                .doValidate().checkResult();
        // 设置创建者信息
        ActiveUser activeUser = DptmsSecurityUtil.get();
        serviceConfigProfileService.deleteById(id, activeUser.getId());
        return ResponseEntityUtil.ok(JsonResult.buildSuccess());
    }
}
