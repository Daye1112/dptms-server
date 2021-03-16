package com.darren1112.dptms.common.spi.service.dto;

import com.darren1112.dptms.common.spi.service.entity.ServiceApplicationEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 应用表Dto
 *
 * @author luyuhao
 * @since 2021/03/12 01:27
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ServiceApplicationDto extends ServiceApplicationEntity {

    /**
     * 是否更新 true/false
     */
    private Boolean isUpdate;
}
