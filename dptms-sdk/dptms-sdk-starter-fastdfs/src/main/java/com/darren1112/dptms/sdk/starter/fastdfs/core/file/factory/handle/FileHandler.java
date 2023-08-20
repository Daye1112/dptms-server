package com.darren1112.dptms.sdk.starter.fastdfs.core.file.factory.handle;

import com.darren1112.dptms.common.spi.file.dto.FileDfsInfoDto;
import com.github.tobato.fastdfs.domain.fdfs.MetaData;

import java.io.InputStream;
import java.util.List;
import java.util.Set;

/**
 * 文件处理器-接口类
 *
 * @author darren
 * @since 2021/12/2
 */
public interface FileHandler {

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
    List<FileDfsInfoDto> uploadFile(InputStream fileStream, String fileName, Set<MetaData> metaDataSet) throws Exception;

    /**
     * 文件下载
     *
     * @param fileDfsInfoList 文件存储信息集合
     * @return {@link FileDfsInfoDto}
     * @throws Exception 异常
     * @author darren
     * @since 2021/12/1
     */
    byte[] download(List<FileDfsInfoDto> fileDfsInfoList) throws Exception;

    /**
     * 文件删除
     *
     * @param fileDfsInfoList 文件存储信息集合
     * @throws Exception 异常
     * @author darren
     * @since 2021/12/1
     */
    void delete(List<FileDfsInfoDto> fileDfsInfoList) throws Exception;
}
