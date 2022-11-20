package com.darren1112.dptms.file.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.darren1112.dptms.common.spi.file.dto.FileDfsInfoDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 文件存储信息Dao
 *
 * @author luyuhao
 * @since 2021/12/1
 */
@Mapper
@Repository
public interface FileDfsInfoDao extends BaseMapper<FileDfsInfoDto> {

    /**
     * 批量新增
     *
     * @param list 文件存储信息
     * @author luyuhao
     * @since 2021/12/7
     */
    void batchInsert(List<FileDfsInfoDto> list);

    /**
     * 根据文件信息id查询文件存储信息
     *
     * @param fileId 文件id
     * @return {@link FileDfsInfoDto}
     * @author luyuhao
     * @since 2021/12/11
     */
    List<FileDfsInfoDto> listByFileInfoId(@Param("fileId") Long fileId);
}
