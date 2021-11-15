package com.darren1112.dptms.common.redis.starter.properties;

import com.darren1112.dptms.common.redis.starter.core.RedisConstants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * redis配置类
 *
 * @author luyuhao
 * @date 2021/11/14 19:33
 */
@Data
@ConfigurationProperties(prefix = "dptms.redis")
public class DptmsRedisProperties {

    /**
     * 前缀
     */
    private String prefix;

    /**
     * ip:port 逗号分隔
     */
    private String ip;

    /**
     * 密码
     */
    private String password;

    /**
     * 设置最大连接数
     */
    private int maxIdle;

    /**
     * 最大空闲连接数
     */
    private int maxTotal;

    /**
     * 最大等待毫秒数
     */
    private int maxWaitMillis;

    /**
     * 是否启动心跳检查
     */
    private boolean enableHeartbeat = true;

    /**
     * redis策略
     */
    private String model = RedisConstants.MODEL_MASTER_SLAVE;

    /**
     * 缓存有效期(min)
     */
    private long cacheTtl = 60;
}
