package com.darren1112.dptms.file.service.impl;

import com.darren1112.dptms.common.fastdfs.starter.core.client.FileClient;
import com.darren1112.dptms.common.spi.file.dto.FileInfoDto;
import com.darren1112.dptms.file.dao.FileInfoDao;
import com.darren1112.dptms.file.service.FileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件信息ServiceImpl
 *
 * @author luyuhao
 * @since 2021/12/1
 */
@Service
@Transactional(rollbackFor = Throwable.class, readOnly = true)
public class FileInfoServiceImpl implements FileInfoService {

    @Autowired
    private FileInfoDao fileInfoDao;

    @Autowired
    private FileClient fileClient;

    /**
     * 文件上传
     *
     * @param file 文件信息
     * @author luyuhao
     * @since 2021/12/05
     */
    @Override
    public void uploadFile(MultipartFile file) {
        FileInfoDto fileInfoDao = new FileInfoDto();
    }
}
