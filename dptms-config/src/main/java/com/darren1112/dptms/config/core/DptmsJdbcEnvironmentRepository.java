package com.darren1112.dptms.config.core;

import com.darren1112.dptms.common.core.util.CollectionUtil;
import com.darren1112.dptms.common.core.util.StringUtil;
import com.darren1112.dptms.common.spi.service.dto.ServiceConfigReleasePropDto;
import com.darren1112.dptms.config.dao.ServiceConfigReleasePropDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
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
public class DptmsJdbcEnvironmentRepository implements EnvironmentRepository {

    private static final String LABEL_LATEST = "latest";

    @Autowired
    private ServiceConfigReleasePropDao serviceConfigReleasePropDao;

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
        Set<String> applicationSet = StringUtil.splitToSet(application);
        Set<String> profileSet = CollectionUtil.arrayToSet(profileArray);

        for (String app : applicationSet) {
            for (String pro : profileSet) {
                // 读取每个应用的每个环境
                List<ServiceConfigReleasePropDto> releasePropList;
                // label为空或latest则读取最新的配置
                if (StringUtil.isEmpty(label) || LABEL_LATEST.equals(label)) {
                    releasePropList = serviceConfigReleasePropDao.listLatest(app, pro);
                } else {
                    releasePropList = serviceConfigReleasePropDao.listBy(app, pro, label);
                }
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
