package com.darren1112.dptms.system.sys.service;

import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.sys.dto.SysUserDto;
import com.darren1112.dptms.common.spi.sys.entity.SysUserEntity;

/**
 * 系统用户Service
 *
 * @author luyuhao
 * @date 2020/07/23 02:42
 */
public interface SysUserService {

    /**
     * 根据id查询用户
     *
     * @param id id
     * @return {@link SysUserDto}
     * @author luyuhao
     * @date 20/11/30 23:12
     */
    SysUserDto getById(Long id);

    /**
     * 插入用户信息
     *
     * @param entity 用户参数
     * @return {@link Long}
     * @author luyuhao
     * @date 20/12/10 01:08
     */
    Long insert(SysUserEntity entity);

    /**
     * 分页查询用户
     *
     * @param dto       筛选参数
     * @param pageParam 分页参数
     * @return {@link SysUserDto}
     * @author luyuhao
     * @date 20/12/10 01:08
     */
    PageBean<SysUserDto> listPage(PageParam pageParam, SysUserDto dto);

    /**
     * 更新用户信息
     *
     * @param entity 用户参数
     * @return {@link Long}
     * @author luyuhao
     * @date 20/12/10 01:08
     */
    Long update(SysUserEntity entity);

    /**
     * 根据id删除记录
     *
     * @param id      记录id
     * @param updater 更新者
     * @author luyuhao
     * @date 20/12/10 01:08
     */
    void deleteById(Long id, Long updater);
}
