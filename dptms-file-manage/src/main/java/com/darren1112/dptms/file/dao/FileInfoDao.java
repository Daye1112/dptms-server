package com.darren1112.dptms.file.dao;

import com.darren1112.dptms.common.spi.file.dto.FileInfoDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 文件信息Dao
 *
 * @author luyuhao
 * @since 2021/12/1
 */
@Mapper
@Repository
public interface FileInfoDao {

    /**
     * 新增文件信息
     *
     * @param fileInfoDto 文件信息
     * @author luyuhao
     * @since 2021/12/6
     */
    void insert(FileInfoDto fileInfoDto);
}
