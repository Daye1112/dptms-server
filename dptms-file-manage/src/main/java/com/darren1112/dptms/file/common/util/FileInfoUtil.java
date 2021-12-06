package com.darren1112.dptms.file.common.util;

import com.darren1112.dptms.common.core.enums.FileTypeEnum;
import com.darren1112.dptms.common.core.util.FileUtil;
import com.darren1112.dptms.common.spi.file.dto.FileInfoDto;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件信息相关工具类
 *
 * @author luyuhao
 * @since 2021/12/6
 */
public class FileInfoUtil {

    /**
     * 创建文件基本信息
     *
     * @param file   文件对象
     * @param userId 用户id
     * @return {@link FileInfoDto}
     * @author luyuhao
     * @since 2021/12/6
     */
    public static FileInfoDto create(MultipartFile file, Long userId) {
        // 创建dto
        FileInfoDto dto = new FileInfoDto();
        // 设置用户id
        dto.setCreater(userId);
        dto.setUpdater(userId);
        // 获取文件信息
        String fileName = file.getOriginalFilename();
        String extName = FileUtil.extName(fileName);
        int fileType = FileTypeEnum.matchTypeByExtName(extName);
        long fileSize = file.getSize();
        // 设置文件信息
        dto.setFileType(fileType);
        dto.setFileName(fileName);
        dto.setFileSize(fileSize);
        dto.setFileExt(extName);
        return dto;
    }
}
