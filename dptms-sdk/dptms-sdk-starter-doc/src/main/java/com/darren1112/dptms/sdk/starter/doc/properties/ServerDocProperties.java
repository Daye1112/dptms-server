package com.darren1112.dptms.sdk.starter.doc.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 文档信息配置
 *
 * @author darren
 * @since 2020/08/06 02:37
 */
@Data
@ConfigurationProperties(prefix = "dptms.doc")
public class ServerDocProperties {
    /**
     * 文档标题
     */
    private String title;
    /**
     * 文档描述
     */
    private String description;
    /**
     * 联系方式：姓名
     */
    private String name;
    /**
     * 联系方式：邮箱
     */
    private String email;
    /**
     * 版本
     */
    private String version;
}
