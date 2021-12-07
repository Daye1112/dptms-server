package com.darren1112.dptms.common.fastdfs.starter.core.file.factory.handle;

import com.darren1112.dptms.common.core.util.FileUtil;
import com.darren1112.dptms.common.fastdfs.starter.properties.FastDfsProperties;
import com.darren1112.dptms.common.spi.file.dto.FileDfsInfoDto;
import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 大文件处理器
 *
 * @author luyuhao
 * @since 2021/12/2
 */
public class LargeFileHandler implements FileHandler {

    private FastFileStorageClient fastFileStorageClient;

    private FastDfsProperties fastDfsProperties;

    private ThreadPoolTaskExecutor fileHandleThreadPool;

    public LargeFileHandler(FastFileStorageClient fastFileStorageClient, FastDfsProperties fastDfsProperties, ThreadPoolTaskExecutor fileHandleThreadPool) {
        this.fastFileStorageClient = fastFileStorageClient;
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
        List<FileDfsInfoDto> resultList = new ArrayList<>();
        // 获取文件大小
        int fileSize = fileStream.available();
        int splitSize = this.fastDfsProperties.getSplitSize();
        String extName = FileUtil.extName(fileName);

        // 计算参数
        int count = (fileSize + splitSize - 1) / splitSize;
        int byteSize = splitSize > 1024 ? 1024 : splitSize;
        Exception exception = null;

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
                // TODO 异步上传
                // 文件上传
                StorePath storePath = fastFileStorageClient.uploadFile(subFileStream, fileSize, extName, metaDataSet);
                // 添加到结果集
                resultList.add(FileDfsInfoDto.create(storePath.getGroup(), storePath.getPath(), (long) fileSize, (i + 1)));
            } catch (Exception e) {
                exception = e;
            }
            // 如果一个上传失败，直接结束
            if (exception != null) {
                break;
            }
        }

        // 如果存在失败，则删除已上传子文件
        if (exception != null) {
            for (FileDfsInfoDto fileDfsInfoDto : resultList) {
                fastFileStorageClient.deleteFile(fileDfsInfoDto.getFileGroup(), fileDfsInfoDto.getFilePath());
            }
            throw exception;
        }
        return resultList;
    }

}
