package com.darren1112.dptms.auth.service.impl;

import com.darren1112.dptms.auth.repository.AuthUserOrganizationRepository;
import com.darren1112.dptms.auth.service.AuthUserOrganizationService;
import com.darren1112.dptms.common.core.util.DatabaseUtil;
import com.darren1112.dptms.common.core.util.StringUtil;
import com.darren1112.dptms.common.spi.auth.dto.AuthOrganizationDto;
import com.darren1112.dptms.common.spi.auth.entity.AuthUserOrganizationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户组织Service
 *
 * @author luyuhao
 * @since 2020/12/13 22:22
 */
@Service
@CacheConfig(cacheNames = "authUserOrganization", keyGenerator = "keyGenerator")
@Transactional(rollbackFor = Throwable.class, readOnly = true)
public class AuthUserOrganizationServiceImpl implements AuthUserOrganizationService {

    @Autowired
    private AuthUserOrganizationRepository authUserOrganizationRepository;

    /**
     * 查询用户关联的组织list
     *
     * @param userId 用户id
     * @return {@link AuthOrganizationDto}
     * @author luyuhao
     * @since 20/12/13 21:43
     */
    @Override
    public List<AuthOrganizationDto> listUserAssigned(Long userId) {
        return authUserOrganizationRepository.getBaseMapper().listUserAssigned(userId);
    }

    /**
     * 分配组织
     *
     * @param userId  用户id
     * @param orgIds  组织ids，逗号分隔
     * @param updater 更新者
     * @author luyuhao
     * @since 20/12/13 22:10
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public void assignedOrg(Long userId, String orgIds, Long updater) {
        // 清空用户已分配的组织
        authUserOrganizationRepository.getBaseMapper().deleteByUserId(userId, updater);
        if (StringUtil.isBlank(orgIds)) {
            return;
        }
        // 封装list
        List<AuthUserOrganizationEntity> list = new ArrayList<>();
        String[] orgIdArr = orgIds.split(",");
        for (String orgId : orgIdArr) {
            AuthUserOrganizationEntity entity = new AuthUserOrganizationEntity();
            entity.setUserId(userId);
            entity.setOrgId(Long.valueOf(orgId));
            entity.setCreater(updater);
            entity.setUpdater(updater);
            list.add(entity);
        }
        // 批量插入
        DatabaseUtil.batchHandle(list, authUserOrganizationRepository.getBaseMapper()::batchInsert);
    }
}
