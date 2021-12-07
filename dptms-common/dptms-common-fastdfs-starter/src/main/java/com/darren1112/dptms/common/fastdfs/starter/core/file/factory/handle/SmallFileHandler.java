package com.darren1112.dptms.common.fastdfs.starter.core.file.factory.handle;

import cn.hutool.core.io.FileUtil;
import com.darren1112.dptms.common.core.util.CollectionUtil;
import com.darren1112.dptms.common.spi.file.dto.FileDfsInfoDto;
import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;

import java.io.InputStream;
import java.util.List;
import java.util.Set;

/**
 * 小文件简单处理器
 *
 * @author luyuhao
 * @since 2021/12/2
 */
public class SmallFileHandler implements FileHandler {

    private FastFileStorageClient fastFileStorageClient;

    public SmallFileHandler(FastFileStorageClient fastFileStorageClient) {
        this.fastFileStorageClient = fastFileStorageClient;
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
        // 提取文件信息
        int fileSize = fileStream.available();
        // 文件上传
        StorePath storePath = fastFileStorageClient.uploadFile(fileStream, fileSize, FileUtil.extName(fileName), metaDataSet);
        // 设置属性
        FileDfsInfoDto result = FileDfsInfoDto.create(storePath.getGroup(), storePath.getPath(), (long) fileSize);
        return CollectionUtil.packToList(result);
    }
}
