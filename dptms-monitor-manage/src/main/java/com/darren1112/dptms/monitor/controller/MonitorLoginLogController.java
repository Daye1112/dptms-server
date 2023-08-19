package com.darren1112.dptms.monitor.controller;

import com.darren1112.dptms.common.core.base.BaseController;
import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.ResponseEntityUtil;
import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.monitor.dto.MonitorLoginLogDto;
import com.darren1112.dptms.monitor.service.MonitorLoginLogService;
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
 * 登录日志Controller
 *
 * @author darren
 * @since 2021/02/06 20:45
 */
@Slf4j
@Api(tags = "登录日志管理")
@RestController
@RequestMapping(value = "/loginLog")
public class MonitorLoginLogController extends BaseController {

    @Autowired
    private MonitorLoginLogService monitorLoginLogService;

    /**
     * 插入登录日志信息
     *
     * @param dto 日志参数
     * @return {@link JsonResult)
     * @author darren
     * @since 2021/02/06 20:50
     */
    @PostMapping("/insert")
    @ApiOperation(value = "插入登录日志", hidden = true)
    public ResponseEntity<JsonResult> insert(@RequestBody MonitorLoginLogDto dto) {
        monitorLoginLogService.insert(dto);
        return ResponseEntityUtil.ok(JsonResult.buildSuccess());
    }

    /**
     * 分页查询登录日志
     *
     * @param dto       筛选参数
     * @param pageParam 分页参数
     * @return {@link JsonResult}
     * @author darren
     * @since 20/12/10 01:08
     */
    @GetMapping("/listPage")
    @ApiOperation("分页查询登录日志")
    @Log(value = "分页查询登录日志", logLevel = LogLevel.DEBUG, businessType = BusinessType.QUERY)
    public ResponseEntity<JsonResult<PageBean<MonitorLoginLogDto>>> listPage(PageParam pageParam,
                                                                             MonitorLoginLogDto dto) {
        PageBean<MonitorLoginLogDto> pageBean = monitorLoginLogService.listPage(getPageParam(pageParam), dto);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(pageBean));
    }

    /**
     * 查询当前用户的登录日志
     *
     * @return {@link MonitorLoginLogDto}
     * @author darren
     * @since 2021/11/27
     */
    @GetMapping("/listCurrentUser")
    @ApiOperation("查询当前用户的登录日志")
    @Log(value = "查询当前用户的登录日志", logLevel = LogLevel.DEBUG, businessType = BusinessType.QUERY)
    public ResponseEntity<JsonResult<List<MonitorLoginLogDto>>> listCurrentUser() {
        ActiveUser activeUser = SecurityUserUtil.getActiveUser();
        List<MonitorLoginLogDto> list = monitorLoginLogService.listLastSevenByUserId(activeUser.getId());
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(list));
    }
}
