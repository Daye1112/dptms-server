package com.darren1112.dptms.auth.service.impl;

import com.darren1112.dptms.auth.common.util.MenuUtil;
import com.darren1112.dptms.auth.dao.AuthRoleMenuDao;
import com.darren1112.dptms.auth.service.SysRoleMenuService;
import com.darren1112.dptms.common.core.util.StringUtil;
import com.darren1112.dptms.common.spi.auth.dto.AuthMenuDto;
import com.darren1112.dptms.common.spi.auth.entity.AuthRoleMenuEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色菜单ServiceImpl
 *
 * @author luyuhao
 * @since 2020/12/13 23:09
 */
@Service
@CacheConfig(cacheNames = "sysRoleMenu", keyGenerator = "keyGenerator")
@Transactional(rollbackFor = Throwable.class, readOnly = true)
public class SysRoleMenuServiceImpl implements SysRoleMenuService {

    @Autowired
    private AuthRoleMenuDao authRoleMenuDao;

    /**
     * 查询角色关联的菜单list
     *
     * @param roleId 角色id
     * @return {@link AuthMenuDto}
     * @author luyuhao
     * @since 20/12/13 21:43
     */
    @Override
    public AuthMenuDto listRoleAssigned(Long roleId) {
        List<AuthMenuDto> sysMenuList = authRoleMenuDao.listRoleAssigned(roleId);
        List<Long> assignedIdList = sysMenuList.stream().filter(AuthMenuDto::getIsAssigned).map(AuthMenuDto::getId).collect(Collectors.toList());
        AuthMenuDto sysMenuDto = MenuUtil.buildTree(sysMenuList);
        if (sysMenuDto != null) {
            sysMenuDto.setAssignedIdList(assignedIdList);
        }
        return sysMenuDto;
    }

    /**
     * 分配菜单
     *
     * @param roleId  角色id
     * @param menuIds 菜单ids，逗号分隔
     * @param updater 更新者
     * @author luyuhao
     * @since 20/12/13 22:10
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public void assignedMenu(Long roleId, String menuIds, Long updater) {
        // 清空角色已分配的组织
        authRoleMenuDao.deleteByRoleId(roleId, updater);
        if (StringUtil.isBlank(menuIds)) {
            return;
        }
        // 封装list
        List<AuthRoleMenuEntity> list = new ArrayList<>();
        String[] menuIdArr = menuIds.split(",");
        for (String menuId : menuIdArr) {
            AuthRoleMenuEntity entity = new AuthRoleMenuEntity();
            entity.setRoleId(roleId);
            entity.setMenuId(Long.valueOf(menuId));
            entity.setCreater(updater);
            entity.setUpdater(updater);
            list.add(entity);
        }
        // 批量插入
        authRoleMenuDao.batchInsert(list);
    }
}
