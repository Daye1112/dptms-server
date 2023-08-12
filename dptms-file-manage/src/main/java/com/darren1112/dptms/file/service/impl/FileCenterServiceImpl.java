package com.darren1112.dptms.file.service.impl;

import com.darren1112.dptms.common.core.util.FileUtil;
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
import java.util.Optional;

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
        List<FileCenterDto> list = fileCenterRepository.getBaseMapper().list(parentId);
        list.forEach(item -> {
            String fileSizeStr = Optional.ofNullable(item.getFileSize())
                    .map(subItem -> FileUtil.readableFileSize(subItem).toUpperCase())
                    .orElse("0 KB");
            String fileSizeCountStr = Optional.ofNullable(item.getFileSizeCount())
                    .map(subItem -> FileUtil.readableFileSize(subItem).toUpperCase())
                    .orElse("0 KB");
            item.setFileSizeStr(fileSizeStr);
            item.setFileSizeCountStr(fileSizeCountStr);
        });
        return list;
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

    /**
     * 更新文件/文件夹
     *
     * @param dto 文件/文件夹信息
     * @author darren
     * @since 2023/08/12
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public void update(FileCenterDto dto) {
        fileCenterRepository.updateInfo(dto);
    }

    /**
     * 删除文件/文件夹
     *
     * @param dto 文件/文件夹信息
     * @author darren
     * @since 2023/08/12
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Throwable.class)
    public void deleteById(FileCenterDto dto) {
        fileCenterRepository.deleteById(dto);
        // TODO 删除文件/文件夹下的所有文件
    }
}
