package com.darren1112.dptms.system.sys.service;

import com.darren1112.dptms.common.spi.sys.dto.SysOrganizationDto;

import java.util.List;

/**
 * 用户组织Service
 *
 * @author luyuhao
 * @date 2020/12/13 22:19
 */
public interface SysUserOrganizationService {

    /**
     * 查询用户关联的组织list
     *
     * @param userId 用户id
     * @return {@link SysOrganizationDto}
     * @author luyuhao
     * @date 20/12/13 21:43
     */
    List<SysOrganizationDto> listUserAssigned(Long userId);

    /**
     * 分配组织
     *
     * @param userId  用户id
     * @param orgIds  组织ids，逗号分隔
     * @param updater 更新者
     * @author luyuhao
     * @date 20/12/13 22:10
     */
    void assignedOrg(Long userId, String orgIds, Long updater);
}
