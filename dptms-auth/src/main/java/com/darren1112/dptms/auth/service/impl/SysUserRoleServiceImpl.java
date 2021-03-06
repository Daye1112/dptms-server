package com.darren1112.dptms.auth.service.impl;

import com.darren1112.dptms.auth.dao.SysUserRoleDao;
import com.darren1112.dptms.auth.service.SysUserRoleService;
import com.darren1112.dptms.common.core.util.StringUtil;
import com.darren1112.dptms.common.spi.sys.dto.SysRoleDto;
import com.darren1112.dptms.common.spi.sys.entity.SysUserRoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户角色ServiceImpl
 *
 * @author luyuhao
 * @since 2020/12/23 01:51
 */
@Service
@CacheConfig(cacheNames = "sysUserRole", keyGenerator = "keyGenerator")
@Transactional(rollbackFor = Throwable.class, readOnly = true)
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    /**
     * 分配角色
     *
     * @param userId  用户id
     * @param roleIds 角色ids，逗号分隔
     * @param updater 更新者
     * @author luyuhao
     * @since 20/12/23 01:48
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public void assignedRole(Long userId, String roleIds, Long updater) {
        // 清空用户已分配的角色
        sysUserRoleDao.deleteByUserId(userId, updater);
        if (StringUtil.isBlank(roleIds)) {
            return;
        }
        // 封装list
        List<SysUserRoleEntity> list = new ArrayList<>();
        String[] roleIdArr = roleIds.split(",");
        for (String roleId : roleIdArr) {
            SysUserRoleEntity entity = new SysUserRoleEntity();
            entity.setUserId(userId);
            entity.setRoleId(Long.valueOf(roleId));
            entity.setCreater(updater);
            entity.setUpdater(updater);
            list.add(entity);
        }
        // 批量插入
        sysUserRoleDao.batchInsert(list);
    }

    /**
     * 查询用户关联的角色list
     *
     * @param userId 用户id
     * @return {@link SysRoleDto}
     * @author luyuhao
     * @since 20/12/13 21:43
     */
    @Override
    public List<SysRoleDto> listUserAssigned(Long userId) {
        return sysUserRoleDao.listUserAssigned(userId);
    }
}
