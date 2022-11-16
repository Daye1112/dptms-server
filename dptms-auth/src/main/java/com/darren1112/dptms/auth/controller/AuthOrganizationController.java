package com.darren1112.dptms.auth.controller;

import com.darren1112.dptms.auth.common.enums.AuthErrorCodeEnum;
import com.darren1112.dptms.auth.service.AuthOrganizationService;
import com.darren1112.dptms.auth.service.AuthUserOrganizationService;
import com.darren1112.dptms.common.core.base.BaseController;
import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.ResponseEntityUtil;
import com.darren1112.dptms.common.core.validate.ValidatorBuilder;
import com.darren1112.dptms.common.core.validate.validator.callback.common.NotEmptyValidatorCallback;
import com.darren1112.dptms.common.core.validate.validator.callback.common.NotNullValidatorCallback;
import com.darren1112.dptms.common.spi.auth.dto.AuthOrganizationDto;
import com.darren1112.dptms.common.spi.auth.entity.AuthOrganizationEntity;
import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.sdk.starter.log.annotation.Log;
import com.darren1112.dptms.sdk.starter.log.enums.BusinessType;
import com.darren1112.dptms.sdk.starter.log.enums.LogLevel;
import com.darren1112.dptms.sdk.starter.security.model.ActiveUser;
import com.darren1112.dptms.sdk.starter.security.util.SecurityUserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 组织Controller
 *
 * @author luyuhao
 * @since 2020/08/16 01:43
 */
@Slf4j
@Api(tags = "组织管理")
@RestController
@RequestMapping(value = "/organization")
public class AuthOrganizationController extends BaseController {

    @Autowired
    private AuthOrganizationService authOrganizationService;

    @Autowired
    private AuthUserOrganizationService authUserOrganizationService;

    /**
     * 分页查询组织信息
     *
     * @param pageParam 分页参数
     * @param dto       筛选参数
     * @return {@link JsonResult}
     * @author luyuhao
     * @since 20/12/10 01:08
     */
    @Log(value = "分页查询组织", logLevel = LogLevel.DEBUG, businessType = BusinessType.QUERY)
    @ApiOperation("分页查询组织")
    @GetMapping("/listPage")
    public ResponseEntity<JsonResult<PageBean<AuthOrganizationDto>>> listPage(PageParam pageParam,
                                                                              AuthOrganizationDto dto) {
        PageBean<AuthOrganizationDto> pageBean = authOrganizationService.listPage(getPageParam(pageParam), dto);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(pageBean));
    }

    /**
     * 插入组织信息
     *
     * @param entity 组织参数
     * @return {@link JsonResult}
     * @author luyuhao
     * @since 20/12/10 01:08
     */
    @Log(value = "插入组织", businessType = BusinessType.INSERT)
    @ApiOperation("插入组织")
    @PostMapping("/insert")
    public ResponseEntity<JsonResult<Long>> insert(AuthOrganizationEntity entity) {
        ValidatorBuilder.build()
                .on(entity.getOrgCode(), new NotEmptyValidatorCallback(AuthErrorCodeEnum.ORG_CODE_NOT_NULL))
                .on(entity.getOrgName(), new NotEmptyValidatorCallback(AuthErrorCodeEnum.ORG_NAME_NOT_NULL))
                .doValidate().checkResult();
        ActiveUser activeUser = SecurityUserUtil.getActiveUser();
        entity.setCreater(activeUser.getId());
        entity.setUpdater(activeUser.getId());
        Long id = authOrganizationService.insert(entity);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(id));
    }

    /**
     * 更新组织信息
     *
     * @param entity 组织参数
     * @return {@link JsonResult}
     * @author luyuhao
     * @since 20/12/10 01:08
     */
    @Log(value = "更新组织", businessType = BusinessType.UPDATE)
    @ApiOperation("更新组织")
    @PostMapping("/update")
    public ResponseEntity<JsonResult<Long>> update(AuthOrganizationEntity entity) {
        ValidatorBuilder.build()
                .on(entity.getId(), new NotNullValidatorCallback(AuthErrorCodeEnum.ID_NOT_NULL))
                .on(entity.getOrgCode(), new NotEmptyValidatorCallback(AuthErrorCodeEnum.ORG_CODE_NOT_NULL))
                .on(entity.getOrgName(), new NotEmptyValidatorCallback(AuthErrorCodeEnum.ORG_NAME_NOT_NULL))
                .doValidate().checkResult();
        ActiveUser activeUser = SecurityUserUtil.getActiveUser();
        entity.setUpdater(activeUser.getId());
        Long count = authOrganizationService.update(entity);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(count));
    }

    /**
     * 根据id删除记录
     *
     * @param id 记录id
     * @return {@link JsonResult}
     * @author luyuhao
     * @since 20/12/10 01:08
     */
    @Log(value = "根据id删除记录", businessType = BusinessType.DELETE)
    @ApiOperation("根据id删除记录")
    @GetMapping("/deleteById")
    public ResponseEntity<JsonResult> deleteById(@RequestParam(value = "id", required = false) Long id) {
        ValidatorBuilder.build()
                .on(id, new NotNullValidatorCallback(AuthErrorCodeEnum.ID_NOT_NULL))
                .doValidate().checkResult();
        ActiveUser activeUser = SecurityUserUtil.getActiveUser();
        authOrganizationService.deleteById(id, activeUser.getId());
        return ResponseEntityUtil.ok(JsonResult.buildSuccess());
    }

    /**
     * 查询用户关联的组织list
     *
     * @param userId 用户id
     * @return {@link AuthOrganizationDto}
     * @author luyuhao
     * @since 20/12/13 21:43
     */
    @Log(value = "查询用户关联的组织list", logLevel = LogLevel.DEBUG, businessType = BusinessType.QUERY)
    @ApiOperation("查询用户关联的组织list")
    @GetMapping("/listUserAssigned")
    public ResponseEntity<JsonResult<List<AuthOrganizationDto>>> listUserAssigned(@RequestParam(value = "userId", required = false) Long userId) {
        ValidatorBuilder.build()
                .on(userId, new NotNullValidatorCallback(AuthErrorCodeEnum.USER_ID_NOT_NULL))
                .doValidate().checkResult();
        List<AuthOrganizationDto> resultList = authUserOrganizationService.listUserAssigned(userId);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(resultList));
    }
}
