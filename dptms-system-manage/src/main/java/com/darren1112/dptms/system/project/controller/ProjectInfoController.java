package com.darren1112.dptms.system.project.controller;

import com.darren1112.dptms.common.core.base.BaseController;
import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.ResponseEntityUtil;
import com.darren1112.dptms.common.core.validate.ValidatorBuilder;
import com.darren1112.dptms.common.core.validate.validator.callback.common.NotBlankValidatorCallback;
import com.darren1112.dptms.common.core.validate.validator.callback.common.NotNullValidatorCallback;
import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.project.dto.ProjectInfoDto;
import com.darren1112.dptms.sdk.starter.log.annotation.Log;
import com.darren1112.dptms.sdk.starter.log.enums.BusinessType;
import com.darren1112.dptms.sdk.starter.log.enums.LogLevel;
import com.darren1112.dptms.sdk.starter.security.model.ActiveUser;
import com.darren1112.dptms.sdk.starter.security.util.SecurityUserUtil;
import com.darren1112.dptms.system.common.enums.SystemManageErrorCodeEnum;
import com.darren1112.dptms.system.project.service.ProjectInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 项目信息Controller
 *
 * @author darren
 * @since 2023/08/13
 */
@Slf4j
@Api(tags = "项目管理", description = "项目管理接口")
@RestController
@RequestMapping(value = "/project/info")
public class ProjectInfoController extends BaseController {

    @Autowired
    private ProjectInfoService projectInfoService;

    /**
     * 分页查询项目信息
     *
     * @param pageParam 分页参数
     * @param dto       查询条件
     * @return {@link ProjectInfoDto}
     * @author darren
     * @since 2023/08/13
     */
    @GetMapping("/listPage")
    @ApiOperation("分页查询项目信息")
    @Log(value = "分页查询项目信息", logLevel = LogLevel.DEBUG, businessType = BusinessType.QUERY)
    public ResponseEntity<JsonResult<PageBean<ProjectInfoDto>>> listPage(PageParam pageParam,
                                                                         ProjectInfoDto dto) {
        PageBean<ProjectInfoDto> pageBean = projectInfoService.listPage(getPageParam(pageParam), dto);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(pageBean));
    }

    /**
     * 新增项目信息
     *
     * @param dto 项目信息
     * @return {@link Long}
     * @author darren
     * @since 2023/08/13
     */
    @PostMapping("/insert")
    @ApiOperation("新增项目信息")
    @Log(value = "新增项目信息", businessType = BusinessType.INSERT)
    public ResponseEntity<JsonResult<Long>> insert(ProjectInfoDto dto) {
        ValidatorBuilder.build()
                .on(dto.getOrgId(), new NotNullValidatorCallback(SystemManageErrorCodeEnum.ORG_ID_NOT_NULL))
                .on(dto.getProjectName(), new NotBlankValidatorCallback(SystemManageErrorCodeEnum.PROJECT_NAME_NOT_NULL))
                .doValidate().checkResult();
        // 设置创建者信息
        Long id = projectInfoService.insert(dto);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(id));
    }

    /**
     * 更新项目信息
     *
     * @param dto 项目信息
     * @return {@link JsonResult}
     * @author darren
     * @since 2023/08/13
     */
    @PostMapping("/update")
    @ApiOperation("更新项目信息")
    @Log(value = "更新项目信息", businessType = BusinessType.UPDATE)
    public ResponseEntity<JsonResult> update(ProjectInfoDto dto) {
        ValidatorBuilder.build()
                .on(dto.getId(), new NotNullValidatorCallback(SystemManageErrorCodeEnum.ID_NOT_NULL))
                .on(dto.getOrgId(), new NotNullValidatorCallback(SystemManageErrorCodeEnum.ORG_ID_NOT_NULL))
                .on(dto.getProjectName(), new NotBlankValidatorCallback(SystemManageErrorCodeEnum.PROJECT_NAME_NOT_NULL))
                .doValidate().checkResult();
        projectInfoService.update(dto);
        return ResponseEntityUtil.ok(JsonResult.buildSuccess());
    }

    /**
     * 根据id删除项目信息
     *
     * @param id 项目id
     * @return {@link JsonResult}
     * @author darren
     * @since 2023/08/13
     */
    @GetMapping("/deleteById")
    @ApiOperation("删除项目信息")
    @Log(value = "删除项目信息", businessType = BusinessType.DELETE)
    public ResponseEntity<JsonResult> deleteById(@RequestParam(value = "id", required = false) Long id) {
        ValidatorBuilder.build()
                .on(id, new NotNullValidatorCallback(SystemManageErrorCodeEnum.ID_NOT_NULL))
                .doValidate().checkResult();
        // 设置创建者信息
        ActiveUser activeUser = SecurityUserUtil.getActiveUser();
        projectInfoService.deleteById(id, activeUser.getId());
        return ResponseEntityUtil.ok(JsonResult.buildSuccess());
    }
}
