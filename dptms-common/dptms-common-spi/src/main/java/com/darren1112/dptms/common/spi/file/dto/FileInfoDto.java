package com.darren1112.dptms.common.spi.file.dto;

import com.darren1112.dptms.common.spi.file.entity.FileInfoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * 文件信息Dto
 *
 * @author luyuhao
 * @since 2021/12/1
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class FileInfoDto extends FileInfoEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 文件存储集合
     */
    private List<FileDfsInfoDto> fileDfsInfoList;

}
