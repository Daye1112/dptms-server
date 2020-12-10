package com.darren1112.dptms.system.sys.controller;

import com.darren1112.dptms.common.core.base.BaseController;
import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.ResponseEntityUtil;
import com.darren1112.dptms.common.core.validate.ValidatorBuilder;
import com.darren1112.dptms.common.core.validate.validator.callback.common.NotEmptyValidatorCallback;
import com.darren1112.dptms.common.security.starter.util.TokenUtil;
import com.darren1112.dptms.common.spi.common.dto.ActiveUser;
import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.sys.dto.SysPermissionDto;
import com.darren1112.dptms.common.spi.sys.entity.SysPermissionEntity;
import com.darren1112.dptms.system.common.enums.SystemManageErrorCodeEnum;
import com.darren1112.dptms.system.sys.service.SysPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 权限Controller
 *
 * @author luyuhao
 * @date 2020/12/10 00:58
 */
@Slf4j
@Api(tags = "权限管理")
@RestController
@RequestMapping(value = "/sys/permission")
public class SysPermissionController extends BaseController {

    @Autowired
    private SysPermissionService sysPermissionService;

    @Autowired
    private TokenUtil tokenUtil;

    /**
     * 插入权限信息
     *
     * @param entity 权限参数
     * @return {@link JsonResult}
     * @author luyuhao
     * @date 20/12/10 01:08
     */
    @ApiOperation("插入权限")
    @GetMapping("/insert")
    public ResponseEntity<JsonResult> insert(SysPermissionEntity entity) {
        ValidatorBuilder.build()
                .on(entity.getPerName(), new NotEmptyValidatorCallback(SystemManageErrorCodeEnum.PER_NAME_NOT_NULL))
                .on(entity.getPerCode(), new NotEmptyValidatorCallback(SystemManageErrorCodeEnum.PER_CODE_NOT_NULL))
                .on(entity.getPerGroup(), new NotEmptyValidatorCallback(SystemManageErrorCodeEnum.PER_GROUP_NOT_NULL))
                .on(entity.getPerUrl(), new NotEmptyValidatorCallback(SystemManageErrorCodeEnum.PER_URL_NOT_NULL))
                .doValidate().checkResult();
        ActiveUser activeUser = tokenUtil.getActiveUser();
        entity.setCreater(activeUser.getId());
        entity.setUpdater(activeUser.getId());
        Long id = sysPermissionService.insert(entity);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(id));
    }

    /**
     * 分页查询权限
     *
     * @param dto       筛选参数
     * @param pageParam 分页参数
     * @return {@link JsonResult}
     * @author luyuhao
     * @date 20/12/10 01:08
     */
    @ApiOperation("分页查询权限")
    @GetMapping("/listPage")
    public ResponseEntity<JsonResult<PageBean<SysPermissionDto>>> listPage(PageParam pageParam,
                                                                           SysPermissionDto dto) {
        PageBean<SysPermissionDto> pageBean = sysPermissionService.listPage(getPageParam(pageParam), dto);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(pageBean));
    }

}
