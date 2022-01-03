package com.darren1112.dptms.common.spi.monitor.entity;

import com.darren1112.dptms.common.spi.common.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 操作日志Entity
 *
 * @author luyuhao
 * @since 2021/02/06 20:38
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MonitorOperateLogEntity extends BaseEntity {

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;

    /**
     * 操作内容
     */
    @ApiModelProperty(value = "操作内容")
    private String content;

    /**
     * 请求地址
     */
    @ApiModelProperty(value = "请求地址")
    private String url;

    /**
     * 浏览器
     */
    @ApiModelProperty(value = "浏览器")
    private String browser;

    /**
     * ip
     */
    @ApiModelProperty(value = "ip")
    private String ip;

    /**
     * ip地址
     */
    @ApiModelProperty(value = "ip地址")
    private String ipAddress;

    /**
     * 日志级别
     */
    @ApiModelProperty(value = "日志级别")
    private Integer logLevel;

    /**
     * 业务类型
     */
    @ApiModelProperty(value = "业务类型")
    private Integer businessType;

    /**
     * 异常内容
     */
    @ApiModelProperty(value = "异常内容")
    private String exceptionContent;

    /**
     * 请求参数
     */
    @ApiModelProperty(value = "请求参数")
    private String requestParam;

    /**
     * 接口耗时
     */
    @ApiModelProperty(value = "接口耗时")
    private Integer timeConsuming;
}
