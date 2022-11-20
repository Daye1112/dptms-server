package com.darren1112.dptms.file.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.darren1112.dptms.common.spi.file.dto.FileCenterDto;
import com.darren1112.dptms.file.dao.FileCenterDao;
import org.springframework.stereotype.Repository;

/**
 * 文件中心Repository
 *
 * @author luyuhao
 * @since 2022/11/20
 */
@Repository
public class FileCenterRepository extends ServiceImpl<FileCenterDao, FileCenterDto> {
}
