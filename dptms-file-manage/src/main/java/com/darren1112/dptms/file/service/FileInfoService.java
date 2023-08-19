package com.darren1112.dptms.file.service;

import com.darren1112.dptms.common.spi.file.dto.FileInfoDto;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件信息Service
 *
 * @author darren
 * @since 2021/12/1
 */
public interface FileInfoService {

    /**
     * 文件上传
     *
     * @param file   文件信息
     * @param userId 用户id
     * @return {@link FileInfoDto}
     * @author darren
     * @since 2021/12/05
     */
    FileInfoDto uploadFile(MultipartFile file, Long userId);

    /**
     * 文件下载
     *
     * @param fileId 文件id
     * @return {@link FileInfoDto}
     * @author darren
     * @since 2021/12/10
     */
    FileInfoDto download(Long fileId);

    /**
     * 根据文件id查询文件完整信息
     *
     * @param fileId 文件id
     * @return {@link FileInfoDto}
     * @author darren
     * @since 2021/12/10
     */
    FileInfoDto getFullInfoById(Long fileId);

    /**
     * 更新文件信息
     *
     * @param id       文件id
     * @param fileName 文件名
     * @param updater  更新者
     * @author darren
     * @since 2023/08/16
     */
    void updateFileName(Long id, String fileName, Long updater);
}
