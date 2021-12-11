package com.darren1112.dptms.common.spi.file.dto;

import com.darren1112.dptms.common.spi.file.entity.FileDfsInfoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 文件存储信息Dto
 *
 * @author luyuhao
 * @since 2021/12/1
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class FileDfsInfoDto extends FileDfsInfoEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 文件字节流
     */
    private byte[] fileBytes;

    /**
     * 创建对象
     *
     * @param fileGroup 存储组
     * @param filePath  存储路径
     * @param fileSize  存储大小
     * @return {@link FileDfsInfoDto}
     * @author luyuhao
     * @since 2021/12/4
     */
    public static FileDfsInfoDto create(String fileGroup, String filePath, Long fileSize) {
        return create(fileGroup, filePath, fileSize, 1);
    }

    /**
     * 创建对象
     *
     * @param fileGroup 存储组
     * @param filePath  存储路径
     * @param fileSize  存储大小
     * @return {@link FileDfsInfoDto}
     * @author luyuhao
     * @since 2021/12/4
     */
    public static FileDfsInfoDto create(String fileGroup, String filePath, Long fileSize, Integer fileOrder) {
        FileDfsInfoDto result = new FileDfsInfoDto();
        result.setFileGroup(fileGroup);
        result.setFilePath(filePath);
        result.setFileSize(fileSize);
        result.setFileOrder(fileOrder);
        return result;
    }
}
