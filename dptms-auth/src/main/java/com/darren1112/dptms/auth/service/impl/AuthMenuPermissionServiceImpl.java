package com.darren1112.dptms.auth.service.impl;

import com.darren1112.dptms.auth.repository.AuthMenuPermissionRepository;
import com.darren1112.dptms.auth.service.AuthMenuPermissionService;
import com.darren1112.dptms.common.core.util.DatabaseUtil;
import com.darren1112.dptms.common.core.util.StringUtil;
import com.darren1112.dptms.common.spi.auth.dto.AuthPermissionDto;
import com.darren1112.dptms.common.spi.auth.entity.AuthMenuPermissionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单权限service
 *
 * @author baojiazhong
 * @since 2020/12/22 22:43
 */
@Service
@CacheConfig(cacheNames = "authMenuPermission", keyGenerator = "keyGenerator")
@Transactional(rollbackFor = Throwable.class, readOnly = true)
public class AuthMenuPermissionServiceImpl implements AuthMenuPermissionService {

    @Autowired
    private AuthMenuPermissionRepository authMenuPermissionRepository;

    /**
     * 查询菜单关联的权限list
     *
     * @param menuId 菜单id
     * @return {@link AuthPermissionDto )
     * @author baojiazhong
     * @since 2020/12/22 22:47
     */
    @Override
    public List<AuthPermissionDto> listMenuAssigned(Long menuId) {
        return authMenuPermissionRepository.getBaseMapper().listMenuAssigned(menuId);
    }

    /**
     * 绑定权限
     *
     * @param menuId  菜单id
     * @param perIds  权限ids，逗号分隔
     * @param updater 更新者
     * @author baojiazhong
     * @since 2020/12/22 23:10
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public void assignedPer(Long menuId, String perIds, Long updater) {
        authMenuPermissionRepository.getBaseMapper().deleteByMenuId(menuId, updater);
        if (StringUtil.isBlank(perIds)) {
            return;
        }
        List<AuthMenuPermissionEntity> list = new ArrayList<>();
        String[] perIdArr = perIds.split(",");
        for (String perId : perIdArr) {
            AuthMenuPermissionEntity entity = new AuthMenuPermissionEntity();
            entity.setMenuId(menuId);
            entity.setPerId(Long.valueOf(perId));
            entity.setCreater(updater);
            entity.setUpdater(updater);
            list.add(entity);
        }
        DatabaseUtil.batchHandle(list, authMenuPermissionRepository.getBaseMapper()::batchInsert);
    }

    /**
     * 根据菜单id查询权限list
     *
     * @param menuId 菜单id
     * @return {@link AuthPermissionDto}
     * @author luyuhao
     * @since 2021/01/04 23:53
     */
    @Override
    public List<AuthPermissionDto> listByMenuId(Long menuId) {
        return authMenuPermissionRepository.getBaseMapper().listByMenuId(menuId);
    }
}
