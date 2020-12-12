package com.darren1112.dptms.common.spi.sys.dto;

import com.darren1112.dptms.common.spi.sys.entity.SysPermissionEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 权限Dto
 *
 * @author luyuhao
 * @date 2020/12/11 01:34
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SysPermissionDto extends SysPermissionEntity {

    /**
     * 是否更新 true/false
     */
    private Boolean isUpdate;
}
