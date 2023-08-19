package com.darren1112.dptms.file.service.impl;

import com.darren1112.dptms.file.service.FileDfsInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 文件存储信息ServiceImpl
 *
 * @author darren
 * @since 2021/12/1
 */
@Service
@Transactional(rollbackFor = Throwable.class, readOnly = true)
public class FileDfsInfoServiceImpl implements FileDfsInfoService {
}
