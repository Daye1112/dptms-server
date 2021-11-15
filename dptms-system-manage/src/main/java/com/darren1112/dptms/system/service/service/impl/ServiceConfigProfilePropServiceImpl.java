package com.darren1112.dptms.system.service.service.impl;

import com.darren1112.dptms.common.core.exception.BadRequestException;
import com.darren1112.dptms.common.core.util.CollectionUtil;
import com.darren1112.dptms.common.core.util.PropertiesUtil;
import com.darren1112.dptms.common.core.util.StringUtil;
import com.darren1112.dptms.common.spi.service.dto.ServiceConfigProfilePropDto;
import com.darren1112.dptms.system.common.enums.SystemManageErrorCodeEnum;
import com.darren1112.dptms.system.service.dao.ServiceConfigProfilePropDao;
import com.darren1112.dptms.system.service.service.ServiceConfigProfilePropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 配置环境属性表ServiceImpl
 *
 * @author luyuhao
 * @since 2021/03/12 01:47
 */
@Service
@CacheConfig(cacheNames = "serviceConfigProfileProp", keyGenerator = "keyGenerator")
@Transactional(rollbackFor = Throwable.class, readOnly = true)
public class ServiceConfigProfilePropServiceImpl implements ServiceConfigProfilePropService {

    @Autowired
    private ServiceConfigProfilePropDao serviceConfigProfilePropDao;

    /**
     * 查询配置环境属性list
     *
     * @param dto 配置环境属性
     * @return {@link ServiceConfigProfilePropDto}
     * @author luyuhao
     * @since 2021/03/14 22:57
     */
    @Override
    @Cacheable
    public List<ServiceConfigProfilePropDto> list(ServiceConfigProfilePropDto dto) {
        return serviceConfigProfilePropDao.list(dto);
    }

    /**
     * 插入配置环境属性
     *
     * @param dto 配置环境属性信息
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/03/14 23:01
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public Long insert(ServiceConfigProfilePropDto dto) {
        validRepeat(dto, false);
        serviceConfigProfilePropDao.insert(dto);
        return dto.getId();
    }

    /**
     * 验证配置环境属性是否重复
     *
     * @param dto      验证对象
     * @param isUpdate 是否更新
     * @author luyuhao
     * @since 2021/03/14 02:04
     */
    private void validRepeat(ServiceConfigProfilePropDto dto, boolean isUpdate) {
        ServiceConfigProfilePropDto param = new ServiceConfigProfilePropDto();
        param.setId(dto.getId());
        param.setProfileId(dto.getProfileId());
        param.setPropKey(dto.getPropKey());
        param.setIsUpdate(isUpdate);
        Long count = serviceConfigProfilePropDao.countByRepeat(param);
        if (count != null && count > 0) {
            throw new BadRequestException(SystemManageErrorCodeEnum.PROP_NOT_REPEAT);
        }
    }

    /**
     * 更新配置环境属性
     *
     * @param dto 配置环境属性信息
     * @author luyuhao
     * @since 2021/03/14 23:08
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public void update(ServiceConfigProfilePropDto dto) {
        validRepeat(dto, true);
        serviceConfigProfilePropDao.update(dto);
    }

    /**
     * 根据id删除配置环境属性
     *
     * @param id      id
     * @param updater 更新者
     * @author luyuhao
     * @since 2021/03/14 23:11
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public void deleteById(Long id, Long updater) {
        serviceConfigProfilePropDao.deleteById(id, updater);
    }

    /**
     * 根据环境id查询属性集合
     *
     * @param profileId 环境id
     * @return {@link ServiceConfigProfilePropDto}
     * @author luyuhao
     * @since 2021/7/3 10:40
     */
    @Override
    @Cacheable
    public List<ServiceConfigProfilePropDto> listByProfileId(Long profileId) {
        ServiceConfigProfilePropDto dto = new ServiceConfigProfilePropDto();
        dto.setProfileId(profileId);
        return serviceConfigProfilePropDao.list(dto);
    }

    /**
     * 批量导入配置环境属性
     *
     * @param dto content   文本内容
     *            profileId 环境id
     * @author luyuhao
     * @since 2021/7/21
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public void batchInsert(ServiceConfigProfilePropDto dto) {
        Properties properties = PropertiesUtil.buildPropertiesByStr(dto.getContent());

        // 提取新增的keys
        Set<String> keySet = properties.stringPropertyNames();
        // 查询已有的配置
        List<ServiceConfigProfilePropDto> propList = this.listByProfileId(dto.getProfileId());
        Set<String> propKeySet = propList.stream().map(ServiceConfigProfilePropDto::getPropKey).collect(Collectors.toSet());

        // 新增的key
        Set<String> newKeySet = new HashSet<>(keySet);
        newKeySet.removeAll(propKeySet);

        // 已有的key
        Set<String> overrideKey = new HashSet<>(keySet);
        overrideKey.retainAll(propKeySet);

        // 新增属性
        if (CollectionUtil.isNotEmpty(newKeySet)) {
            List<ServiceConfigProfilePropDto> newPropList = new ArrayList<>();
            for (String newKey : newKeySet) {
                // 去除空格
                String key = StringUtil.trim(newKey);
                String value = StringUtil.trim(properties.getProperty(newKey));

                if (key.length() > 128) {
                    throw new BadRequestException(SystemManageErrorCodeEnum.PROP_KEY_TOO_LONG);
                }
                ServiceConfigProfilePropDto subDto = new ServiceConfigProfilePropDto();
                subDto.setProfileId(dto.getProfileId());
                subDto.setPropKey(key);
                subDto.setPropValue(value);
                subDto.setCreater(dto.getCreater());
                subDto.setUpdater(dto.getUpdater());

                newPropList.add(subDto);
            }

            // 批量新增
            serviceConfigProfilePropDao.batchInsert(newPropList);
        }

        // 更新属性
        if (CollectionUtil.isNotEmpty(overrideKey)) {
            propList.stream()
                    .filter(item -> overrideKey.contains(item.getPropKey()))
                    .forEach(item -> {
                        item.setPropKey(StringUtil.trim(item.getPropKey()));
                        item.setPropValue(StringUtil.trim(properties.getProperty(item.getPropKey())));
                        item.setUpdater(dto.getUpdater());

                        // 更新属性
                        serviceConfigProfilePropDao.update(item);
                    });
        }
    }
}
