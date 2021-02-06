package com.darren1112.dptms.monitor.controller;

import com.darren1112.dptms.common.core.base.BaseController;
import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.ResponseEntityUtil;
import com.darren1112.dptms.common.spi.sys.dto.SysLoginLogDto;
import com.darren1112.dptms.monitor.service.SysLoginLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录日志Controller
 *
 * @author luyuhao
 * @date 2021/02/06 20:45
 */
@Slf4j
@Api(tags = "登录日志管理")
@RestController
@RequestMapping(value = "/loginLog")
public class SysLoginLogController extends BaseController {

    @Autowired
    private SysLoginLogService sysLoginLogService;

    /**
     * 插入登录日志信息
     *
     * @param dto 日志参数
     * @return {@link JsonResult)
     * @author luyuhao
     * @date 2021/02/06 20:50
     */
    @ApiOperation(value = "插入登录日志", hidden = true)
    @PostMapping("/insert")
    public ResponseEntity<JsonResult> insert(SysLoginLogDto dto) {
        sysLoginLogService.insert(dto);
        return ResponseEntityUtil.ok(JsonResult.buildSuccess());
    }
}
