package com.darren1112.dptms.file.service.impl;

import com.darren1112.dptms.common.spi.file.dto.FileCenterDto;
import com.darren1112.dptms.file.repository.FileCenterRepository;
import com.darren1112.dptms.file.service.FileCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 文件中心ServiceImpl
 *
 * @author luyuhao
 * @since 2021/12/18
 */
@Service
@Transactional(rollbackFor = Throwable.class, readOnly = true)
@CacheConfig(cacheNames = "fileCenter", keyGenerator = "keyGenerator")
public class FileCenterServiceImpl implements FileCenterService {

    @Autowired
    private FileCenterRepository fileCenterRepository;

    /**
     * 根据父节点id查询
     *
     * @param parentId 父节点id
     * @return {@link FileCenterDto}
     * @author luyuhao
     * @since 2021/12/18
     */
    @Override
    @Cacheable
    public List<FileCenterDto> list(Long parentId) {
        return fileCenterRepository.getBaseMapper().list(parentId);
    }

    /**
     * 新增文件/文件夹
     *
     * @param dto 文件/文件夹信息
     * @author luyuhao
     * @since 2021/12/19
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public void insert(FileCenterDto dto) {
        fileCenterRepository.getBaseMapper().insert(dto);
    }
}
