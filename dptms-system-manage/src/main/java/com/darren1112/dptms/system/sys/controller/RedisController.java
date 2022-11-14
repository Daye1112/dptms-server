package com.darren1112.dptms.system.sys.controller;

import com.darren1112.dptms.common.core.base.BaseController;
import com.darren1112.dptms.common.core.message.JsonResult;
import com.darren1112.dptms.common.core.util.ResponseEntityUtil;
import com.darren1112.dptms.common.core.util.StringUtil;
import com.darren1112.dptms.common.core.validate.ValidatorBuilder;
import com.darren1112.dptms.common.core.validate.validator.callback.common.NotEmptyValidatorCallback;
import com.darren1112.dptms.common.core.validate.validator.callback.common.NotNullValidatorCallback;
import com.darren1112.dptms.sdk.starter.log.annotation.Log;
import com.darren1112.dptms.sdk.starter.log.enums.BusinessType;
import com.darren1112.dptms.sdk.starter.log.enums.LogLevel;
import com.darren1112.dptms.sdk.starter.redis.properties.DptmsRedisProperties;
import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.sys.dto.SysRedisDto;
import com.darren1112.dptms.system.common.enums.SystemManageErrorCodeEnum;
import com.darren1112.dptms.system.sys.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 缓存管理
 *
 * @author luyuhao
 * @since 2021/01/30 23:41
 */
@Slf4j
@Api(tags = "缓存管理", description = "缓存管理接口")
@RestController
@RequestMapping(value = "/cache")
public class RedisController extends BaseController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private DptmsRedisProperties dptmsRedisProperties;

    /**
     * 根据key的前缀分页查缓存数据
     *
     * @param keyPrefix key前缀
     * @param pageParam 分页参数
     * @return {@link SysRedisDto}
     * @author luyuhao
     * @since 2021/01/31 00:04
     */
    @Log(value = "分页查询缓存", logLevel = LogLevel.DEBUG, businessType = BusinessType.QUERY)
    @ApiOperation("分页查询缓存")
    @GetMapping("/listPage")
    public ResponseEntity<JsonResult<PageBean<SysRedisDto>>> listPage(@RequestParam(value = "keyPrefix", required = false) String keyPrefix, PageParam pageParam) {
        keyPrefix = StringUtil.isBlank(keyPrefix) ? dptmsRedisProperties.getPrefix() : keyPrefix;
        PageBean<SysRedisDto> result = redisService.listPage(keyPrefix, getPageParam(pageParam));
        return ResponseEntityUtil.ok(JsonResult.buildSuccessData(result));
    }

    /**
     * 新增缓存
     *
     * @param sysRedisDto 缓存信息
     * @return {@link JsonResult}
     * @author luyuhao
     * @since 2021/01/31 00:04
     */
    @Log(value = "新增缓存", logLevel = LogLevel.WARN, businessType = BusinessType.INSERT)
    @ApiOperation("新增缓存")
    @PostMapping("/insert")
    public ResponseEntity<JsonResult> insert(SysRedisDto sysRedisDto) {
        ValidatorBuilder.build()
                .on(sysRedisDto.getKey(), new NotEmptyValidatorCallback(SystemManageErrorCodeEnum.REDIS_KEY_PREFIX_NOT_NULL))
                .on(sysRedisDto.getValue(), new NotNullValidatorCallback(SystemManageErrorCodeEnum.REDIS_VALUE_NOT_NULL))
                .on(sysRedisDto.getExpired(), new NotNullValidatorCallback(SystemManageErrorCodeEnum.REDIS_KEY_EXPIRED_NOT_NULL))
                .doValidate().checkResult();
        redisService.insert(sysRedisDto);
        return ResponseEntityUtil.ok(JsonResult.buildSuccess());
    }

    /**
     * 删除缓存
     *
     * @param sysRedisDto 缓存信息
     * @return {@link JsonResult}
     * @author luyuhao
     * @since 2021/01/31 00:04
     */
    @Log(value = "删除缓存", logLevel = LogLevel.WARN, businessType = BusinessType.DELETE)
    @ApiOperation("删除缓存")
    @GetMapping("/deleteByKey")
    public ResponseEntity<JsonResult> deleteByKey(SysRedisDto sysRedisDto) {
        ValidatorBuilder.build()
                .on(sysRedisDto.getKey(), new NotEmptyValidatorCallback(SystemManageErrorCodeEnum.REDIS_KEY_NOT_NULL))
                .doValidate().checkResult();
        redisService.deleteByKey(sysRedisDto);
        return ResponseEntityUtil.ok(JsonResult.buildSuccess());
    }
}
