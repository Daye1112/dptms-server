package com.darren1112.dptms.system.sys.service;

import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.sys.dto.SysRedisDto;

/**
 * redis service
 *
 * @author luyuhao
 * @date 2021/01/31 00:04
 */
public interface RedisService {

    /**
     * 根据key的前缀分页查缓存数据
     *
     * @param keyPrefix key前缀
     * @param pageParam 分页参数
     * @return {@link SysRedisDto}
     * @author luyuhao
     * @date 2021/01/31 00:07
     */
    PageBean<SysRedisDto> listPage(String keyPrefix, PageParam pageParam);

    /**
     * 新增缓存
     *
     * @param sysRedisDto 缓存信息
     * @author luyuhao
     * @date 2021/01/31 01:01
     */
    void insert(SysRedisDto sysRedisDto);

    /**
     * 删除缓存
     *
     * @param sysRedisDto 缓存信息
     * @author luyuhao
     * @date 2021/01/31 00:04
     */
    void deleteByKey(SysRedisDto sysRedisDto);
}
