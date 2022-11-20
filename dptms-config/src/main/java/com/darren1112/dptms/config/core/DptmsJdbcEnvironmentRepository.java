package com.darren1112.dptms.config.core;

import com.darren1112.dptms.common.core.util.CollectionUtil;
import com.darren1112.dptms.common.core.util.StringUtil;
import com.darren1112.dptms.common.spi.service.dto.ServiceConfigReleasePropDto;
import com.darren1112.dptms.config.repository.ServiceConfigReleasePropRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 基于数据库的配置中心
 *
 * @author luyuhao
 * @since 2021/7/22
 */
@Component
@Profile("!native")
public class DptmsJdbcEnvironmentRepository implements EnvironmentRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(DptmsJdbcEnvironmentRepository.class);

    private static final String LABEL_LATEST = "latest";

    @Autowired
    private ServiceConfigReleasePropRepository serviceConfigReleasePropRepository;

    public DptmsJdbcEnvironmentRepository() {
        LOGGER.info("DptmsJdbcEnvironmentRepository init");
    }

    /**
     * 查询相关配置
     *
     * @param application 应用，逗号分隔
     * @param profile     环境，逗号分隔
     * @param label       分支
     * @return {@link Environment}
     * @author luyuhao
     * @since 2021/7/22
     */
    @Override
    public Environment findOne(String application, String profile, String label) {
        // 逗号分隔，转为环境数组
        String[] profileArray = StringUtil.split(profile, ",");
        // 初始化环境信息
        Environment environment = new Environment(application, profileArray, label, null, null);
        // 逗号分隔，转为应用/环境集合
        Set<String> applicationSet = StringUtil.splitToSet(application)
                .stream()
                .filter(item -> !"app".equals(item))
                .collect(Collectors.toSet());
        Set<String> profileSet = CollectionUtil.arrayToSet(profileArray);
        // 遍历获取
        for (String app : applicationSet) {
            for (String pro : profileSet) {
                // 读取每个应用的每个环境
                List<ServiceConfigReleasePropDto> releasePropList;
                // label为空或latest则读取最新的配置
                if (StringUtil.isEmpty(label) || LABEL_LATEST.equals(label)) {
                    releasePropList = serviceConfigReleasePropRepository.getBaseMapper().listLatest(app, pro);
                } else {
                    releasePropList = serviceConfigReleasePropRepository.getBaseMapper().listBy(app, pro, label);
                }
                LOGGER.info("environment: name={}, profile={}, label={}, propSize={}", app, pro, label, CollectionUtil.isEmpty(releasePropList) ? 0 : releasePropList.size());
                // 查询结果集组装
                if (CollectionUtil.isNotEmpty(releasePropList)) {
                    Map<String, String> properties = releasePropList.stream()
                            .collect(Collectors.toMap(ServiceConfigReleasePropDto::getPropKey,
                                    ServiceConfigReleasePropDto::getPropValue,
                                    (k1, k2) -> k1));
                    environment.add(new PropertySource(app + "-" + pro, properties));
                }
            }
        }
        return environment;
    }
}
