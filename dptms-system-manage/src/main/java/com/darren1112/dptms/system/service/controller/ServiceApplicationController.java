package com.darren1112.dptms.system.service.controller;

import com.darren1112.dptms.common.core.base.BaseController;
import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.ResponseEntityUtil;
import com.darren1112.dptms.common.log.starter.annotation.Log;
import com.darren1112.dptms.common.log.starter.enums.BusinessType;
import com.darren1112.dptms.common.log.starter.enums.LogLevel;
import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.service.dto.ServiceApplicationDto;
import com.darren1112.dptms.system.service.service.ServiceApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 应用表Controller
 *
 * @author luyuhao
 * @date 2021/03/12 01:33
 */
@Slf4j
@Api(tags = "服务管理", description = "服务管理接口")
@RestController
@RequestMapping(value = "/service/application")
public class ServiceApplicationController extends BaseController {

    @Autowired
    private ServiceApplicationService serviceApplicationService;

    /**
     * 分页查询服务应用
     *
     * @param dto       筛选参数
     * @param pageParam 分页参数
     * @return {@link ServiceApplicationDto}
     * @author luyuhao
     * @date 2021/03/12 17:33
     */
    @Log(value = "分页查询用户", logLevel = LogLevel.DEBUG, businessType = BusinessType.QUERY)
    @ApiOperation("分页查询用户")
    @GetMapping("/listPage")
    public ResponseEntity<JsonResult<PageBean<ServiceApplicationDto>>> listPage(PageParam pageParam,
                                                                                ServiceApplicationDto dto) {
        PageBean<ServiceApplicationDto> pageBean = serviceApplicationService.listPage(getPageParam(pageParam), dto);
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(pageBean));
    }
}
