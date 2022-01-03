package com.darren1112.dptms.file.dao;

import com.darren1112.dptms.common.spi.file.dto.FileCenterDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 文件中心Dao
 *
 * @author luyuhao
 * @since 2021/12/18
 */
@Mapper
@Repository
public interface FileCenterDao {

    /**
     * 根据父节点id查询
     *
     * @param parentId 父节点id
     * @return {@link FileCenterDto}
     * @author luyuhao
     * @since 2021/12/18
     */
    List<FileCenterDto> list(@Param("parentId") Long parentId);

    /**
     * 新增文件/文件夹
     *
     * @param dto 文件信息
     * @author luyuhao
     * @since 2022/01/03
     */
    void insert(FileCenterDto dto);
}
