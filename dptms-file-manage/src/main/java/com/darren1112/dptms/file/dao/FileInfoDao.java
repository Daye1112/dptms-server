package com.darren1112.dptms.file.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.darren1112.dptms.common.spi.file.dto.FileInfoDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 文件信息Dao
 *
 * @author luyuhao
 * @since 2021/12/1
 */
@Mapper
@Repository
public interface FileInfoDao extends BaseMapper<FileInfoDto> {

    /**
     * 根据文件id查询文件信息
     *
     * @param fileId 文件id
     * @return {@link FileInfoDto}
     * @author luyuhao
     * @since 2021/12/10
     */
    FileInfoDto getById(@Param("fileId") Long fileId);
}
