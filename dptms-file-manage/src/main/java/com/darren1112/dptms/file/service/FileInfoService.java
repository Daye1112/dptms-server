package com.darren1112.dptms.file.service;

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
     * @param file 文件信息
     * @author luyuhao
     * @since 2021/12/05
     */
    void uploadFile(MultipartFile file);
}
