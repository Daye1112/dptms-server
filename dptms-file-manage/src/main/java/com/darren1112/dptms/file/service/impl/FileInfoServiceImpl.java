package com.darren1112.dptms.file.service.impl;

import com.darren1112.dptms.common.core.exception.ServiceHandleException;
import com.darren1112.dptms.common.fastdfs.starter.core.file.client.FileClient;
import com.darren1112.dptms.common.spi.file.dto.FileDfsInfoDto;
import com.darren1112.dptms.common.spi.file.dto.FileInfoDto;
import com.darren1112.dptms.file.common.enums.FileManageErrorCodeEnum;
import com.darren1112.dptms.file.common.util.FileInfoUtil;
import com.darren1112.dptms.file.dao.FileDfsInfoDao;
import com.darren1112.dptms.file.dao.FileInfoDao;
import com.darren1112.dptms.file.service.FileInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 文件信息ServiceImpl
 *
 * @author luyuhao
 * @since 2021/12/1
 */
@Slf4j
@Service
@Transactional(rollbackFor = Throwable.class, readOnly = true)
public class FileInfoServiceImpl implements FileInfoService {

    @Autowired
    private FileInfoDao fileInfoDao;

    @Autowired
    private FileDfsInfoDao fileDfsInfoDao;

    @Autowired
    private FileClient fileClient;

    /**
     * 文件上传
     *
     * @param file   文件信息
     * @param userId 用户id
     * @author luyuhao
     * @since 2021/12/05
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void uploadFile(MultipartFile file, Long userId) {
        try {
            // 初始化上传信息
            FileInfoDto fileInfoDto = FileInfoUtil.create(file, userId);
            // 上传文件
            List<FileDfsInfoDto> fileDfsInfoList = fileClient.uploadFile(file.getInputStream(), fileInfoDto.getFileName(), null);
            // 保存结果
            fileInfoDao.insert(fileInfoDto);
            // 设置存储关联信息
            fileDfsInfoList.forEach(item -> {
                item.setFileInfoId(fileInfoDto.getId());
                item.setCreater(fileInfoDto.getCreater());
                item.setUpdater(fileInfoDto.getUpdater());
            });
            // 批量新增存储信息
            fileDfsInfoDao.batchInsert(fileDfsInfoList);
        } catch (Exception e) {
            log.error("文件上传失败, 失败原因：" + e.getMessage(), e);
            throw new ServiceHandleException(FileManageErrorCodeEnum.FILE_UPLOAD_ERROR);
        }
    }
}
