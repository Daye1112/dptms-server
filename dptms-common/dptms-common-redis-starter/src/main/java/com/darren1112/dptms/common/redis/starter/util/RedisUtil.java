package com.darren1112.dptms.common.redis.starter.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * redis封装工具类
 *
 * @author luyuhao
 * @since 20/02/09 17:35
 */
@Slf4j
public class RedisUtil {

    private static final long EXPIRED = -2;

    private RedisTemplate<Object, Object> redisTemplate;

    private String keyPrefix;

    public RedisUtil(RedisTemplate<Object, Object> redisTemplate, String keyPrefix) {
        this.redisTemplate = redisTemplate;
        this.keyPrefix = keyPrefix;
    }

    /**
     * 清空redis
     */
    public void clear() {
        Set<Object> keys = redisTemplate.keys(keyPrefix + "*");
        if (!CollectionUtils.isEmpty(keys)) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * 根据前缀匹配所有key
     */
    public Set<Object> getKeysByPrefix(String prefix) {
        Set<Object> keys = redisTemplate.keys(keyPrefix + prefix + "*");
        return Optional.ofNullable(keys)
                .orElse(Collections.emptySet());
    }

    /**
     * 批量删除缓存
     */
    public void batchDeleteByPrefix(String prefix) {
        redisTemplate.delete(getKeysByPrefix(keyPrefix + prefix));
    }

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     */
    public void expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(keyPrefix + key, time, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public long getExpire(String key) {
        Long res = redisTemplate.getExpire(keyPrefix + key, TimeUnit.SECONDS);
        return Optional.ofNullable(res)
                .orElse(EXPIRED);
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean hasKey(String key) {
        try {
            return Optional.ofNullable(redisTemplate.hasKey(keyPrefix + key))
                    .orElse(false);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public void delete(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(keyPrefix + key[0]);
            } else {
                List<String> keyList = new ArrayList<>();
                for (String s : key) {
                    keyList.add(keyPrefix + s);
                }
                redisTemplate.delete(CollectionUtils.arrayToList(keyList));
            }
        }
    }

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(keyPrefix + key);
    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public void set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(keyPrefix + key, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public void set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(keyPrefix + key, value, time, TimeUnit.SECONDS);
            } else {
                set(keyPrefix + key, value);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 递增
     *
     * @param key   键
     * @param delta 要增加几(大于0)
     */
    public long inc(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return Optional.ofNullable(redisTemplate.opsForValue().increment(keyPrefix + key, delta))
                .orElse(EXPIRED);
    }

    /**
     * 递减
     *
     * @param key   键
     * @param delta 要减少几(小于0)
     */
    public long dec(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return Optional.ofNullable(redisTemplate.opsForValue().increment(keyPrefix + key, -delta))
                .orElse(EXPIRED);
    }

    /**
     * HashGet
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public Object hGet(String key, String item) {
        return redisTemplate.opsForHash().get(keyPrefix + key, item);
    }

    /**
     * 获取hashKey对应的所有键值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<Object, Object> hmGet(String key) {
        return redisTemplate.opsForHash().entries(keyPrefix + key);
    }

    /**
     * HashSet
     *
     * @param key 键
     * @param map 对应多个键值
     */
    public void hmSet(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(keyPrefix + key, map);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * HashSet 并设置时间
     *
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     */
    public void hmSet(String key, Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(keyPrefix + key, map);
            if (time > 0) {
                expire(keyPrefix + key, time);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     */
    public void hSet(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(keyPrefix + key, item, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     */
    public void hSet(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(keyPrefix + key, item, value);
            if (time > 0) {
                expire(keyPrefix + key, time);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public void hDelete(String key, Object... item) {
        redisTemplate.opsForHash().delete(keyPrefix + key, item);
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(keyPrefix + key, item);
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     */
    public double hInc(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(keyPrefix + key, item, by);
    }

    /**
     * hash递减
     *
     * @param key  键
     * @param item 项
     * @param by   要减少记(小于0)
     */
    public double hDec(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(keyPrefix + key, item, -by);
    }

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     */
    public Set<Object> sGet(String key) {
        try {
            return redisTemplate.opsForSet().members(keyPrefix + key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public boolean sHasKey(String key, Object value) {
        try {
            return Optional.ofNullable(redisTemplate.opsForSet().isMember(keyPrefix + key, value))
                    .orElse(false);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSet(String key, Object... values) {
        try {
            return Optional.ofNullable(redisTemplate.opsForSet().add(keyPrefix + key, values))
                    .orElse(EXPIRED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 将set数据放入缓存
     *
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSetAndTime(String key, long time, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().add(keyPrefix + key, values);
            if (time > 0) {
                expire(keyPrefix + key, time);
            }
            return Optional.ofNullable(count)
                    .orElse(EXPIRED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     */
    public long sGetSetSize(String key) {
        try {
            return Optional.ofNullable(redisTemplate.opsForSet().size(keyPrefix + key))
                    .orElse(EXPIRED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public long setRemove(String key, Object... values) {
        try {
            return Optional.ofNullable(redisTemplate.opsForSet().remove(keyPrefix + key, values))
                    .orElse(EXPIRED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束 0 到 -1代表所有值
     */
    public List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(keyPrefix + key, start, end);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 获取list缓存的长度
     *
     * @param key 键
     */
    public long lGetListSize(String key) {
        try {
            return Optional.ofNullable(redisTemplate.opsForList().size(keyPrefix + key))
                    .orElse(EXPIRED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 通过索引 获取list中的值
     *
     * @param key   键
     * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     */
    public Object lGetIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(keyPrefix + key, index);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }


    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     */
    public void lSet(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(keyPrefix + key, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     */
    public boolean lSet(String key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(keyPrefix + key, value);
            if (time > 0) {
                expire(keyPrefix + key, time);
            }
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     */
    public void lSet(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(keyPrefix + key, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     */
    public void lSet(String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(keyPrefix + key, value);
            if (time > 0) {
                expire(keyPrefix + key, time);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     */
    public void lUpdateIndex(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(keyPrefix + key, index, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 移除N个值为value
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public long lRemove(String key, long count, Object value) {
        try {
            return Optional.ofNullable(redisTemplate.opsForList().remove(keyPrefix + key, count, value))
                    .orElse(EXPIRED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 加锁
     *
     * @param key    key
     * @param value  值
     * @param second 过期时间
     * @return true/false
     * @author luyuhao
     * @date 20/12/04 21:51
     */
    public boolean tryLock(String key, Object value, long second) {
        return Optional.ofNullable(redisTemplate.opsForValue().setIfAbsent(keyPrefix + key, value, second, TimeUnit.SECONDS)).orElse(false);
    }

    /**
     * 释放锁
     *
     * @param key   key
     * @param value value
     * @author luyuhao
     * @date 20/12/04 21:59
     */
    public void releaseLock(String key, Object value) {
        Object object = get(key);
        if (object != null && object.equals(value)) {
            delete(key);
        }
    }
}
