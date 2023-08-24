package com.darren1112.dptms.auth.service;

import com.darren1112.dptms.common.spi.auth.dto.AuthOrganizationDto;
import com.darren1112.dptms.common.spi.auth.entity.AuthOrganizationEntity;
import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;

import java.util.List;

/**
 * 组织Service
 *
 * @author darren
 * @since 2020/08/16 01:42
 */
public interface AuthOrganizationService {

    /**
     * 插入组织信息
     *
     * @param dto 组织参数
     * @return {@link Long}
     * @author darren
     * @since 20/12/10 01:08
     */
    Long insert(AuthOrganizationDto dto);

    /**
     * 更新组织信息
     *
     * @param entity 组织参数
     * @return {@link Long}
     * @author darren
     * @since 20/12/10 01:08
     */
    Long update(AuthOrganizationEntity entity);

    /**
     * 根据id删除记录
     *
     * @param id      记录id
     * @param updater 更新者
     * @author darren
     * @since 20/12/10 01:08
     */
    void deleteById(Long id, Long updater);

    /**
     * 分页查询组织信息
     *
     * @param pageParam 分页参数
     * @param param     筛选参数
     * @return {@link AuthOrganizationDto}
     * @author darren
     * @since 20/12/10 01:08
     */
    PageBean<AuthOrganizationDto> listPage(PageParam pageParam, AuthOrganizationDto param);

    /**
     * 根据用户id查询组织集合
     *
     * @param userId 用户id
     * @return {@link AuthOrganizationDto}
     * @author darren
     * @since 2023/08/20
     */
    List<AuthOrganizationDto> listByUserId(Long userId);
}
