package com.darren1112.dptms.file.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.darren1112.dptms.common.spi.file.dto.FileCenterDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 文件中心Dao
 *
 * @author darren
 * @since 2021/12/18
 */
@Mapper
@Repository
public interface FileCenterDao extends BaseMapper<FileCenterDto> {

    /**
     * 根据父节点id查询
     *
     * @param param 查询条件
     * @return {@link FileCenterDto}
     * @author darren
     * @since 2021/12/18
     */
    List<FileCenterDto> list(@Param("param") FileCenterDto param);

}
