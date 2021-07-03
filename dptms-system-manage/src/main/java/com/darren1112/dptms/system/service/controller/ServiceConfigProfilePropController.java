package com.darren1112.dptms.system.service.controller;

import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.ResponseEntityUtil;
import com.darren1112.dptms.common.core.validate.ValidatorBuilder;
import com.darren1112.dptms.common.core.validate.validator.callback.common.NotEmptyValidatorCallback;
import com.darren1112.dptms.common.core.validate.validator.callback.common.NotNullValidatorCallback;
import com.darren1112.dptms.common.log.starter.annotation.Log;
import com.darren1112.dptms.common.log.starter.enums.BusinessType;
import com.darren1112.dptms.common.log.starter.enums.LogLevel;
import com.darren1112.dptms.common.security.starter.util.DptmsSecurityUtil;
import com.darren1112.dptms.common.spi.common.dto.ActiveUser;
import com.darren1112.dptms.common.spi.service.dto.ServiceConfigProfilePropDto;
import com.darren1112.dptms.system.common.enums.SystemManageErrorCodeEnum;
import com.darren1112.dptms.system.service.service.ServiceConfigProfilePropService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 配置环境属性表Controller
 *
 * @author luyuhao
 * @since 2021/03/12 01:51
 */
@Slf4j
@Api(tags = "配置环境属性管理", description = "配置环境属性管理接口")
@RestController
@RequestMapping(value = "/service/config/profile/prop")
public class ServiceConfigProfilePropController {

    @Autowired
    private ServiceConfigProfilePropService serviceConfigProfilePropService;

    /**
     * 查询配置环境属性list
     *
     * @param dto 筛选参数
     * @return {@link ServiceConfigProfilePropDto}
     * @author luyuhao
     * @since 2021/03/13 01:33
     */
    @Log(value = "查询配置环境属性list", logLevel = LogLevel.DEBUG, businessType = BusinessType.QUERY)
    @ApiOperation("查询配置环境属性list")
    @GetMapping("/list")
    public ResponseEntity<JsonResult<List<ServiceConfigProfilePropDto>>> list(ServiceConfigProfilePropDto dto) {
        ValidatorBuilder.build()
                .on(dto.getProfileId(), new NotNullValidatorCallback(SystemManageErrorCodeEnum.PROFILE_ID_NOT_NULL))
                .doValidate().checkResult();
        List<ServiceConfigProfilePropDto> list = serviceConfigProfilePropService.list(dto);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(list));
    }

    /**
     * 插入配置环境属性
     *
     * @param dto 配置环境属性信息
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/03/14 02:03
     */
    @Log(value = "插入配置环境属性", businessType = BusinessType.INSERT)
    @ApiOperation("插入配置环境属性")
    @PostMapping("/insert")
    public ResponseEntity<JsonResult<Long>> insert(ServiceConfigProfilePropDto dto) {
        ValidatorBuilder.build()
                .on(dto.getProfileId(), new NotNullValidatorCallback(SystemManageErrorCodeEnum.PROFILE_ID_NOT_NULL))
                .on(dto.getPropKey(), new NotEmptyValidatorCallback(SystemManageErrorCodeEnum.PROP_KEY_NOT_NULL))
                .on(dto.getPropValue(), new NotEmptyValidatorCallback(SystemManageErrorCodeEnum.PROP_VALUE_NOT_NULL))
                .doValidate().checkResult();
        // 设置创建者信息
        ActiveUser activeUser = DptmsSecurityUtil.get();
        dto.setCreater(activeUser.getId());
        dto.setUpdater(activeUser.getId());
        Long id = serviceConfigProfilePropService.insert(dto);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(id));
    }

    /**
     * 更新配置环境属性
     *
     * @param dto 配置环境信息
     * @return {@link JsonResult}
     * @author luyuhao
     * @since 2021/03/14 02:03
     */
    @Log(value = "更新配置环境属性", businessType = BusinessType.UPDATE)
    @ApiOperation("更新配置环境属性")
    @PostMapping("/update")
    public ResponseEntity<JsonResult> update(ServiceConfigProfilePropDto dto) {
        ValidatorBuilder.build()
                .on(dto.getId(), new NotNullValidatorCallback(SystemManageErrorCodeEnum.ID_NOT_NULL))
                .on(dto.getProfileId(), new NotNullValidatorCallback(SystemManageErrorCodeEnum.PROFILE_ID_NOT_NULL))
                .on(dto.getPropKey(), new NotEmptyValidatorCallback(SystemManageErrorCodeEnum.PROP_KEY_NOT_NULL))
                .on(dto.getPropValue(), new NotEmptyValidatorCallback(SystemManageErrorCodeEnum.PROP_VALUE_NOT_NULL))
                .doValidate().checkResult();
        // 设置创建者信息
        ActiveUser activeUser = DptmsSecurityUtil.get();
        dto.setCreater(activeUser.getId());
        dto.setUpdater(activeUser.getId());
        serviceConfigProfilePropService.update(dto);
        return ResponseEntityUtil.ok(JsonResult.buildSuccess());
    }

    /**
     * 根据id删除配置环境属性
     *
     * @param id 配置环境id
     * @return {@link JsonResult}
     * @author luyuhao
     * @since 2021/03/14 02:03
     */
    @Log(value = "删除配置环境属性", businessType = BusinessType.DELETE)
    @ApiOperation("删除配置环境属性")
    @GetMapping("/deleteById")
    public ResponseEntity<JsonResult> deleteById(@RequestParam(value = "id", required = false) Long id) {
        ValidatorBuilder.build()
                .on(id, new NotNullValidatorCallback(SystemManageErrorCodeEnum.ID_NOT_NULL))
                .doValidate().checkResult();
        // 设置创建者信息
        ActiveUser activeUser = DptmsSecurityUtil.get();
        serviceConfigProfilePropService.deleteById(id, activeUser.getId());
        return ResponseEntityUtil.ok(JsonResult.buildSuccess());
    }
}
