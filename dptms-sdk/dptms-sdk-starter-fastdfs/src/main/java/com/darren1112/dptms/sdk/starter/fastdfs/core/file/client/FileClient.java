package com.darren1112.dptms.sdk.starter.fastdfs.core.file.client;

import com.darren1112.dptms.common.spi.file.dto.FileDfsInfoDto;
import com.github.tobato.fastdfs.domain.fdfs.MetaData;

import java.io.InputStream;
import java.util.List;
import java.util.Set;

/**
 * 文件Service
 *
 * @author darren
 * @date 2021/11/28 21:43
 */
public interface FileClient {

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
     * 批量删除文件
     *
     * @param fileDfsInfoList 文件存储信息集合
     * @author darren
     * @since 2023/08/19
     */
    void delete(List<FileDfsInfoDto> fileDfsInfoList) throws Exception;
}
