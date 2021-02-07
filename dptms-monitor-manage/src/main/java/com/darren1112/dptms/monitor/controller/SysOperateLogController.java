package com.darren1112.dptms.monitor.controller;

import com.darren1112.dptms.common.core.base.BaseController;
import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.ResponseEntityUtil;
import com.darren1112.dptms.common.spi.sys.dto.SysOperateLogDto;
import com.darren1112.dptms.monitor.service.SysOperateLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 操作日志Controller
 *
 * @author luyuhao
 * @date 2021/02/06 20:45
 */
@Slf4j
@Api(tags = "操作日志管理")
@RestController
@RequestMapping(value = "/operateLog")
public class SysOperateLogController extends BaseController {

    @Autowired
    private SysOperateLogService sysOperateLogService;

    /**
     * 插入操作日志信息
     *
     * @param dto 日志参数
     * @return {@link JsonResult)
     * @author luyuhao
     * @date 2021/02/06 20:50
     */
    @ApiOperation(value = "插入操作日志", hidden = true)
    @PostMapping("/insert")
    public ResponseEntity<JsonResult> insert(@RequestBody SysOperateLogDto dto) {
        sysOperateLogService.insert(dto);
        return ResponseEntityUtil.ok(JsonResult.buildSuccess());
    }
}
