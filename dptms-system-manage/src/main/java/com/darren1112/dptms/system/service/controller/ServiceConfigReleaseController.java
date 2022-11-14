package com.darren1112.dptms.system.service.controller;

import com.darren1112.dptms.common.core.base.BaseController;
import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.ResponseEntityUtil;
import com.darren1112.dptms.common.core.validate.ValidatorBuilder;
import com.darren1112.dptms.common.core.validate.validator.callback.common.NotNullValidatorCallback;
import com.darren1112.dptms.sdk.starter.log.annotation.Log;
import com.darren1112.dptms.sdk.starter.log.enums.BusinessType;
import com.darren1112.dptms.sdk.starter.log.enums.LogLevel;
import com.darren1112.dptms.sdk.starter.security.util.DptmsSecurityUtil;
import com.darren1112.dptms.common.spi.common.dto.ActiveUser;
import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.service.dto.ServiceConfigReleaseDto;
import com.darren1112.dptms.system.common.enums.SystemManageErrorCodeEnum;
import com.darren1112.dptms.system.service.service.ServiceConfigReleaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 配置发布表Controller
 *
 * @author luyuhao
 * @since 2021/03/12 01:51
 */
@Slf4j
@Api(tags = "配置发布管理", description = "配置发布管理接口")
@RestController
@RequestMapping(value = "/service/config/release")
public class ServiceConfigReleaseController extends BaseController {

    @Autowired
    private ServiceConfigReleaseService serviceConfigReleaseService;

    /**
     * 分页查询发布列表
     *
     * @param dto       筛选参数
     * @param pageParam 分页参数
     * @return {@link ServiceConfigReleaseDto}
     * @author luyuhao
     * @since 2021/03/16 08:33
     */
    @Log(value = "分页查询发布列表", logLevel = LogLevel.DEBUG, businessType = BusinessType.QUERY)
    @ApiOperation("分页查询发布列表")
    @GetMapping("/listPage")
    public ResponseEntity<JsonResult<PageBean<ServiceConfigReleaseDto>>> listPage(PageParam pageParam,
                                                                                  ServiceConfigReleaseDto dto) {
        ValidatorBuilder.build()
                .on(dto.getApplicationId(), new NotNullValidatorCallback(SystemManageErrorCodeEnum.APP_ID_NOT_NULL))
                .on(dto.getProfileId(), new NotNullValidatorCallback(SystemManageErrorCodeEnum.PROFILE_ID_NOT_NULL))
                .doValidate().checkResult();
        PageBean<ServiceConfigReleaseDto> pageBean = serviceConfigReleaseService.listPage(getPageParam(pageParam), dto);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(pageBean));
    }

    /**
     * 插入发布信息
     *
     * @param dto 服务信息
     * @return {@link JsonResult}
     * @author luyuhao
     * @since 2021/03/16 08:33
     */
    @Log(value = "插入发布信息", businessType = BusinessType.INSERT)
    @ApiOperation("插入发布信息")
    @PostMapping("/insert")
    public ResponseEntity<JsonResult<Long>> insert(ServiceConfigReleaseDto dto) {
        ValidatorBuilder.build()
                .on(dto.getApplicationId(), new NotNullValidatorCallback(SystemManageErrorCodeEnum.APP_ID_NOT_NULL))
                .on(dto.getProfileId(), new NotNullValidatorCallback(SystemManageErrorCodeEnum.PROFILE_ID_NOT_NULL))
                .on(dto.getReleaseType(), new NotNullValidatorCallback(SystemManageErrorCodeEnum.RELEASE_TYPE_NOT_NULL))
                .doValidate().checkResult();
        // 设置创建者信息
        ActiveUser activeUser = DptmsSecurityUtil.get();
        dto.setCreater(activeUser.getId());
        dto.setUpdater(activeUser.getId());
        Long id = serviceConfigReleaseService.insert(dto);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(id));
    }


    /**
     * 删除发布信息
     *
     * @param id 发布id
     * @return {@link JsonResult}
     * @author luyuhao
     * @since 2021/03/16 08:33
     */
    @Log(value = "删除发布信息", businessType = BusinessType.DELETE)
    @ApiOperation("删除发布信息")
    @GetMapping("/deleteById")
    public ResponseEntity<JsonResult> deleteById(@RequestParam(value = "id", required = false) Long id) {
        ValidatorBuilder.build()
                .on(id, new NotNullValidatorCallback(SystemManageErrorCodeEnum.ID_NOT_NULL))
                .doValidate().checkResult();
        // 设置创建者信息
        ActiveUser activeUser = DptmsSecurityUtil.get();
        serviceConfigReleaseService.deleteById(id, activeUser.getId());
        return ResponseEntityUtil.ok(JsonResult.buildSuccess());
    }
}
