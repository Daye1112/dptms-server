package com.darren1112.dptms.common.spi.file.dto;

import com.darren1112.dptms.common.spi.file.entity.FileInfoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
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

    /**
     * 添加单个文件存储信息
     *
     * @param fileDfsInfoDto 文件存储信息
     * @author luyuhao
     * @since 2021/12/3
     */
    public void addFileDfsInfoDto(FileDfsInfoDto fileDfsInfoDto) {
        if (this.fileDfsInfoList == null) {
            this.fileDfsInfoList = new ArrayList<>();
        }
        this.fileDfsInfoList.add(fileDfsInfoDto);
    }
}
