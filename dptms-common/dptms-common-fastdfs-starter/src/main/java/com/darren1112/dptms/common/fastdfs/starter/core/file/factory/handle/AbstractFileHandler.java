package com.darren1112.dptms.common.fastdfs.starter.core.file.factory.handle;

import com.darren1112.dptms.common.core.util.FileUtil;
import com.darren1112.dptms.common.fastdfs.starter.core.fastdfs.factory.FastDfsHandlerFactory;
import com.darren1112.dptms.common.spi.file.dto.FileDfsInfoDto;
import com.darren1112.dptms.common.spi.file.entity.FileDfsInfoEntity;
import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;

import java.io.InputStream;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * 文件处理抽象类
 *
 * @author luyuhao
 * @since 2021/12/8
 */
public abstract class AbstractFileHandler implements FileHandler {

    protected FastDfsHandlerFactory fastDfsHandlerFactory;

    public AbstractFileHandler(FastDfsHandlerFactory fastDfsHandlerFactory) {
        this.fastDfsHandlerFactory = fastDfsHandlerFactory;
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
            byte[] subFileBytes = fastDfsHandlerFactory.create()
                    .downloadFile(subFileInfo.getFileGroup(), subFileInfo.getFilePath(), new DownloadByteArray());
            result = FileUtil.mergeBytes(result, subFileBytes);
        }
        return result;
    }

    /**
     * 简单上传封装方法
     *
     * @param fileStream  文件流
     * @param fileName    文件名
     * @param metaDataSet 元信息
     * @param fileOrder   文件排序
     * @return {@link FileDfsInfoDto}
     * @author luyuhao
     * @since 2021/12/8
     */
    protected FileDfsInfoDto simpleUpload(InputStream fileStream, String fileName, Set<MetaData> metaDataSet, Integer fileOrder) {
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
}
