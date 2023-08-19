package com.darren1112.dptms.file.service.impl;

import com.darren1112.dptms.common.core.exception.BadRequestException;
import com.darren1112.dptms.common.core.exception.ServiceHandleException;
import com.darren1112.dptms.common.spi.file.dto.FileDfsInfoDto;
import com.darren1112.dptms.common.spi.file.dto.FileInfoDto;
import com.darren1112.dptms.file.common.enums.FileManageErrorCodeEnum;
import com.darren1112.dptms.file.common.util.FileInfoUtil;
import com.darren1112.dptms.file.repository.FileDfsInfoRepository;
import com.darren1112.dptms.file.repository.FileInfoRepository;
import com.darren1112.dptms.file.service.FileInfoService;
import com.darren1112.dptms.sdk.starter.fastdfs.core.file.client.FileClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 文件信息ServiceImpl
 *
 * @author darren
 * @since 2021/12/1
 */
@Slf4j
@Service
@Transactional(rollbackFor = Throwable.class, readOnly = true)
public class FileInfoServiceImpl implements FileInfoService {

    @Autowired
    private FileInfoRepository fileInfoRepository;

    @Autowired
    private FileDfsInfoRepository fileDfsInfoRepository;

    @Autowired
    private FileClient fileClient;

    /**
     * 文件上传
     *
     * @param file   文件信息
     * @param userId 用户id
     * @return {@link FileInfoDto}
     * @author darren
     * @since 2021/12/05
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public FileInfoDto uploadFile(MultipartFile file, Long userId) {
        try {
            // 初始化上传信息
            FileInfoDto fileInfoDto = FileInfoUtil.create(file, userId);
            // 上传文件
            List<FileDfsInfoDto> fileDfsInfoList = fileClient.uploadFile(file.getInputStream(), fileInfoDto.getFileName(), null);
            // 保存结果
            fileInfoRepository.getBaseMapper().insert(fileInfoDto);
            // 设置存储关联信息
            fileDfsInfoList.forEach(item -> {
                item.setFileInfoId(fileInfoDto.getId());
                item.setCreater(fileInfoDto.getCreater());
                item.setUpdater(fileInfoDto.getUpdater());
            });
            // 批量新增存储信息
            fileDfsInfoRepository.getBaseMapper().batchInsert(fileDfsInfoList);
            return fileInfoDto;
        } catch (Exception e) {
            log.error("文件上传失败, 失败原因：" + e.getMessage(), e);
            throw new ServiceHandleException(FileManageErrorCodeEnum.FILE_UPLOAD_ERROR);
        }
    }

    /**
     * 文件下载
     *
     * @param fileId 文件id
     * @return {@link FileInfoDto}
     * @author darren
     * @since 2021/12/10
     */
    @Override
    public FileInfoDto download(Long fileId) {
        try {
            FileInfoDto fileInfo = getFullInfoById(fileId);
            byte[] fileBytes = fileClient.download(fileInfo.getFileDfsInfoList());
            fileInfo.setFileBytes(fileBytes);
            return fileInfo;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BadRequestException(FileManageErrorCodeEnum.FILE_DOWNLOAD_ERROR);
        }
    }

    /**
     * 根据文件id查询文件完整信息
     *
     * @param fileId 文件id
     * @return {@link FileInfoDto}
     * @author darren
     * @since 2021/12/10
     */
    @Override
    public FileInfoDto getFullInfoById(Long fileId) {
        FileInfoDto fileInfo = fileInfoRepository.getBaseMapper().getById(fileId);
        if (null == fileInfo) {
            throw new BadRequestException(FileManageErrorCodeEnum.FILE_NOT_EXIST);
        }
        List<FileDfsInfoDto> fileDfsInfoList = fileDfsInfoRepository.getBaseMapper().listByFileInfoId(fileId);
        fileInfo.setFileDfsInfoList(fileDfsInfoList);
        return fileInfo;
    }

    /**
     * 更新文件信息
     *
     * @param id       文件id
     * @param fileName 文件名
     * @param updater  更新者
     * @author darren
     * @since 2023/08/16
     */
    @Override
    public void updateFileName(Long id, String fileName, Long updater) {
        FileInfoDto fileInfoDto = new FileInfoDto();
        fileInfoDto.setId(id);
        fileInfoDto.setFileName(fileName);
        fileInfoDto.setUpdater(updater);

        fileInfoRepository.updateFileName(fileInfoDto);
    }
}
