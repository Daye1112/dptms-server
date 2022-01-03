package com.darren1112.dptms.auth.service.impl;

import com.darren1112.dptms.auth.common.enums.AuthErrorCodeEnum;
import com.darren1112.dptms.auth.common.util.MenuUtil;
import com.darren1112.dptms.auth.dao.AuthMenuDao;
import com.darren1112.dptms.auth.service.SysMenuService;
import com.darren1112.dptms.common.core.base.BaseService;
import com.darren1112.dptms.common.core.exception.BadRequestException;
import com.darren1112.dptms.common.spi.auth.dto.AuthMenuDto;
import com.darren1112.dptms.common.spi.auth.entity.AuthMenuEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 菜单ServiceImpl
 *
 * @author luyuhao
 * @since 2020/12/12 17:25
 */
@Service
@CacheConfig(cacheNames = "sysMenu", keyGenerator = "keyGenerator")
@Transactional(rollbackFor = Throwable.class, readOnly = true)
public class SysMenuServiceImpl extends BaseService implements SysMenuService {

    @Autowired
    private AuthMenuDao authMenuDao;

    /**
     * 获取用户的菜单
     *
     * @param userId 用户id
     * @return {@link AuthMenuDto}
     * @author luyuhao
     * @since 2021/01/17 19:34
     */
    @Override
    public List<AuthMenuDto> listMenuByUserId(Long userId) {
        return authMenuDao.listMenuByUserId(userId);
    }

    /**
     * 插入菜单信息
     *
     * @param entity 菜单参数
     * @return {@link Long 菜单id)
     * @author baojiazhong
     * @since 2020/12/16 11:02
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public Long insert(AuthMenuEntity entity) {
        validRepeat(entity, false);
        authMenuDao.insert(entity);
        return entity.getId();
    }

    /**
     * 根据id删除记录
     *
     * @param id      id
     * @param updater 更新者
     * @author baojiazhong
     * @since 2020/12/16 14:39
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public void deleteById(Long id, Long updater) {
        authMenuDao.deleteById(id, updater);
    }

    /**
     * 更新菜单信息
     *
     * @param entity 菜单参数
     * @return {@link Long)
     * @author baojiazhong
     * @since 2020/12/16 15:49
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public Long update(AuthMenuEntity entity) {
        validRepeat(entity, true);
        return authMenuDao.update(entity);
    }

    /**
     * 验证code是否重复
     *
     * @param entity   验证参数
     * @param isUpdate 是否更新
     * @author baojiazhong
     * @since 2020/12/16 11:05
     */
    private void validRepeat(AuthMenuEntity entity, boolean isUpdate) {
        AuthMenuDto param = new AuthMenuDto();
        param.setId(entity.getId());
        param.setMenuCode(entity.getMenuCode());
        param.setIsUpdate(isUpdate);
        Long count = authMenuDao.countByRepeat(param);
        if (count != null && count > 0) {
            throw new BadRequestException(AuthErrorCodeEnum.MENU_CODE_REPEAT);
        }
    }

    /**
     * 查询菜单树
     *
     * @return {@link AuthMenuDto}
     * @author luyuhao
     * @since 2021/01/03 23:29
     */
    @Override
    @Cacheable
    public AuthMenuDto listTree() {
        List<AuthMenuDto> sysMenuList = authMenuDao.list();
        return MenuUtil.buildTree(sysMenuList);
    }
}
