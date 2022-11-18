package com.darren1112.dptms.auth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.auth.dto.AuthOrganizationDto;
import com.darren1112.dptms.common.spi.auth.entity.AuthOrganizationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 组织Dao
 *
 * @author luyuhao
 * @since 20/08/16 01:35
 */
@Mapper
@Repository
public interface AuthOrganizationDao extends BaseMapper<AuthOrganizationDto> {

    /**
     * 查询重复记录数
     *
     * @param param 查询参数
     * @return 记录数
     * @author luyuhao
     * @since 20/12/12 22:08
     */
    Long countByRepeat(AuthOrganizationDto param);

    // /**
    //  * 插入组织信息
    //  *
    //  * @param entity 组织参数
    //  * @return {@link Long}
    //  * @author luyuhao
    //  * @since 20/12/10 01:08
    //  */
    // Long insert(AuthOrganizationEntity entity);

    /**
     * 更新组织信息
     *
     * @param entity 组织参数
     * @return {@link Long}
     * @author luyuhao
     * @since 20/12/10 01:08
     */
    Long update(AuthOrganizationEntity entity);

    /**
     * 根据id删除记录
     *
     * @param id      记录id
     * @param updater 更新者
     * @author luyuhao
     * @since 20/12/10 01:08
     */
    void deleteById(@Param("id") Long id, @Param("updater") Long updater);

    /**
     * 分页查询组织记录
     *
     * @param pageParam 分页参数
     * @param param     筛选条件
     * @return {@link AuthOrganizationDto}
     * @author luyuhao
     * @since 20/12/12 22:47
     */
    List<AuthOrganizationDto> listPage(@Param("pageParam") PageParam pageParam, @Param("param") AuthOrganizationDto param);

    /**
     * 分页查询组织记录-总记录数
     *
     * @param param 筛选条件
     * @return {@link Long}
     * @author luyuhao
     * @since 20/12/12 22:47
     */
    Long listPageCount(AuthOrganizationDto param);
}
