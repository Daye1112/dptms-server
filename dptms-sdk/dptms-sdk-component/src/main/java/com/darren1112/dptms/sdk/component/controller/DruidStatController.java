package com.darren1112.dptms.sdk.component.controller;

import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.ResponseEntityUtil;
import com.darren1112.dptms.common.spi.monitor.dto.DruidStatDto;
import com.darren1112.dptms.sdk.component.service.DruidService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * druid监控数据处理器
 *
 * @author luyuhao
 * @since 2020/3/14 10:29
 */
@Slf4j
@ApiIgnore
@RestController
@RequestMapping(value = "/druidStat")
public class DruidStatController {

    private DruidService druidService;

    public DruidStatController(DruidService druidService) {
        this.druidService = druidService;
    }

    /**
     * 接口访问统计
     *
     * @return {@link DruidStatDto}
     * @author luyuhao
     * @since 2021/02/17 20:45
     */
    @GetMapping("/apiStat")
    public ResponseEntity<JsonResult<List<DruidStatDto>>> getApiStat() {
        List<DruidStatDto> druidStatDtoList = druidService.apiStatList();
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(druidStatDtoList));
    }
}
