package com.darren1112.dptms.file.service;

import com.darren1112.dptms.common.spi.file.dto.FileCenterDto;

import java.util.List;

/**
 * 文件中心Service
 *
 * @author luyuhao
 * @since 2021/12/18
 */
public interface FileCenterService {

    /**
     * 根据父节点id查询
     *
     * @param parentId 父节点id
     * @return {@link FileCenterDto}
     * @author luyuhao
     * @since 2021/12/18
     */
    List<FileCenterDto> list(Long parentId);

    /**
     * 新增文件/文件夹
     *
     * @param dto 文件/文件夹信息
     * @author luyuhao
     * @since 2021/12/19
     */
    void insert(FileCenterDto dto);
}
