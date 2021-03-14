package com.darren1112.dptms.common.spi.service.dto;

import com.darren1112.dptms.common.spi.service.entity.ServiceConfigProfilePropEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 配置环境属性表Dto
 *
 * @author luyuhao
 * @date 2021/03/12 01:39
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ServiceConfigProfilePropDto extends ServiceConfigProfilePropEntity {

    /**
     * 是否更新 true/false
     */
    private Boolean isUpdate;
}
