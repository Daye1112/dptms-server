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
import com.darren1112.dptms.common.spi.sys.dto.SysRoleDto;
import com.darren1112.dptms.common.spi.sys.entity.SysRoleEntity;
import com.darren1112.dptms.system.common.enums.SystemManageErrorCodeEnum;
import com.darren1112.dptms.system.sys.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 角色Controller
 *
 * @author luyuhao
 * @date 2020/12/13 23:11
 */
@Slf4j
@Api(tags = "角色管理")
@RestController
@RequestMapping(value = "/sys/role")
public class SysRoleController extends BaseController {

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 插入角色信息
     *
     * @param entity 角色参数
     * @return {@link JsonResult}
     * @author luyuhao
     * @date 20/12/10 01:08
     */
    @ApiOperation("插入角色")
    @PostMapping("/insert")
    public ResponseEntity<JsonResult<Long>> insert(SysRoleEntity entity) {
        ValidatorBuilder.build()
                .on(entity.getOrgId(), new NotEmptyValidatorCallback(SystemManageErrorCodeEnum.ORG_ID_NOT_NULL))
                .on(entity.getRoleName(), new NotEmptyValidatorCallback(SystemManageErrorCodeEnum.ROLE_NAME_NOT_NULL))
                .on(entity.getRoleCode(), new NotEmptyValidatorCallback(SystemManageErrorCodeEnum.ROLE_CODE_NOT_NULL))
                .on(entity.getIsAdmin(), new NotEmptyValidatorCallback(SystemManageErrorCodeEnum.ROLE_IS_DADMIN_NOT_NULL))
                .doValidate().checkResult();
        ActiveUser activeUser = tokenUtil.getActiveUser();
        entity.setCreater(activeUser.getId());
        entity.setUpdater(activeUser.getId());
        Long id = sysRoleService.insert(entity);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(id));
    }

    /**
     * 分页查询角色
     *
     * @param dto       筛选参数
     * @param pageParam 分页参数
     * @return {@link JsonResult}
     * @author luyuhao
     * @date 20/12/10 01:08
     */
    @ApiOperation("分页查询角色")
    @GetMapping("/listPage")
    public ResponseEntity<JsonResult<PageBean<SysRoleDto>>> listPage(PageParam pageParam,
                                                                     SysRoleDto dto) {
        PageBean<SysRoleDto> pageBean = sysRoleService.listPage(getPageParam(pageParam), dto);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(pageBean));
    }

    /**
     * 更新角色信息
     *
     * @param entity 权限参数
     * @return {@link JsonResult}
     * @author luyuhao
     * @date 20/12/10 01:08
     */
    @ApiOperation("更新角色")
    @PostMapping("/update")
    public ResponseEntity<JsonResult<Long>> update(SysRoleEntity entity) {
        ValidatorBuilder.build()
                .on(entity.getId(), new NotNullValidatorCallback(SystemManageErrorCodeEnum.ID_NOT_NULL))
                .on(entity.getOrgId(), new NotEmptyValidatorCallback(SystemManageErrorCodeEnum.ORG_ID_NOT_NULL))
                .on(entity.getRoleName(), new NotEmptyValidatorCallback(SystemManageErrorCodeEnum.ROLE_NAME_NOT_NULL))
                .on(entity.getRoleCode(), new NotEmptyValidatorCallback(SystemManageErrorCodeEnum.ROLE_CODE_NOT_NULL))
                .on(entity.getIsAdmin(), new NotEmptyValidatorCallback(SystemManageErrorCodeEnum.ROLE_IS_DADMIN_NOT_NULL))
                .doValidate().checkResult();
        ActiveUser activeUser = tokenUtil.getActiveUser();
        entity.setUpdater(activeUser.getId());
        Long count = sysRoleService.update(entity);
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
        sysRoleService.deleteById(id, activeUser.getId());
        return ResponseEntityUtil.ok(JsonResult.buildSuccess());
    }
}
