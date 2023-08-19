package com.darren1112.dptms.common.spi.service.dto;

import com.darren1112.dptms.common.spi.service.entity.ServiceConfigReleaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 配置发布表Dto
 *
 * @author darren
 * @since 2021/03/12 01:40
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ServiceConfigReleaseDto extends ServiceConfigReleaseEntity {

    /**
     * 是否更新 true/false
     */
    private Boolean isUpdate;
}
