package com.darren1112.dptms.sdk.component.service.impl;

import com.alibaba.druid.stat.DruidStatService;
import com.darren1112.dptms.common.core.util.JsonUtil;
import com.darren1112.dptms.common.spi.monitor.dto.DruidStatDto;
import com.darren1112.dptms.sdk.component.service.DruidService;

import java.util.List;
import java.util.Map;

/**
 * druid service impl
 *
 * @author darren
 * @since 2021/02/15 23:31
 */
public class DruidServiceImpl implements DruidService {

    private DruidStatService druidStatService = DruidStatService.getInstance();

    /**
     * 查询接口统计list
     *
     * @return {@link DruidStatDto}
     * @author darren
     * @since 2021/02/15 23:32
     */
    @Override
    public List<DruidStatDto> apiStatList() {
        String service = druidStatService.service("/weburi.json");
        Map res = JsonUtil.parseObject(service, Map.class);
        Object contentJson = res.get("Content");
        List<DruidStatDto> druidStatDtoList = JsonUtil.parseArray(contentJson.toString(), DruidStatDto.class);
        //过滤/actuator接口统计信息
        for (int i = 0; i < druidStatDtoList.size(); i++) {
            DruidStatDto druidStatDto = druidStatDtoList.get(i);
            if (druidStatDto.getUri().contains("/actuator")) {
                druidStatDtoList.remove(i--);
            }
        }
        return druidStatDtoList;
    }
}
