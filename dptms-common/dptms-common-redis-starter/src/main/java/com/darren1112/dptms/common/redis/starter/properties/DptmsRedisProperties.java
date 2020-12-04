package com.darren1112.dptms.common.redis.starter.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * redis配置
 *
 * @author luyuhao
 * @since 2020/12/4 20:02
 */
@Data
@ConfigurationProperties(prefix = "dptms.redis")
public class DptmsRedisProperties {

    /**
     * 系统统一redis前缀
     */
    private String systemPrefix;
}
