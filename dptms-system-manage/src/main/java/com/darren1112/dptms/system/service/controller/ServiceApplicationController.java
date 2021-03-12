package com.darren1112.dptms.system.service.controller;

import com.darren1112.dptms.common.core.base.BaseController;
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
import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.service.dto.ServiceApplicationDto;
import com.darren1112.dptms.system.common.enums.SystemManageErrorCodeEnum;
import com.darren1112.dptms.system.service.service.ServiceApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 分页查询服务应用
     *
     * @param dto       筛选参数
     * @param pageParam 分页参数
     * @return {@link ServiceApplicationDto}
     * @author luyuhao
     * @date 2021/03/12 17:33
     */
    @Log(value = "分页查询服务应用", logLevel = LogLevel.DEBUG, businessType = BusinessType.QUERY)
    @ApiOperation("分页查询服务应用")
    @GetMapping("/listPage")
    public ResponseEntity<JsonResult<PageBean<ServiceApplicationDto>>> listPage(PageParam pageParam,
                                                                                ServiceApplicationDto dto) {
        PageBean<ServiceApplicationDto> pageBean = serviceApplicationService.listPage(getPageParam(pageParam), dto);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(pageBean));
    }

    /**
     * 插入服务应用
     *
     * @param dto 服务信息
     * @return {@link JsonResult}
     * @author luyuhao
     * @date 2021/03/12 23:33
     */
    @Log(value = "插入服务应用", businessType = BusinessType.INSERT)
    @ApiOperation("插入服务应用")
    @PostMapping("/insert")
    public ResponseEntity<JsonResult<Long>> insert(ServiceApplicationDto dto) {
        ValidatorBuilder.build()
                .on(dto.getOrgId(), new NotNullValidatorCallback(SystemManageErrorCodeEnum.ORG_ID_NOT_NULL))
                .on(dto.getAppCode(), new NotEmptyValidatorCallback(SystemManageErrorCodeEnum.APP_CODE_NOT_NULL))
                .on(dto.getAppName(), new NotEmptyValidatorCallback(SystemManageErrorCodeEnum.APP_NAME_NOT_NULL))
                .on(dto.getAppType(), new NotEmptyValidatorCallback(SystemManageErrorCodeEnum.APP_TYPE_NOT_NULL))
                .doValidate().checkResult();
        // 设置创建者信息
        ActiveUser activeUser = DptmsSecurityUtil.get();
        dto.setCreater(activeUser.getId());
        dto.setUpdater(activeUser.getId());
        Long id = serviceApplicationService.insert(dto);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(id));
    }

    /**
     * 更新服务应用
     *
     * @param dto 服务信息
     * @return {@link JsonResult}
     * @author luyuhao
     * @date 2021/03/12 23:33
     */
    @Log(value = "插入服务应用", businessType = BusinessType.INSERT)
    @ApiOperation("插入服务应用")
    @PostMapping("/update")
    public ResponseEntity<JsonResult> update(ServiceApplicationDto dto) {
        ValidatorBuilder.build()
                .on(dto.getOrgId(), new NotNullValidatorCallback(SystemManageErrorCodeEnum.ORG_ID_NOT_NULL))
                .on(dto.getAppCode(), new NotEmptyValidatorCallback(SystemManageErrorCodeEnum.APP_CODE_NOT_NULL))
                .on(dto.getAppName(), new NotEmptyValidatorCallback(SystemManageErrorCodeEnum.APP_NAME_NOT_NULL))
                .on(dto.getAppType(), new NotEmptyValidatorCallback(SystemManageErrorCodeEnum.APP_TYPE_NOT_NULL))
                .doValidate().checkResult();
        // 设置创建者信息
        ActiveUser activeUser = DptmsSecurityUtil.get();
        dto.setCreater(activeUser.getId());
        dto.setUpdater(activeUser.getId());
        serviceApplicationService.update(dto);
        return ResponseEntityUtil.ok(JsonResult.buildSuccess());
    }

    /**
     * 根据id删除服务应用
     *
     * @param id 服务应用id
     * @return {@link JsonResult}
     * @author luyuhao
     * @date 2021/03/12 23:33
     */
    @Log(value = "删除服务应用", businessType = BusinessType.DELETE)
    @ApiOperation("删除服务应用")
    @PostMapping("/deleteById")
    public ResponseEntity<JsonResult> deleteById(@RequestParam(value = "id", required = false) Long id) {
        ValidatorBuilder.build()
                .on(id, new NotNullValidatorCallback(SystemManageErrorCodeEnum.ID_NOT_NULL))
                .doValidate().checkResult();
        // 设置创建者信息
        ActiveUser activeUser = DptmsSecurityUtil.get();
        serviceApplicationService.deleteById(id, activeUser.getId());
        return ResponseEntityUtil.ok(JsonResult.buildSuccess());
    }
}
