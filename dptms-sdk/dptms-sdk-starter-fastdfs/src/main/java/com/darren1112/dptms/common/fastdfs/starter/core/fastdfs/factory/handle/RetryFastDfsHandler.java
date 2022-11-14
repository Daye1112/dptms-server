package com.darren1112.dptms.common.fastdfs.starter.core.fastdfs.factory.handle;

import com.darren1112.dptms.common.fastdfs.starter.properties.FastDfsProperties;
import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadCallback;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Set;

/**
 * 重试机制fastDfs处理器
 *
 * @author luyuhao
 * @since 2021/12/7
 */
public class RetryFastDfsHandler implements FastDfsHandler {

    private static final Logger log = LoggerFactory.getLogger(RetryFastDfsHandler.class);

    private FastFileStorageClient fastFileStorageClient;

    private FastDfsProperties fastDfsProperties;

    public RetryFastDfsHandler(FastFileStorageClient fastFileStorageClient,
                               FastDfsProperties fastDfsProperties) {
        this.fastFileStorageClient = fastFileStorageClient;
        this.fastDfsProperties = fastDfsProperties;
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
        int tryTimes = fastDfsProperties.getRetryTimes();
        Exception exception = null;
        for (int i = 0; i < tryTimes; i++) {
            try {
                return fastFileStorageClient.downloadFile(groupName, path, callback);
            } catch (Exception e) {
                log.warn("文件下载失败，失败原因 {}，开始第 {} 次重试", e.getMessage(), (i + 1));
                exception = e;
                Thread.sleep(100);
            }
        }
        if (exception != null) {
            throw exception;
        }
        return null;
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
        int tryTimes = fastDfsProperties.getRetryTimes();
        Exception exception = null;
        for (int i = 0; i < tryTimes; i++) {
            try {
                return fastFileStorageClient.uploadFile(inputStream, fileSize, fileExtName, metaDataSet);
            } catch (Exception e) {
                log.warn("文件上传失败，失败原因 {}，开始第 {} 次重试", e.getMessage(), (i + 1));
                exception = e;
                Thread.sleep(100);
            }
        }
        if (exception != null) {
            throw exception;
        }
        return null;
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
        int tryTimes = fastDfsProperties.getRetryTimes();
        Exception exception = null;
        for (int i = 0; i < tryTimes; i++) {
            try {
                fastFileStorageClient.deleteFile(groupName, path);
                break;
            } catch (Exception e) {
                log.warn("文件删除失败，失败原因 {}，开始第 {} 次重试", e.getMessage(), (i + 1));
                exception = e;
                Thread.sleep(100);
            }
        }
        if (exception != null) {
            throw exception;
        }
    }
}
