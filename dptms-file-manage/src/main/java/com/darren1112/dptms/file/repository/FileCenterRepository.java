package com.darren1112.dptms.file.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.darren1112.dptms.common.spi.file.dto.FileCenterDto;
import com.darren1112.dptms.file.dao.FileCenterDao;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 文件中心Repository
 *
 * @author darren
 * @since 2022/11/20
 */
@Repository
public class FileCenterRepository extends ServiceImpl<FileCenterDao, FileCenterDto> {

    /**
     * 更新文件/文件夹
     *
     * @param dto 文件/文件夹信息
     * @author darren
     * @since 2023/08/12
     */
    public void updateInfo(FileCenterDto dto) {
        this.lambdaUpdate()
                .set(FileCenterDto::getFileName, dto.getFileName())
                .set(FileCenterDto::getFileDesc, dto.getFileDesc())
                .set(FileCenterDto::getUpdater, dto.getUpdater())
                .set(FileCenterDto::getMtime, new Date())
                .eq(FileCenterDto::getId, dto.getId())
                .update();
    }

    /**
     * 删除文件/文件夹
     *
     * @param dto 文件/文件夹信息
     * @author darren
     * @since 2023/08/12
     */
    public void deleteById(FileCenterDto dto) {
        this.lambdaUpdate()
                .set(FileCenterDto::getIsvalid, 0)
                .set(FileCenterDto::getUpdater, dto.getUpdater())
                .set(FileCenterDto::getMtime, new Date())
                .eq(FileCenterDto::getId, dto.getId())
                .update();
    }

    /**
     * 查询该文件夹下的所有文件
     *
     * @param fileParentPath 父节点path
     * @return {@link FileCenterDto}
     * @author darren
     * @since 2023/08/19
     */
    public List<FileCenterDto> listSubFileList(String fileParentPath) {
        return this.lambdaQuery()
                .likeRight(FileCenterDto::getFileParentPath, fileParentPath)
                .list();
    }

    /**
     * 批量删除文件
     *
     * @param fileIdList 文件id集合
     * @param updater    更新者
     * @author darren
     * @since 2023/08/19
     */
    public void deleteByIds(List<Long> fileIdList, Long updater) {
        this.lambdaUpdate()
                .set(FileCenterDto::getIsvalid, 0)
                .set(FileCenterDto::getUpdater, updater)
                .set(FileCenterDto::getMtime, new Date())
                .in(FileCenterDto::getId, fileIdList)
                .update();
    }
}
