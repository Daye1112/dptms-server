package com.darren1112.dptms.file.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.darren1112.dptms.common.spi.file.dto.FileDfsInfoDto;
import com.darren1112.dptms.file.dao.FileDfsInfoDao;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 文件存储信息Repository
 *
 * @author darren
 * @since 2022/11/20
 */
@Repository
public class FileDfsInfoRepository extends ServiceImpl<FileDfsInfoDao, FileDfsInfoDto> {

    /**
     * 批量删除存储信息
     *
     * @param idList  id集合
     * @param updater 更新者
     * @author darren
     * @since 2023/08/19
     */
    public void batchDeleteByIds(List<Long> idList, Long updater) {
        this.lambdaUpdate()
                .set(FileDfsInfoDto::getIsvalid, 0)
                .set(FileDfsInfoDto::getUpdater, updater)
                .set(FileDfsInfoDto::getMtime, new Date())
                .in(FileDfsInfoDto::getId, idList)
                .update();
    }
}
