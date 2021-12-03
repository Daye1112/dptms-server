package com.darren1112.dptms.common.fastdfs.starter.core.factory.handle;

import cn.hutool.core.io.FileUtil;
import com.darren1112.dptms.common.fastdfs.starter.properties.FastDfsProperties;
import com.darren1112.dptms.common.spi.file.dto.FileDfsInfoDto;
import com.darren1112.dptms.common.spi.file.dto.FileInfoDto;
import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;

import java.io.InputStream;
import java.util.Set;

/**
 * 小文件简单处理器
 *
 * @author luyuhao
 * @since 2021/12/2
 */
public class SmallFileHandler implements FileHandler {

    private FastFileStorageClient fastFileStorageClient;

    private FastDfsProperties fastDfsProperties;

    public SmallFileHandler(FastFileStorageClient fastFileStorageClient, FastDfsProperties fastDfsProperties) {
        this.fastFileStorageClient = fastFileStorageClient;
        this.fastDfsProperties = fastDfsProperties;
    }

    /**
     * 文件上传
     *
     * @param fileStream  文件流
     * @param fileName    文件名
     * @param metaDataSet 元数据信息
     * @return {@link FileInfoDto}
     * @throws Exception 异常
     * @author luyuhao
     * @since 2021/12/1
     */
    @Override
    public FileInfoDto uploadFile(InputStream fileStream, String fileName, Set<MetaData> metaDataSet) throws Exception{
        FileInfoDto result = new FileInfoDto();
        // 提取文件信息
        int fileSize = fileStream.available();
        String extName = FileUtil.extName(fileName);


        // 文件上传
        StorePath storePath = fastFileStorageClient.uploadFile(fileStream, fileSize, extName, metaDataSet);
        // 添加存储信息
        FileDfsInfoDto fileDfsInfoDto = new FileDfsInfoDto();

    }
}
