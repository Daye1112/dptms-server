package com.darren1112.dptms.common.fastdfs.starter.service.impl;

import com.darren1112.dptms.common.core.util.CollectionUtil;
import com.darren1112.dptms.common.core.util.StringUtil;
import com.darren1112.dptms.common.fastdfs.starter.properties.FastDfsProperties;
import com.darren1112.dptms.common.fastdfs.starter.service.FileService;
import com.github.tobato.fastdfs.domain.fdfs.FileInfo;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 文件ServiceImpl
 *
 * @author luyuhao
 * @since 2021/11/28
 */
public class FileServiceImpl implements FileService {

    private FastFileStorageClient fastFileStorageClient;

    private FastDfsProperties fastDfsProperties;

    public FileServiceImpl(FastFileStorageClient fastFileStorageClient,
                           FastDfsProperties fastDfsProperties) {
        this.fastFileStorageClient = fastFileStorageClient;
        this.fastDfsProperties = fastDfsProperties;
    }

    /**
     * 上传文件
     *
     * @param file 文件对象
     * @return {@link String 文件访问地址}
     * @author luyuhao
     * @since 2021/11/28
     */
    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
        return getResAccessUrl(storePath);
    }

    /**
     * 将一段字符串生成一个文件上传
     *
     * @param content       文件内容
     * @param fileExtension 文件后缀名
     * @return {@link String}
     * @author luyuhao
     * @since 2021/11/28
     */
    @Override
    public String uploadFile(String content, String fileExtension) {
        byte[] buff = content.getBytes(Charset.forName("UTF-8"));
        ByteArrayInputStream stream = new ByteArrayInputStream(buff);
        StorePath storePath = fastFileStorageClient.uploadFile(stream, buff.length, fileExtension, null);
        return getResAccessUrl(storePath);
    }

    /**
     * 下载文件
     *
     * @param fileUrl 文件url
     * @return {@link Byte}
     * @author luyuhao
     * @since 2021/11/28
     */
    @Override
    public byte[] download(String fileUrl) {
        if (StringUtil.isEmpty(fileUrl)) {
            return null;
        }
        try {
            StorePath storePath = StorePath.parseFromUrl(fileUrl);
            return fastFileStorageClient.downloadFile(storePath.getGroup(), storePath.getPath(), new DownloadByteArray());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 删除文件
     *
     * @param fileUrl 文件访问地址
     * @return {@link Boolean}
     * @author luyuhao
     * @since 2021/11/28
     */
    @Override
    public boolean deleteFile(String fileUrl) {
        if (StringUtil.isEmpty(fileUrl)) {
            return true;
        }
        try {
            StorePath storePath = StorePath.parseFromUrl(fileUrl);
            fastFileStorageClient.deleteFile(storePath.getGroup(), storePath.getPath());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断是否存在文件
     *
     * @param fileUrl 文件url
     * @return {@link Boolean}
     * @author luyuhao
     * @since 2021/11/28
     */
    @Override
    public boolean hasFile(String fileUrl) {
        if (StringUtil.isEmpty(fileUrl)) {
            return false;
        }
        try {
            StorePath storePath = StorePath.parseFromUrl(fileUrl);
            FileInfo fileInfo = fastFileStorageClient.queryFileInfo(storePath.getGroup(), storePath.getPath());
            return fileInfo != null;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取文件信息
     *
     * @param fileUrl 文件url
     * @return {@link FileInfo}
     * @author luyuhao
     * @since 2021/11/28
     */
    @Override
    public FileInfo getFileInfo(String fileUrl) {
        if (StringUtil.isEmpty(fileUrl)) {
            return null;
        }
        try {
            StorePath storePath = StorePath.parseFromUrl(fileUrl);
            FileInfo fileInfo = fastFileStorageClient.queryFileInfo(storePath.getGroup(), storePath.getPath());
            return Optional.ofNullable(fileInfo)
                    .orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取文件大小
     *
     * @param fileUrl 文件url
     * @return {@link Long 文件大小}
     * @author luyuhao
     * @since 2021/11/28
     */
    @Override
    public Long getFileSize(String fileUrl) {
        return Optional.ofNullable(getFileInfo(fileUrl))
                .map(FileInfo::getFileSize)
                .orElse(0L);
    }

    /**
     * 获取文件大小集合
     *
     * @param fileUrlList 文件url集合
     * @return {@link Long 文件大小集合}
     * @author luyuhao
     * @since 2021/11/28
     */
    @Override
    public List<Long> getFileSizeList(List<String> fileUrlList) {
        List<Long> fileSizeList = new ArrayList<>();
        if (!CollectionUtil.isEmpty(fileUrlList)) {
            for (String url : fileUrlList) {
                fileSizeList.add(getFileSize(url));
            }
        }
        return fileSizeList;
    }

    /**
     * 获取文件大小集合
     *
     * @param fileUrlArray 文件url数组
     * @return {@link String 文件大小集合}
     * @author luyuhao
     * @since 2021/11/28
     */
    @Override
    public List<Long> getFileSizeList(String[] fileUrlArray) {
        List<Long> fileSizeList = new ArrayList<>();
        if (fileUrlArray != null && fileUrlArray.length > 0) {
            for (String url : fileUrlArray) {
                fileSizeList.add(getFileSize(url));
            }
        }
        return fileSizeList;
    }

    /**
     * 封装图片完整URL地址
     *
     * @param storePath store信息
     * @author luyuhao
     * @since 2021/11/28
     */
    private String getResAccessUrl(StorePath storePath) {
        return fastDfsProperties.getRequestUrl() + storePath.getFullPath();
    }
}
