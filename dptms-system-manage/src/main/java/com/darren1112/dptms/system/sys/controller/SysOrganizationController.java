package com.darren1112.dptms.system.sys.controller;

import com.darren1112.dptms.common.core.base.BaseController;
import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.ResponseEntityUtil;
import com.darren1112.dptms.common.core.validate.ValidatorBuilder;
import com.darren1112.dptms.common.core.validate.validator.callback.common.NotEmptyValidatorCallback;
import com.darren1112.dptms.common.core.validate.validator.callback.common.NotNullValidatorCallback;
import com.darren1112.dptms.common.security.starter.util.TokenUtil;
import com.darren1112.dptms.common.spi.common.dto.ActiveUser;
import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.sys.dto.SysOrganizationDto;
import com.darren1112.dptms.common.spi.sys.entity.SysOrganizationEntity;
import com.darren1112.dptms.system.common.enums.SystemManageErrorCodeEnum;
import com.darren1112.dptms.system.sys.service.SysOrganizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 组织Controller
 *
 * @author luyuhao
 * @date 2020/08/16 01:43
 */
@Slf4j
@Api(tags = "组织管理")
@RestController
@RequestMapping(value = "/sys/organization")
public class SysOrganizationController extends BaseController {

    @Autowired
    private SysOrganizationService sysOrganizationService;

    @Autowired
    private TokenUtil tokenUtil;

    /**
     * 分页查询组织信息
     *
     * @param pageParam 分页参数
     * @param dto       筛选参数
     * @return {@link JsonResult}
     * @author luyuhao
     * @date 20/12/10 01:08
     */
    @ApiOperation("分页查询组织")
    @PostMapping("/listPage")
    public ResponseEntity<JsonResult<PageBean<SysOrganizationDto>>> listPage(PageParam pageParam,
                                                                             SysOrganizationDto dto) {
        PageBean<SysOrganizationDto> pageBean = sysOrganizationService.listPage(getPageParam(pageParam), dto);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(pageBean));
    }

    /**
     * 插入组织信息
     *
     * @param entity 组织参数
     * @return {@link JsonResult}
     * @author luyuhao
     * @date 20/12/10 01:08
     */
    @ApiOperation("插入组织")
    @PostMapping("/insert")
    public ResponseEntity<JsonResult<Long>> insert(SysOrganizationEntity entity) {
        ValidatorBuilder.build()
                .on(entity.getOrgCode(), new NotEmptyValidatorCallback(SystemManageErrorCodeEnum.ORG_CODE_NOT_NULL))
                .on(entity.getOrgName(), new NotEmptyValidatorCallback(SystemManageErrorCodeEnum.ORG_NAME_NOT_NULL))
                .doValidate().checkResult();
        ActiveUser activeUser = tokenUtil.getActiveUser();
        entity.setCreater(activeUser.getId());
        entity.setUpdater(activeUser.getId());
        Long id = sysOrganizationService.insert(entity);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(id));
    }

    /**
     * 更新组织信息
     *
     * @param entity 组织参数
     * @return {@link JsonResult}
     * @author luyuhao
     * @date 20/12/10 01:08
     */
    @ApiOperation("更新组织")
    @PostMapping("/update")
    public ResponseEntity<JsonResult<Long>> update(SysOrganizationEntity entity) {
        ValidatorBuilder.build()
                .on(entity.getId(), new NotNullValidatorCallback(SystemManageErrorCodeEnum.ID_NOT_NULL))
                .on(entity.getOrgCode(), new NotEmptyValidatorCallback(SystemManageErrorCodeEnum.ORG_CODE_NOT_NULL))
                .on(entity.getOrgName(), new NotEmptyValidatorCallback(SystemManageErrorCodeEnum.ORG_NAME_NOT_NULL))
                .doValidate().checkResult();
        ActiveUser activeUser = tokenUtil.getActiveUser();
        entity.setUpdater(activeUser.getId());
        Long count = sysOrganizationService.update(entity);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(count));
    }

    /**
     * 根据id删除记录
     *
     * @param id 记录id
     * @return {@link JsonResult}
     * @author luyuhao
     * @date 20/12/10 01:08
     */
    @ApiOperation("根据id删除记录")
    @GetMapping("/deleteById")
    public ResponseEntity<JsonResult> deleteById(@RequestParam(value = "id", required = false) Long id) {
        ValidatorBuilder.build()
                .on(id, new NotNullValidatorCallback(SystemManageErrorCodeEnum.ID_NOT_NULL))
                .doValidate().checkResult();
        ActiveUser activeUser = tokenUtil.getActiveUser();
        sysOrganizationService.deleteById(id, activeUser.getId());
        return ResponseEntityUtil.ok(JsonResult.buildSuccess());
    }
}
