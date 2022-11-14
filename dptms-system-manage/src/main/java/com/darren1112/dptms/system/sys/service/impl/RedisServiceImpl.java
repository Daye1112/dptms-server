package com.darren1112.dptms.system.sys.service.impl;

import com.darren1112.dptms.common.core.base.BaseService;
import com.darren1112.dptms.sdk.starter.redis.core.RedisUtil;
import com.darren1112.dptms.common.spi.common.dto.PageBean;
import com.darren1112.dptms.common.spi.common.dto.PageParam;
import com.darren1112.dptms.common.spi.sys.dto.SysRedisDto;
import com.darren1112.dptms.system.sys.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * redis service impl
 *
 * @author luyuhao
 * @since 2021/01/31 00:05
 */
@Service
public class RedisServiceImpl extends BaseService implements RedisService {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 根据key的前缀分页查缓存数据
     *
     * @param keyPrefix key前缀
     * @param pageParam 分页参数
     * @return {@link SysRedisDto}
     * @author luyuhao
     * @since 2021/01/31 00:07
     */
    @Override
    public PageBean<SysRedisDto> listPage(String keyPrefix, PageParam pageParam) {
        // 查询keys，并进行排序分页
        Set<String> keySet = redisUtil.getKeysWithKeyPrefix("", keyPrefix);
        List<String> keyList = new ArrayList<>(keySet);
        Long count = (long) keyList.size();
        keyList.sort(String::compareTo);
        int endIndex = pageParam.getStartIndex() + pageParam.getPageSize();
        endIndex = endIndex > keyList.size() ? keyList.size() : endIndex;
        keyList = keyList.subList(pageParam.getStartIndex(), endIndex);

        // 查询value和有效期
        List<SysRedisDto> list = new ArrayList<>();
        for (String key : keyList) {
            String value = redisUtil.getWithPrefix("", key);
            long expire = redisUtil.ttlWithPrefix("", key);
            SysRedisDto subSysRedisDto = new SysRedisDto();
            subSysRedisDto.setKey(key);
            subSysRedisDto.setValue(value);
            subSysRedisDto.setExpired((int) expire);
            list.add(subSysRedisDto);
        }
        return createPageBean(pageParam, count, list);
    }

    /**
     * 新增缓存
     *
     * @param sysRedisDto 缓存信息
     * @author luyuhao
     * @since 2021/01/31 01:01
     */
    @Override
    public void insert(SysRedisDto sysRedisDto) {
        redisUtil.setWithPrefix("", sysRedisDto.getKey(), sysRedisDto.getValue(), sysRedisDto.getExpired());
    }

    /**
     * 删除缓存
     *
     * @param sysRedisDto 缓存信息
     * @author luyuhao
     * @since 2021/01/31 00:04
     */
    @Override
    public void deleteByKey(SysRedisDto sysRedisDto) {
        redisUtil.removeKeyWithPrefix("", sysRedisDto.getKey());
    }
}
