package com.darren1112.dptms.auth.service;

import com.darren1112.dptms.common.spi.auth.dto.AuthOrganizationDto;

import java.util.List;

/**
 * 用户组织Service
 *
 * @author darren
 * @since 2020/12/13 22:19
 */
public interface AuthUserOrganizationService {

    /**
     * 查询用户关联的组织list
     *
     * @param userId 用户id
     * @return {@link AuthOrganizationDto}
     * @author darren
     * @since 20/12/13 21:43
     */
    List<AuthOrganizationDto> listUserAssigned(Long userId);

    /**
     * 分配组织
     *
     * @param userId  用户id
     * @param orgIds  组织ids，逗号分隔
     * @param updater 更新者
     * @author darren
     * @since 20/12/13 22:10
     */
    void assignedOrg(Long userId, String orgIds, Long updater);
}
