package com.darren1112.dptms.system.sys.controller;

import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.ResponseEntityUtil;
import com.darren1112.dptms.common.core.validate.ValidatorBuilder;
import com.darren1112.dptms.common.core.validate.validator.callback.common.NotNullValidatorCallback;
import com.darren1112.dptms.common.spi.sys.dto.SysUserDto;
import com.darren1112.dptms.system.common.enums.SystemManageErrorCodeEnum;
import com.darren1112.dptms.system.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统用户Controller
 *
 * @author luyuhao
 * @date 2020/07/25 02:14
 */
@Slf4j
@Api(tags = "用户管理")
@RestController
@RequestMapping(value = "/sys/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 根据id查询用户
     *
     * @param id id
     * @return {@link SysUserDto}
     * @author luyuhao
     * @date 20/11/30 23:12
     */
    @ApiOperation("根据id查询用户")
    @GetMapping("/getById")
    public ResponseEntity<JsonResult<SysUserDto>> getById(Long id) {
        ValidatorBuilder.build()
                .on(id, new NotNullValidatorCallback(SystemManageErrorCodeEnum.ID_NOT_NULL))
                .doValidate().checkResult();
        SysUserDto result = sysUserService.getById(id);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(result));
    }
}
