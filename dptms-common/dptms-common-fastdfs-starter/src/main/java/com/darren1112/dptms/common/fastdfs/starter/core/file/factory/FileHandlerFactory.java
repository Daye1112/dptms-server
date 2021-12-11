package com.darren1112.dptms.common.fastdfs.starter.core.file.factory;

import com.darren1112.dptms.common.fastdfs.starter.core.file.factory.handle.FileHandler;
import com.darren1112.dptms.common.fastdfs.starter.properties.FastDfsProperties;

/**
 * 文件处理工厂类
 *
 * @author luyuhao
 * @since 2021/12/2
 */
public class FileHandlerFactory {

    private FileHandler largeFileHandler;

    private FileHandler smallFileHandler;

    private FastDfsProperties fastDfsProperties;

    public FileHandlerFactory(FileHandler largeFileHandler, FileHandler smallFileHandler, FastDfsProperties fastDfsProperties) {
        this.largeFileHandler = largeFileHandler;
        this.smallFileHandler = smallFileHandler;
        this.fastDfsProperties = fastDfsProperties;
    }

    /**
     * 文件上传-根据文件大小返回处理器
     *
     * @param fileSize 文件大小
     * @return {@link FileHandler}
     * @author luyuhao
     * @since 2021/12/4
     */
    public FileHandler uploadCreate(int fileSize) {
        if (fastDfsProperties.getSplitSize() <= 0
                || fileSize <= fastDfsProperties.getSplitSize()) {
            // 未配置分割参数或文件小于分割大小
            return smallFileHandler;
        } else {
            // 配置分割参数且文件大小大于分割大小
            return largeFileHandler;
        }
    }

    /**
     * 文件下载-根据文件存储信息集合大小返回处理器
     *
     * @return {@link FileHandler}
     * @author luyuhao
     * @since 2021/12/4
     */
    public FileHandler downloadCreate(int fileDfsSize) {
        if (fileDfsSize == 1) {
            return smallFileHandler;
        } else if (fileDfsSize > 1) {
            return largeFileHandler;
        } else {
            throw new RuntimeException("文件下载-存储大小小于1");
        }
    }
}
