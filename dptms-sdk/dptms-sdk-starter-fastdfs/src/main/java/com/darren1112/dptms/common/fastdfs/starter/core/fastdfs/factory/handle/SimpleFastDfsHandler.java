package com.darren1112.dptms.common.fastdfs.starter.core.fastdfs.factory.handle;

import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadCallback;
import com.github.tobato.fastdfs.service.FastFileStorageClient;

import java.io.InputStream;
import java.util.Set;

/**
 * 简单fastDfs处理器
 *
 * @author luyuhao
 * @since 2021/12/7
 */
public class SimpleFastDfsHandler implements FastDfsHandler {

    private FastFileStorageClient fastFileStorageClient;

    public SimpleFastDfsHandler(FastFileStorageClient fastFileStorageClient) {
        this.fastFileStorageClient = fastFileStorageClient;
    }

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
    @Override
    public <T> T downloadFile(String groupName, String path, DownloadCallback<T> callback) throws Exception {
        return fastFileStorageClient.downloadFile(groupName, path, callback);
    }

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
    @Override
    public StorePath uploadFile(InputStream inputStream, long fileSize, String fileExtName, Set<MetaData> metaDataSet) throws Exception {
        return fastFileStorageClient.uploadFile(inputStream, fileSize, fileExtName, metaDataSet);
    }

    /**
     * 删除文件
     *
     * @param groupName 组名
     * @param path      路径
     * @throws Exception 异常
     * @author luyuhao
     * @since 2021/12/7
     */
    @Override
    public void deleteFile(String groupName, String path) throws Exception {
        fastFileStorageClient.deleteFile(groupName, path);
    }
}
