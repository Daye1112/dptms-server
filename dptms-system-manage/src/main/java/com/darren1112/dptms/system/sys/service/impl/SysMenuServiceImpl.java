package com.darren1112.dptms.system.sys.service.impl;

import com.darren1112.dptms.common.core.base.BaseService;
import com.darren1112.dptms.common.core.exception.BadRequestException;
import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.sys.dto.SysMenuDto;
import com.darren1112.dptms.common.spi.sys.entity.SysMenuEntity;
import com.darren1112.dptms.system.common.enums.SystemManageErrorCodeEnum;
import com.darren1112.dptms.system.sys.dao.SysMenuDao;
import com.darren1112.dptms.system.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
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
    private SysMenuDao sysMenuDao;

    /**
     * 插入菜单信息
     *
     * @param entity 菜单参数
     * @author baojiazhong
     * @return {@link Long 菜单id)
     * @date 2020/12/16 11:02
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public Long insert(SysMenuEntity entity) {
        validRepeat(entity, false);
        sysMenuDao.insert(entity);
        return entity.getId();
    }

    /**
     * 分页查询菜单
     *
     * @param dto       筛选参数
     * @param pageParam 分页参数
     * @return {@link PageBean<SysMenuDto>)
     * @author baojiazhong
     * @date 2020/12/16 15:11
     */
    @Override
    public PageBean<SysMenuDto> listPage(PageParam pageParam, SysMenuDto dto) {
        List<SysMenuDto> list = sysMenuDao.listPage(pageParam, dto);
        Long count = sysMenuDao.listPageCount(dto);
        return createPageBean(pageParam, count, list);
    }

    /**
     * 根据id删除记录
     *
     * @param id        id
     * @param updater   更新者
     * @author baojiazhong
     * @date 2020/12/16 14:39
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public void deleteById(Long id, Long updater) {
        sysMenuDao.deleteById(id, updater);
    }

    /**
     * 更新菜单信息
     *
     * @param entity 菜单参数
     * @return {@link Long)
     * @author baojiazhong
     * @date 2020/12/16 15:49
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public Long update(SysMenuEntity entity) {
        validRepeat(entity, true);
        return sysMenuDao.update(entity);
    }

    /**
     * 验证code是否重复
     *
     * @param entity    验证参数
     * @param isUpdate  是否更新
     * @author baojiazhong
     * @date 2020/12/16 11:05
     */
    private void validRepeat(SysMenuEntity entity, boolean isUpdate) {
        SysMenuDto param = new SysMenuDto();
        param.setId(entity.getId());
        param.setMenuCode(entity.getMenuCode());
        param.setIsUpdate(isUpdate);
        Long count = sysMenuDao.countByRepeat(param);
        if (count != null && count > 0) {
            throw new BadRequestException(SystemManageErrorCodeEnum.MENU_CODE_REPEAT);
        }

    }
}
