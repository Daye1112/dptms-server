package com.darren1112.dptms.common.spi.sys.dto;

import com.darren1112.dptms.common.spi.sys.entity.SysMenuEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
}
