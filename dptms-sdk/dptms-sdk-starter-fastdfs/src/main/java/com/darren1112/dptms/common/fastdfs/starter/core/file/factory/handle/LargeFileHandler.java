package com.darren1112.dptms.common.fastdfs.starter.core.file.factory.handle;

import com.darren1112.dptms.common.core.util.FileUtil;
import com.darren1112.dptms.common.fastdfs.starter.core.fastdfs.factory.FastDfsHandlerFactory;
import com.darren1112.dptms.common.fastdfs.starter.properties.FastDfsProperties;
import com.darren1112.dptms.common.spi.file.dto.FileDfsInfoDto;
import com.darren1112.dptms.common.spi.file.entity.FileDfsInfoEntity;
import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;

/**
 * 大文件处理器
 *
 * @author luyuhao
 * @since 2021/12/2
 */
public class LargeFileHandler extends AbstractFileHandler {

    private static final Logger log = LoggerFactory.getLogger(LargeFileHandler.class);

    private FastDfsProperties fastDfsProperties;

    private ThreadPoolTaskExecutor fileHandleThreadPool;

    public LargeFileHandler(FastDfsHandlerFactory fastDfsHandlerFactory,
                            FastDfsProperties fastDfsProperties,
                            ThreadPoolTaskExecutor fileHandleThreadPool) {
        super(fastDfsHandlerFactory);
        this.fastDfsProperties = fastDfsProperties;
        this.fileHandleThreadPool = fileHandleThreadPool;
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
        // 获取文件大小
        int fileSize = fileStream.available();
        int splitSize = this.fastDfsProperties.getSplitSize();

        // 计算参数
        int count = (fileSize + splitSize - 1) / splitSize;
        int byteSize = splitSize > 1024 ? 1024 : splitSize;
        Exception exception = null;
        List<Future<FileDfsInfoDto>> futureList = new ArrayList<>();
        // 循环遍历
        for (int i = 0; i < count; i++) {
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                // 初始化分割参数
                byte[] bytes = new byte[byteSize];
                int splitCount = 0;
                int len;
                while (splitCount < splitSize && (len = fileStream.read(bytes)) != -1) {
                    baos.write(bytes, 0, len);
                    baos.flush();
                    splitCount += len;
                }
                // 封装切割结果
                InputStream subFileStream = new ByteArrayInputStream(baos.toByteArray());
                // 异步上传
                int order = i + 1;
                Future<FileDfsInfoDto> future = fileHandleThreadPool.submit(() -> super.simpleUpload(subFileStream, fileName, metaDataSet, order));
                futureList.add(future);
            } catch (Exception e) {
                // 上传异常，直接结束
                log.error("文件上传失败, 失败原因: " + e.getMessage(), e);
                exception = e;
                break;
            }
        }
        List<FileDfsInfoDto> resultList = new ArrayList<>();
        for (Future<FileDfsInfoDto> subFuture : futureList) {
            try {
                resultList.add(subFuture.get());
            } catch (Exception e) {
                log.error("文件上传失败, 失败原因: " + e.getMessage(), e);
                exception = e;
            }
        }
        // 如果存在失败，则删除已上传子文件
        if (exception != null) {
            for (FileDfsInfoDto fileDfsInfoDto : resultList) {
                fileHandleThreadPool.execute(() -> {
                    try {
                        fastDfsHandlerFactory.create()
                                .deleteFile(fileDfsInfoDto.getFileGroup(), fileDfsInfoDto.getFilePath());
                    } catch (Exception e) {
                        log.error("文件删除失败, groupName: {}, path: {}", fileDfsInfoDto.getFileGroup(), fileDfsInfoDto.getFilePath());
                        log.error(e.getMessage(), e);
                    }
                });
            }
            throw exception;
        }
        return resultList;
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
        // 文件异步下载
        List<Future<FileDfsInfoDto>> futureList = new ArrayList<>();
        for (FileDfsInfoDto subFileDfsInfo : fileDfsInfoList) {
            Future<FileDfsInfoDto> subFileDfsInfoFuture = fileHandleThreadPool.submit(() -> super.simpleDownload(subFileDfsInfo));
            futureList.add(subFileDfsInfoFuture);
        }
        // 获取文件下载结果
        List<FileDfsInfoDto> downLoadList = new ArrayList<>();
        for (Future<FileDfsInfoDto> subFileDfsInfoFuture : futureList) {
            downLoadList.add(subFileDfsInfoFuture.get());
        }
        // 文件下载结果组装
        byte[] result = null;
        downLoadList.sort(Comparator.comparing(FileDfsInfoEntity::getFileOrder));
        for (FileDfsInfoDto subFileDfsInfo : downLoadList) {
            result = FileUtil.mergeBytes(result, subFileDfsInfo.getFileBytes());
        }
        return result;
    }
}
