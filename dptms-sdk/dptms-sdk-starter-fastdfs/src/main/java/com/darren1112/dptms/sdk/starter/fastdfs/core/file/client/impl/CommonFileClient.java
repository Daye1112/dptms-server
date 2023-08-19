package com.darren1112.dptms.sdk.starter.fastdfs.core.file.client.impl;

import com.darren1112.dptms.sdk.starter.fastdfs.core.file.client.FileClient;
import com.darren1112.dptms.sdk.starter.fastdfs.core.file.factory.FileHandlerFactory;
import com.darren1112.dptms.common.spi.file.dto.FileDfsInfoDto;
import com.github.tobato.fastdfs.domain.fdfs.MetaData;

import java.io.InputStream;
import java.util.List;
import java.util.Set;

/**
 * 文件ServiceImpl
 *
 * @author darren
 * @since 2021/11/28
 */
public class CommonFileClient implements FileClient {

    private FileHandlerFactory fileHandlerFactory;

    public CommonFileClient(FileHandlerFactory fileHandlerFactory) {
        this.fileHandlerFactory = fileHandlerFactory;
    }

    /**
     * 文件上传
     *
     * @param fileStream  文件流
     * @param fileName    文件名
     * @param metaDataSet 元数据信息
     * @return {@link FileDfsInfoDto}
     * @throws Exception 异常
     * @author darren
     * @since 2021/12/1
     */
    @Override
    public List<FileDfsInfoDto> uploadFile(InputStream fileStream, String fileName, Set<MetaData> metaDataSet) throws Exception {
        return fileHandlerFactory.uploadCreate(fileStream.available())
                .uploadFile(fileStream, fileName, metaDataSet);
    }

    /**
     * 文件下载
     *
     * @param fileDfsInfoList 文件存储信息集合
     * @return {@link FileDfsInfoDto}
     * @throws Exception 异常
     * @author darren
     * @since 2021/12/1
     */
    @Override
    public byte[] download(List<FileDfsInfoDto> fileDfsInfoList) throws Exception {
        return fileHandlerFactory.downloadCreate(fileDfsInfoList.size())
                .download(fileDfsInfoList);
    }
}
