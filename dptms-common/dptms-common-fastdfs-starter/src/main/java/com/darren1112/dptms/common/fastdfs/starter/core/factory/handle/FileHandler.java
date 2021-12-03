package com.darren1112.dptms.common.fastdfs.starter.core.factory.handle;

import com.darren1112.dptms.common.spi.file.dto.FileInfoDto;
import com.github.tobato.fastdfs.domain.fdfs.MetaData;

import java.io.InputStream;
import java.util.Set;

/**
 * 文件处理器-接口类
 *
 * @author luyuhao
 * @since 2021/12/2
 */
public interface FileHandler {

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
    FileInfoDto uploadFile(InputStream fileStream, String fileName, Set<MetaData> metaDataSet) throws Exception;
}
