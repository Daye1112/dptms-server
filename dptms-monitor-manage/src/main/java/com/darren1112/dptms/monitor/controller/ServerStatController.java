package com.darren1112.dptms.monitor.controller;

import com.darren1112.dptms.common.core.enums.ServerEnum;
import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.CollectionUtil;
import com.darren1112.dptms.common.core.util.ResponseEntityUtil;
import com.darren1112.dptms.common.core.validate.handler.ValidateHandler;
import com.darren1112.dptms.common.log.starter.annotation.Log;
import com.darren1112.dptms.common.log.starter.enums.BusinessType;
import com.darren1112.dptms.common.log.starter.enums.LogLevel;
import com.darren1112.dptms.common.spi.common.dto.KeyValueDto;
import com.darren1112.dptms.common.spi.monitor.dto.DruidStatDto;
import com.darren1112.dptms.sdk.component.remoting.FileManageRemoting;
import com.darren1112.dptms.sdk.component.service.DruidService;
import com.darren1112.dptms.monitor.common.enums.MonitorManageErrorCodeEnum;
import com.darren1112.dptms.sdk.component.remoting.AuthRemoting;
import com.darren1112.dptms.sdk.component.remoting.SystemManageRemoting;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * 服务统计controller
 *
 * @author luyuhao
 * @since 2021/02/15 23:25
 */
@Slf4j
@Api(tags = "服务统计管理")
@RestController
@RequestMapping(value = "/serverStat")
public class ServerStatController {

    @Autowired
    private DruidService druidService;

    @Autowired
    private AuthRemoting authRemoting;

    @Autowired
    private SystemManageRemoting systemManageRemoting;

    @Autowired
    private FileManageRemoting fileManageRemoting;

    /**
     * 查询服务list
     *
     * @return {@link KeyValueDto}
     * @author luyuhao
     * @since 2021/02/17 22:48
     */
    @Log(value = "查询服务list", logLevel = LogLevel.DEBUG, businessType = BusinessType.QUERY)
    @ApiOperation(value = "查询服务list")
    @GetMapping("/serverList")
    public ResponseEntity<JsonResult<List<KeyValueDto>>> serverList() {
        List<KeyValueDto> resultList = new ArrayList<>();
        ServerEnum[] serverEnums = ServerEnum.values();
        if (CollectionUtil.isNotEmpty(serverEnums)) {
            for (ServerEnum serverEnum : serverEnums) {
                KeyValueDto subKeyValueDto = new KeyValueDto();
                subKeyValueDto.setKey(serverEnum.getCode());
                subKeyValueDto.setValue(serverEnum.getName());
                resultList.add(subKeyValueDto);
            }
        }
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(resultList));
    }

    /**
     * 根据模块key查询模块的api统计数据
     *
     * @param module 模块key
     * @return {@link DruidStatDto}
     * @author luyuhao
     * @since 2021/02/15 23:36
     */
    @Log(value = "接口统计查询", logLevel = LogLevel.INFO, businessType = BusinessType.QUERY)
    @ApiOperation(value = "接口统计查询")
    @GetMapping("/apiStatList")
    public ResponseEntity<JsonResult<List<DruidStatDto>>> apiStatList(@RequestParam(value = "module", required = false) Integer module) {
        ValidateHandler.checkNull(module, MonitorManageErrorCodeEnum.MODULE_NOT_NULL);
        List<DruidStatDto> druidStatDtoList = new ArrayList<>();
        ServerEnum serverEnum = ServerEnum.matchByCode(module);
        switch (serverEnum) {
            // dptms-auth
            case DPTMS_AUTH:
                druidStatDtoList = Optional.ofNullable(authRemoting.getApiStat())
                        .map(JsonResult::getData)
                        .orElse(Collections.emptyList());
                break;
            // dptms-system-manage
            case DPTMS_SYSTEM_MANAGE:
                druidStatDtoList = Optional.ofNullable(systemManageRemoting.getApiStat())
                        .map(JsonResult::getData)
                        .orElse(Collections.emptyList());
                break;
            // dptms-file-manage
            case DPTMS_FILE_MANAGE:
                druidStatDtoList = Optional.ofNullable(fileManageRemoting.getApiStat())
                        .map(JsonResult::getData)
                        .orElse(Collections.emptyList());
                break;
            // dptms-monitor-manage
            case DPTMS_MONITOR_MANAGE:
                druidStatDtoList = druidService.apiStatList();
                break;
            default:
                break;
        }
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(druidStatDtoList));
    }
}
