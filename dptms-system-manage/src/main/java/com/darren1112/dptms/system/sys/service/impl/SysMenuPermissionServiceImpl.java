package com.darren1112.dptms.system.sys.service.impl;

import com.darren1112.dptms.common.core.util.StringUtil;
import com.darren1112.dptms.common.spi.sys.dto.SysPermissionDto;
import com.darren1112.dptms.common.spi.sys.entity.SysMenuPermissionEntity;
import com.darren1112.dptms.system.sys.dao.SysMenuPermissionDao;
import com.darren1112.dptms.system.sys.service.SysMenuPermissionService;
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
@CacheConfig(cacheNames = "sysMenuPermission", keyGenerator = "keyGenerator")
@Transactional(rollbackFor = Throwable.class, readOnly = true)
public class SysMenuPermissionServiceImpl implements SysMenuPermissionService {

    @Autowired
    private SysMenuPermissionDao sysMenuPermissionDao;

    /**
     * 查询菜单关联的权限list
     *
     * @param menuId 菜单id
     * @return {@link SysPermissionDto)
     * @author baojiazhong
     * @date 2020/12/22 22:47
     */
    @Override
    public List<SysPermissionDto> listMenuAssigned(Long menuId) {
        return sysMenuPermissionDao.listMenuAssigned(menuId);
    }

    /**
     * 绑定权限
     *
     * @param menuId  菜单id
     * @param perIds  权限ids，逗号分隔
     * @param updater 更新者
     * @author baojiazhong
     * @date 2020/12/22 23:10
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public void assignedPer(Long menuId, String perIds, Long updater) {
        sysMenuPermissionDao.deleteByMenuId(menuId, updater);
        if (StringUtil.isBlank(perIds)) {
            return;
        }
        List<SysMenuPermissionEntity> list = new ArrayList<>();
        String[] perIdArr = perIds.split(",");
        for (String perId : perIdArr) {
            SysMenuPermissionEntity entity = new SysMenuPermissionEntity();
            entity.setMenuId(menuId);
            entity.setPerId(Long.valueOf(perId));
            entity.setCreater(updater);
            entity.setUpdater(updater);
            list.add(entity);
        }
        sysMenuPermissionDao.batchInsert(list);
    }
}
