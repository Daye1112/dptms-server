package com.darren1112.dptms.common.fastdfs.starter.service;

import com.github.tobato.fastdfs.domain.fdfs.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 文件Service
 *
 * @author luyuhao
 * @date 2021/11/28 21:43
 */
public interface FileService {

    /**
     * 上传文件
     *
     * @param file 文件对象
     * @return {@link String 文件访问地址}
     * @throws IOException io异常
     * @author luyuhao
     * @since 2021/11/28
     */
    String uploadFile(MultipartFile file) throws IOException;

    /**
     * 将一段字符串生成一个文件上传
     *
     * @param content       文件内容
     * @param fileExtension 文件后缀名
     * @return {@link String}
     * @author luyuhao
     * @since 2021/11/28
     */
    String uploadFile(String content, String fileExtension);

    /**
     * 下载文件
     *
     * @param fileUrl 文件url
     * @return {@link Byte}
     * @author luyuhao
     * @since 2021/11/28
     */
    byte[] download(String fileUrl);

    /**
     * 删除文件
     *
     * @param fileUrl 文件访问地址
     * @return {@link Boolean}
     * @author luyuhao
     * @since 2021/11/28
     */
    boolean deleteFile(String fileUrl);

    /**
     * 判断是否存在文件
     *
     * @param fileUrl 文件url
     * @return {@link Boolean}
     * @author luyuhao
     * @since 2021/11/28
     */
    boolean hasFile(String fileUrl);

    /**
     * 获取文件信息
     *
     * @param fileUrl 文件url
     * @return {@link FileInfo}
     * @author luyuhao
     * @since 2021/11/28
     */
    FileInfo getFileInfo(String fileUrl);

    /**
     * 获取文件大小
     *
     * @param fileUrl 文件url
     * @return {@link Long 文件大小}
     * @author luyuhao
     * @since 2021/11/28
     */
    Long getFileSize(String fileUrl);

    /**
     * 获取文件大小集合
     *
     * @param fileUrlList 文件url集合
     * @return {@link Long 文件大小集合}
     * @author luyuhao
     * @since 2021/11/28
     */
    List<Long> getFileSizeList(List<String> fileUrlList);

    /**
     * 获取文件大小集合
     *
     * @param fileUrlArray 文件url数组
     * @return {@link String 文件大小集合}
     * @author luyuhao
     * @since 2021/11/28
     */
    List<Long> getFileSizeList(String[] fileUrlArray);
}
