package com.darren1112.dptms.file.service;

import com.darren1112.dptms.common.spi.file.dto.FileInfoDto;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件信息Service
 *
 * @author luyuhao
 * @since 2021/12/1
 */
public interface FileInfoService {

    /**
     * 文件上传
     *
     * @param file   文件信息
     * @param userId 用户id
     * @return {@link FileInfoDto}
     * @author luyuhao
     * @since 2021/12/05
     */
    FileInfoDto uploadFile(MultipartFile file, Long userId);
}
