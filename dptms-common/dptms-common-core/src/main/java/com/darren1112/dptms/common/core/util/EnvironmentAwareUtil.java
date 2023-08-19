package com.darren1112.dptms.common.core.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * @author darren
 * @since 2019/12/4 18:38
 */
@Slf4j
public class EnvironmentAwareUtil {

    /**
     * 环境变量：系统环境
     */
    private static final String ENV_LOCATION = "dptms";

    /**
     * 系统环境默认：local
     */
    public static final String DEFAULT_LOCATION = "local";

    /**
     * 环境变量：系统密钥
     */
    private static final String SECRET = "dptmsSecret";


    public static String adjust() {
        String location = System.getenv(ENV_LOCATION);
        String dptmsSecret = Optional.ofNullable(System.getenv(SECRET)).orElse("");
        if (StringUtils.isEmpty(location)) {
            location = DEFAULT_LOCATION;
        }
        log.info("当前环境为 {}", location);
        log.info("系统密钥为 {}", dptmsSecret);
        System.setProperty(ConfigFileApplicationListener.CONFIG_LOCATION_PROPERTY, "classpath:/config/" + location + "/");
        System.setProperty(ConfigFileApplicationListener.ACTIVE_PROFILES_PROPERTY, location);
        System.setProperty("jasypt.encryptor.password", dptmsSecret);
        return location;
    }
}
