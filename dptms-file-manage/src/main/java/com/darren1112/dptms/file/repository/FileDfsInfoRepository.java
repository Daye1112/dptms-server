package com.darren1112.dptms.file.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.darren1112.dptms.common.spi.file.dto.FileDfsInfoDto;
import com.darren1112.dptms.file.dao.FileDfsInfoDao;
import org.springframework.stereotype.Repository;

/**
 * 文件存储信息Repository
 *
 * @author darren
 * @since 2022/11/20
 */
@Repository
public class FileDfsInfoRepository extends ServiceImpl<FileDfsInfoDao, FileDfsInfoDto> {
}
