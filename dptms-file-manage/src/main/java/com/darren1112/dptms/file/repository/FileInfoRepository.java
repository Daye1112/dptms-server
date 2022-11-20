package com.darren1112.dptms.file.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.darren1112.dptms.common.spi.file.dto.FileInfoDto;
import com.darren1112.dptms.file.dao.FileInfoDao;
import org.springframework.stereotype.Repository;

/**
 * 文件信息Repository
 *
 * @author luyuhao
 * @since 2022/11/20
 */
@Repository
public class FileInfoRepository extends ServiceImpl<FileInfoDao, FileInfoDto> {
}
