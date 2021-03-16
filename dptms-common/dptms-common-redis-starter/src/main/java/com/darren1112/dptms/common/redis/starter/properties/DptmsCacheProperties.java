package com.darren1112.dptms.common.redis.starter.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 缓存配置
 *
 * @author luyuhao
 * @since 2020/12/11 22:44
 */
@Data
@ConfigurationProperties(prefix = "dptms.cache")
public class DptmsCacheProperties {

    /**
     * 是否启用前缀
     */
    private boolean useKeyPrefix = true;

    /**
     * 统一前缀
     */
    private String keyPrefix = "";

    /**
     * 缓存有效期(min)
     */
    private long cacheTtl = 60;
}
