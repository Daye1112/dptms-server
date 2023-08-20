package com.darren1112.dptms.file.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.darren1112.dptms.common.spi.file.dto.FileInfoDto;
import com.darren1112.dptms.file.dao.FileInfoDao;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * 文件信息Repository
 *
 * @author darren
 * @since 2022/11/20
 */
@Repository
public class FileInfoRepository extends ServiceImpl<FileInfoDao, FileInfoDto> {

    /**
     * 更新文件名称
     *
     * @param fileInfoDto 文件信息
     * @author darren
     * @since 2023/08/16
     */
    public void updateFileName(FileInfoDto fileInfoDto) {
        this.lambdaUpdate()
                .set(FileInfoDto::getFileName, fileInfoDto.getFileName())
                .set(FileInfoDto::getUpdater, fileInfoDto.getUpdater())
                .set(FileInfoDto::getMtime, new Date())
                .eq(FileInfoDto::getId, fileInfoDto.getId())
                .update();
    }

    /**
     * 根据id删除数据
     *
     * @param id      数据id
     * @param updater 更新者
     * @author darren
     * @since 2023/08/19
     */
    public void deleteById(Long id, Long updater) {
        this.lambdaUpdate()
                .set(FileInfoDto::getIsvalid, 0)
                .set(FileInfoDto::getUpdater, updater)
                .set(FileInfoDto::getMtime, new Date())
                .eq(FileInfoDto::getId, id)
                .update();
    }
}
