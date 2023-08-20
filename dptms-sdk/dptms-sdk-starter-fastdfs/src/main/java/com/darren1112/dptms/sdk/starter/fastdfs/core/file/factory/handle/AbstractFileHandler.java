package com.darren1112.dptms.sdk.starter.fastdfs.core.file.factory.handle;

import com.darren1112.dptms.common.core.util.CollectionUtil;
import com.darren1112.dptms.common.core.util.FileUtil;
import com.darren1112.dptms.common.spi.file.dto.FileDfsInfoDto;
import com.darren1112.dptms.sdk.starter.fastdfs.core.fastdfs.factory.FastDfsHandlerFactory;
import com.darren1112.dptms.sdk.starter.fastdfs.core.fastdfs.factory.handle.FastDfsHandler;
import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;

import java.io.InputStream;
import java.util.List;
import java.util.Set;

/**
 * 文件处理抽象类
 *
 * @author darren
 * @since 2021/12/8
 */
public abstract class AbstractFileHandler implements FileHandler {

    FastDfsHandlerFactory fastDfsHandlerFactory;

    AbstractFileHandler(FastDfsHandlerFactory fastDfsHandlerFactory) {
        this.fastDfsHandlerFactory = fastDfsHandlerFactory;
    }

    /**
     * 简单文件下载封装方法
     *
     * @param fileDfsInfo 文件存储信息
     * @return {@link FileDfsInfoDto}
     * @author darren
     * @since 2021/12/11
     */
    FileDfsInfoDto simpleDownload(FileDfsInfoDto fileDfsInfo) {
        try {
            byte[] fileBytes = fastDfsHandlerFactory.create()
                    .downloadFile(fileDfsInfo.getFileGroup(), fileDfsInfo.getFilePath(), new DownloadByteArray());
            fileDfsInfo.setFileBytes(fileBytes);
            return fileDfsInfo;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 简单上传封装方法
     *
     * @param fileStream  文件流
     * @param fileName    文件名
     * @param metaDataSet 元信息
     * @param fileOrder   文件排序
     * @return {@link FileDfsInfoDto}
     * @author darren
     * @since 2021/12/8
     */
    FileDfsInfoDto simpleUpload(InputStream fileStream, String fileName, Set<MetaData> metaDataSet, Integer fileOrder) {
        try {
            long fileSize = fileStream.available();
            // 文件上传
            StorePath storePath = fastDfsHandlerFactory.create()
                    .uploadFile(fileStream, fileSize, FileUtil.extName(fileName), metaDataSet);
            // 设置属性
            return FileDfsInfoDto.create(storePath.getGroup(), storePath.getPath(), fileSize, fileOrder);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 文件删除
     *
     * @param fileDfsInfoList 文件存储信息集合
     * @throws Exception 异常
     * @author darren
     * @since 2021/12/1
     */
    @Override
    public void delete(List<FileDfsInfoDto> fileDfsInfoList) throws Exception {
        // 为空则不进行处理
        if (CollectionUtil.isEmpty(fileDfsInfoList)) {
            return;
        }

        FastDfsHandler fastDfsHandler = fastDfsHandlerFactory.create();

        // 批量删除
        for (FileDfsInfoDto dto : fileDfsInfoList) {
            fastDfsHandler.deleteFile(dto.getFileGroup(), dto.getFilePath());
        }
    }
}
