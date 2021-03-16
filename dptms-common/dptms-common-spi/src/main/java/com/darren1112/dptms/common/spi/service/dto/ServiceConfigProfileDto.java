package com.darren1112.dptms.common.spi.service.dto;

import com.darren1112.dptms.common.spi.service.entity.ServiceConfigProfileEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 配置环境表Dto
 *
 * @author luyuhao
 * @since 2021/03/12 01:37
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ServiceConfigProfileDto extends ServiceConfigProfileEntity {

    /**
     * 是否更新 true/false
     */
    private Boolean isUpdate;
}
