package com.darren1112.dptms.common.spi.sys.dto;

import com.darren1112.dptms.common.spi.sys.entity.SysMenuEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * 菜单Dto
 *
 * @author luyuhao
 * @since 2020/12/12 17:23
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SysMenuDto extends SysMenuEntity {

    /**
     * 是否更新
     */
    @ApiModelProperty("是否更新")
    private Boolean isUpdate;

    /**
     * 是否已分配
     */
    @ApiModelProperty("是否已分配")
    private Boolean isAssigned;

    /**
     * 子节点
     */
    @ApiModelProperty("子节点")
    private List<SysMenuDto> children;

    /**
     * 已分配的ids
     */
    private List<Long> assignedIdList;
}
