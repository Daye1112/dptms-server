package com.darren1112.dptms.common.fastdfs.starter.core.fastdfs.factory.handle;

import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadCallback;

import java.io.InputStream;
import java.util.Set;

/**
 * fastDfs处理器接口类
 *
 * @author luyuhao
 * @since 2021/12/7
 */
public interface FastDfsHandler {

    /**
     * 下载文件
     *
     * @param groupName 组名
     * @param path      路径
     * @param callback  回调
     * @return {@link T}
     * @throws Exception 异常
     * @author luyuhao
     * @since 2021/12/7
     */
    <T> T downloadFile(String groupName, String path, DownloadCallback<T> callback) throws Exception;

    /**
     * 上传文件
     *
     * @param inputStream 文件流
     * @param fileSize    文件大小
     * @param fileExtName 文件后缀名
     * @param metaDataSet 元信息
     * @return {@link StorePath}
     * @throws Exception 异常
     * @author luyuhao
     * @since 2021/12/7
     */
    StorePath uploadFile(InputStream inputStream, long fileSize, String fileExtName, Set<MetaData> metaDataSet) throws Exception;

    /**
     * 删除文件
     *
     * @param groupName 组名
     * @param path      路径
     * @throws Exception 异常
     * @author luyuhao
     * @since 2021/12/7
     */
    void deleteFile(String groupName, String path) throws Exception;
}
