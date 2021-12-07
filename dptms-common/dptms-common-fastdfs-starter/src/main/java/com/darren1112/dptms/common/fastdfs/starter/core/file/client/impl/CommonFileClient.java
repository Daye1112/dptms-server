package com.darren1112.dptms.common.fastdfs.starter.core.file.client.impl;

import com.darren1112.dptms.common.core.util.FileUtil;
import com.darren1112.dptms.common.fastdfs.starter.core.file.factory.FileHandlerFactory;
import com.darren1112.dptms.common.fastdfs.starter.properties.FastDfsProperties;
import com.darren1112.dptms.common.fastdfs.starter.core.file.client.FileClient;
import com.darren1112.dptms.common.spi.file.dto.FileDfsInfoDto;
import com.darren1112.dptms.common.spi.file.entity.FileDfsInfoEntity;
import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;

import java.io.InputStream;
import java.util.*;

/**
 * 文件ServiceImpl
 *
 * @author luyuhao
 * @since 2021/11/28
 */
public class CommonFileClient implements FileClient {

    private FastFileStorageClient fastFileStorageClient;

    private FastDfsProperties fastDfsProperties;

    private FileHandlerFactory fileHandlerFactory;

    public CommonFileClient(FastFileStorageClient fastFileStorageClient,
                            FastDfsProperties fastDfsProperties,
                            FileHandlerFactory fileHandlerFactory) {
        this.fastFileStorageClient = fastFileStorageClient;
        this.fastDfsProperties = fastDfsProperties;
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
     * @author luyuhao
     * @since 2021/12/1
     */
    @Override
    public List<FileDfsInfoDto> uploadFile(InputStream fileStream, String fileName, Set<MetaData> metaDataSet) throws Exception {
        return fileHandlerFactory.create(fileStream.available())
                .uploadFile(fileStream, fileName, metaDataSet);
    }

    /**
     * 文件下载
     *
     * @param fileDfsInfoList 文件存储信息集合
     * @return {@link FileDfsInfoDto}
     * @throws Exception 异常
     * @author luyuhao
     * @since 2021/12/1
     */
    @Override
    public byte[] download(List<FileDfsInfoDto> fileDfsInfoList) throws Exception {
        byte[] result = null;
        // 根据order排序
        fileDfsInfoList.sort(Comparator.comparing(FileDfsInfoEntity::getFileOrder));
        for (FileDfsInfoDto subFileInfo : fileDfsInfoList) {
            byte[] subFileBytes = fastFileStorageClient.downloadFile(subFileInfo.getFileGroup(), subFileInfo.getFilePath(), new DownloadByteArray());
            result = FileUtil.mergeBytes(result, subFileBytes);
        }
        return result;
    }
}
