package com.darren1112.dptms.common.redis.starter.core;

import redis.clients.jedis.JedisPubSub;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 统一接口
 *
 * @author luyuhao
 * @since 2021/8/4
 */
public interface RedisUtil {

    /**
     * 资源初始化
     *
     * @author luyuhao
     * @since 2021/8/11
     */
    void init();

    /**
     * 关闭资源
     *
     * @author luyuhao
     * @since 2021/8/11
     */
    void close();

    /**
     * 获取value
     *
     * @param key 键
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    String get(String key);

    /**
     * 含前缀-获取value
     *
     * @param prefix 前缀
     * @param key    键
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    String getWithPrefix(String prefix, String key);

    /**
     * 获取object value
     *
     * @param key 键
     * @return {@link T}
     * @author luyuhao
     * @since 2021/8/11
     */
    <T> T getObject(String key);

    /**
     * 含前缀-获取object value
     *
     * @param prefix 前缀
     * @param key    键
     * @return {@link T}
     * @author luyuhao
     * @since 2021/8/11
     */
    <T> T getObjectWithPrefix(String prefix, String key);

    /**
     * 设置key value
     *
     * @param key   键
     * @param value 值
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    String set(String key, String value);

    /**
     * 含前缀-设置key value
     *
     * @param prefix 前缀
     * @param key    键
     * @param value  值
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    String setWithPrefix(String prefix, String key, String value);

    /**
     * 设置key value
     *
     * @param key     键
     * @param value   值
     * @param seconds 有效时间（秒）
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    String set(String key, String value, int seconds);

    /**
     * 含前缀-设置key value
     *
     * @param prefix  前缀
     * @param key     键
     * @param value   值
     * @param seconds 有效时间（秒）
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    String setWithPrefix(String prefix, String key, String value, int seconds);

    /**
     * 根据前缀匹配所有key
     *
     * @param keyPrefix 前缀
     * @return {@link String}
     * @author luyuhao
     * @since 2021/11/14
     */
    Set<String> getKeys(String keyPrefix);

    /**
     * 根据前缀匹配所有key
     *
     * @param prefix    前缀
     * @param keyPrefix 查询前缀
     * @return {@link String}
     * @author luyuhao
     * @since 2021/11/14
     */
    Set<String> getKeysWithKeyPrefix(String prefix, String keyPrefix);

    /**
     * 根据策略设置key value
     *
     * @param key     键
     * @param value   值
     * @param nxxx    NX: 仅当key不存在时设置值
     *                XX: 仅当key存在时设置值
     * @param expx    EX seconds: 设定过期时间，单位为秒
     *                PX milliseconds: 设定过期时间，单位为毫秒
     * @param seconds 有效时间（秒）
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    String set(String key, String value, String nxxx, String expx, long seconds);

    /**
     * 含前缀-根据策略设置key value
     *
     * @param prefix  前缀
     * @param key     键
     * @param value   值
     * @param nxxx    NX: 仅当key不存在时设置值
     *                XX: 仅当key存在时设置值
     * @param expx    EX seconds: 设定过期时间，单位为秒
     *                PX milliseconds: 设定过期时间，单位为毫秒
     * @param seconds 有效时间（秒）
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    String setWithPrefix(String prefix, String key, String value, String nxxx, String expx, long seconds);

    /**
     * 设置object value
     *
     * @param key   键
     * @param value 值
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    <T> String setObject(String key, T value);

    /**
     * 含前缀-设置object value
     *
     * @param prefix 前缀
     * @param key    键
     * @param value  值
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    <T> String setObjectWithPrefix(String prefix, String key, T value);

    /**
     * 设置key value和有效时间
     *
     * @param key     键
     * @param value   值
     * @param seconds 有效时间（秒）
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    String setEx(String key, String value, Integer seconds);

    /**
     * 含前缀-设置key value和有效时间
     *
     * @param prefix  前缀
     * @param key     键
     * @param value   值
     * @param seconds 有效时间（秒）
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    String setExWithPrefix(String prefix, String key, String value, Integer seconds);

    /**
     * 设置key objectValue和有效时间
     *
     * @param key     键
     * @param value   值
     * @param seconds 有效时间（秒）
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    <T> String setExObject(String key, T value, Integer seconds);

    /**
     * 含前缀-设置key objectValue和有效时间
     *
     * @param prefix  前缀
     * @param key     键
     * @param value   值
     * @param seconds 有效时间（秒）
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    <T> String setExObjectWithPrefix(String prefix, String key, T value, Integer seconds);

    /**
     * 设置key byteValue和有效时间
     *
     * @param key     键
     * @param value   值
     * @param seconds 有效时间（秒）
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    String setExBytes(String key, byte[] value, Integer seconds);

    /**
     * 含前缀-设置key byteValue和有效时间
     *
     * @param prefix  前缀
     * @param key     键
     * @param value   值
     * @param seconds 有效时间（秒）
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    String setExBytesWithPrefix(String prefix, String key, byte[] value, Integer seconds);

    /**
     * 获取byteValue
     *
     * @param key 键
     * @return {@link byte[]}
     * @author luyuhao
     * @since 2021/8/11
     */
    byte[] getBytes(String key);

    /**
     * 含前缀-获取byteValue
     *
     * @param prefix 前缀
     * @param key    键
     * @return {@link byte[]}
     * @author luyuhao
     * @since 2021/8/11
     */
    byte[] getBytesWithPrefix(String prefix, String key);

    /**
     * 设置key byteValue
     *
     * @param key   键
     * @param value 值
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    String setBytes(String key, byte[] value);

    /**
     * 含前缀-设置key byteValue
     *
     * @param prefix 前缀
     * @param key    键
     * @param value  值
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    String setBytesWithPrefix(String prefix, String key, byte[] value);

    /**
     * 设置key map
     *
     * @param key   键
     * @param field 域
     * @param value 值
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long setMap(String key, String field, String value);

    /**
     * 含前缀-设置key map
     *
     * @param prefix 前缀
     * @param key    键
     * @param field  域
     * @param value  值
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long setMapWithPrefix(String prefix, String key, String field, String value);

    /**
     * 设置key map
     *
     * @param key   键
     * @param field 域
     * @param value 值
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    <T> Long setMapObject(String key, String field, T value);

    /**
     * 含前缀-设置key map
     *
     * @param prefix 前缀
     * @param key    键
     * @param field  域
     * @param value  值
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    <T> Long setMapObjectWithPrefix(String prefix, String key, String field, T value);

    /**
     * 设置key map
     *
     * @param key   键
     * @param field 域
     * @param value 值
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long hset(String key, String field, String value);

    /**
     * 含前缀-设置key map
     *
     * @param prefix 前缀
     * @param key    键
     * @param field  域
     * @param value  值
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long hsetWithPrefix(String prefix, String key, String field, String value);

    /**
     * 获取map域的值
     *
     * @param key   键
     * @param field 域
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    String getMap(String key, String field);

    /**
     * 含前缀-获取map域的值
     *
     * @param prefix 前缀
     * @param key    键
     * @param field  域
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    String getMapWithPrefix(String prefix, String key, String field);

    /**
     * 获取map域的object值
     *
     * @param key   键
     * @param field 域
     * @return {@link T}
     * @author luyuhao
     * @since 2021/8/11
     */
    <T> T getMapObject(String key, String field);

    /**
     * 含前缀-获取map域的object值
     *
     * @param prefix 前缀
     * @param key    键
     * @param field  域
     * @return {@link T}
     * @author luyuhao
     * @since 2021/8/11
     */
    <T> T getMapObjectWithPrefix(String prefix, String key, String field);

    /**
     * 获取map域的byte值
     *
     * @param key   键
     * @param field 域
     * @return {@link byte[]}
     * @author luyuhao
     * @since 2021/8/11
     */
    byte[] getMapBytes(String key, String field);

    /**
     * 含前缀-获取map域的byte值
     *
     * @param prefix 前缀
     * @param key    键
     * @param field  域
     * @return {@link byte[]}
     * @author luyuhao
     * @since 2021/8/11
     */
    byte[] getMapBytesWithPrefix(String prefix, String key, String field);

    /**
     * 设置map域的byte值
     *
     * @param key   键
     * @param field 域
     * @param value 值
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long setMapBytes(String key, String field, byte[] value);

    /**
     * 含前缀-设置map域的byte值
     *
     * @param prefix 前缀
     * @param key    键
     * @param field  域
     * @param value  值
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long setMapBytesWithPrefix(String prefix, String key, String field, byte[] value);

    /**
     * 移除map的域
     *
     * @param key    键
     * @param fields 域
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long removeMapField(String key, String... fields);

    /**
     * 含前缀-移除map的域
     *
     * @param prefix 前缀
     * @param key    键
     * @param fields 域
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long removeMapFieldWithPrefix(String prefix, String key, String... fields);

    /**
     * 设置key的有效期
     *
     * @param key     键
     * @param seconds 有效时间（秒）
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long expire(String key, Integer seconds);

    /**
     * 含前缀-设置key的有效期
     *
     * @param prefix  前缀
     * @param key     键
     * @param seconds 有效时间（秒）
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long expireWithPrefix(String prefix, String key, Integer seconds);

    /**
     * 查看key的有效期
     *
     * @param key 键
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long ttl(String key);

    /**
     * 含前缀-查看key的有效期
     *
     * @param prefix 前缀
     * @param key    键
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long ttlWithPrefix(String prefix, String key);

    /**
     * 移除key
     *
     * @param key 键
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long removeKey(String key);

    /**
     * 含前缀-移除key
     *
     * @param prefix 前缀
     * @param key    键
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long removeKeyWithPrefix(String prefix, String key);

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return {@link boolean}
     * @author luyuhao
     * @since 2021/8/11
     */
    boolean exists(String key);

    /**
     * 含前缀-判断key是否存在
     *
     * @param prefix 前缀
     * @param key    键
     * @return {@link boolean}
     * @author luyuhao
     * @since 2021/8/11
     */
    boolean existsWithPrefix(String prefix, String key);

    /**
     * 仅当key不存在时设置值
     *
     * @param key   键
     * @param value 值
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long setNx(String key, String value);

    /**
     * 含前缀-仅当key不存在时设置值
     *
     * @param prefix 前缀
     * @param key    键
     * @param value  值
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long setNxWithPrefix(String prefix, String key, String value);

    /**
     * 仅当key不存在时设置值
     *
     * @param key   键
     * @param value 值
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    <T> Long setNxObject(String key, T value);

    /**
     * 含前缀-仅当key不存在时设置值
     *
     * @param prefix 前缀
     * @param key    键
     * @param value  值
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    <T> Long setNxObjectWithPrefix(String prefix, String key, T value);

    /**
     * 仅当key不存在时设置值
     *
     * @param key   键
     * @param value 值
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long setNxBytes(String key, byte[] value);

    /**
     * 含前缀-仅当key不存在时设置值
     *
     * @param prefix 前缀
     * @param key    键
     * @param value  值
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long setNxBytesWithPrefix(String prefix, String key, byte[] value);

    /**
     * 匹配获取map的域
     *
     * @param pattern 匹配值
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    Set<String> getMapKeys(String pattern);

    /**
     * 含前缀-匹配获取map的域
     *
     * @param prefix  前缀
     * @param pattern 匹配值
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    Set<String> getMapKeysWithPrefix(String prefix, String pattern);

    /**
     * 匹配获取map域的值
     *
     * @param key    键
     * @param fields 域
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    List<String> getMapValues(String key, String... fields);

    /**
     * 含前缀-匹配获取map域的值
     *
     * @param prefix 前缀
     * @param key    键
     * @param fields 域
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    List<String> getMapValuesWithPrefix(String prefix, String key, String... fields);

    /**
     * 设置map
     *
     * @param key  键
     * @param hash map
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    String setMap(String key, Map<String, String> hash);

    /**
     * 含前缀-设置map
     *
     * @param prefix 前缀
     * @param key    键
     * @param hash   map
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    String setMapWithPrefix(String prefix, String key, Map<String, String> hash);

    /**
     * 值自增
     *
     * @param key 键
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long incr(String key);

    /**
     * 含前缀-值自增
     *
     * @param prefix 前缀
     * @param key    键
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long incrWithPrefix(String prefix, String key);

    /**
     * 值自增
     *
     * @param key       键
     * @param increment 自增值
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long incrBy(String key, Long increment);

    /**
     * 含前缀-值自增
     *
     * @param prefix    前缀
     * @param key       键
     * @param increment 自增值
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long incrByWithPrefix(String prefix, String key, Long increment);

    /**
     * map域的值自增
     *
     * @param key   键
     * @param field 域
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long hincr(String key, String field);

    /**
     * 含前缀-map域的值自增
     *
     * @param prefix 前缀
     * @param key    键
     * @param field  域
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long hincrWithPrefix(String prefix, String key, String field);

    /**
     * 发布
     *
     * @param jedisPubSub 发布
     * @author luyuhao
     * @since 2021/8/11
     */
    void psubscribe(JedisPubSub jedisPubSub);

    /**
     * 订阅
     *
     * @param jedisPubSub 发布
     * @param patterns    匹配值
     * @author luyuhao
     * @since 2021/8/11
     */
    void psubscribe(JedisPubSub jedisPubSub, String... patterns);

    /**
     * 订阅
     *
     * @param listener 监听器
     * @param channels 频道
     * @author luyuhao
     * @since 2021/8/11
     */
    void subscribe(JedisPubSub listener, String... channels);

    /**
     * 移除并返回list的第一个元素
     *
     * @param key 键
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    String lpop(String key);

    /**
     * 含前缀-移除并返回list的第一个元素
     *
     * @param prefix 前缀
     * @param key    键
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    String lpopWithPrefix(String prefix, String key);

    /**
     * push到list的头
     *
     * @param key     键
     * @param strings 值
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long lpush(String key, final String... strings);

    /**
     * 含前缀-push到list的头
     *
     * @param prefix  前缀
     * @param key     键
     * @param strings 值
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long lpushWithPrefix(String prefix, String key, final String... strings);

    /**
     * 移除并返回list的最后一个元素
     *
     * @param key 键
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    String rpop(String key);

    /**
     * 含前缀-移除并返回list的最后一个元素
     *
     * @param prefix 前缀
     * @param key    键
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    String rpopWithPrefix(String prefix, String key);

    /**
     * push到list的尾
     *
     * @param key     键
     * @param strings 值
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long rpush(String key, final String... strings);

    /**
     * 含前缀-push到list的尾
     *
     * @param prefix  前缀
     * @param key     键
     * @param strings 值
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long rpushWithPrefix(String prefix, String key, final String... strings);

    /**
     * 查询list长度
     *
     * @param key 键
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long llen(String key);

    /**
     * 含前缀-查询list长度
     *
     * @param prefix 前缀
     * @param key    键
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long llenWithPrefix(String prefix, String key);

    /**
     * 查询list中下标为index的元素
     *
     * @param key   键
     * @param index 索引
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    String lIndex(String key, long index);

    /**
     * 含前缀-查询list中下标为index的元素
     *
     * @param prefix 前缀
     * @param key    键
     * @param index  索引
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    String lIndexWithPrefix(String prefix, String key, long index);

    /**
     * 查询list区间内的值
     *
     * @param key   键
     * @param start 起始下标
     * @param end   结束下标
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    List<String> lrange(String key, long start, long end);

    /**
     * 含前缀-查询list区间内的值
     *
     * @param prefix 前缀
     * @param key    键
     * @param start  起始下标
     * @param end    结束下标
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    List<String> lrangeWithPrefix(String prefix, String key, long start, long end);

    /**
     * 设置set的值
     *
     * @param key     键
     * @param members 值
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long sadd(String key, String... members);

    /**
     * 含前缀-设置set的值
     *
     * @param prefix  前缀
     * @param key     键
     * @param members 值
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long saddWithPrefix(String prefix, String key, String... members);

    /**
     * 设置set的值
     *
     * @param key     键
     * @param members 值
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long sadd(String key, byte[]... members);

    /**
     * 含前缀-设置set的值
     *
     * @param prefix  前缀
     * @param key     键
     * @param members 值
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long saddWithPrefix(String prefix, String key, byte[]... members);

    /**
     * 删除set中的值
     *
     * @param key     键
     * @param members 值
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long srem(String key, String... members);

    /**
     * 含前缀-删除set中的值
     *
     * @param prefix  前缀
     * @param key     键
     * @param members 值
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long sremWithPrefix(String prefix, String key, String... members);

    /**
     * 返回名称为key的set的基数
     *
     * @param key 键
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long scard(String key);

    /**
     * 含前缀-返回名称为key的set的基数
     *
     * @param prefix 前缀
     * @param key    键
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long scardWithPrefix(String prefix, String key);

    /**
     * 返回名称为key的set的所有元素
     *
     * @param key 键
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    Set<String> smembers(String key);

    /**
     * 含前缀-返回名称为key的set的所有元素
     *
     * @param prefix 前缀
     * @param key    键
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    Set<String> smembersWithPrefix(String prefix, String key);

    /**
     * 随机返回名称为key的set的一个元素
     *
     * @param key   键
     * @param count 返回的元素数量
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    List<String> srandmember(String key, int count);

    /**
     * 含前缀-随机返回名称为key的set的一个元素
     *
     * @param prefix 前缀
     * @param key    键
     * @param count  返回的元素数量
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    List<String> srandmemberWithPrefix(String prefix, String key, int count);

    /**
     * 向名称为key的zset中添加元素member
     * score用于排序，如果该元素已经存在，则根据score更新该元素的顺序
     *
     * @param key    键
     * @param score  分数
     * @param member 值
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long zadd(String key, double score, String member);

    /**
     * 含前缀-向名称为key的zset中添加元素member
     * score用于排序，如果该元素已经存在，则根据score更新该元素的顺序
     *
     * @param prefix 前缀
     * @param key    键
     * @param score  分数
     * @param member 值
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long zaddWithPrefix(String prefix, String key, double score, String member);

    /**
     * 删除名称为key的zset中的元素member
     *
     * @param key     键
     * @param members 值
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long zrem(String key, String... members);

    /**
     * 含前缀-删除名称为key的zset中的元素member
     *
     * @param prefix  前缀
     * @param key     键
     * @param members 值
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long zremWithPrefix(String prefix, String key, String... members);

    /**
     * 删除名称为key的zset中rank >= start <= stop的所有元素
     *
     * @param key   键
     * @param start 起始下标
     * @param stop  结束下标
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long zremrangeByRank(String key, long start, long stop);

    /**
     * 含前缀-删除名称为key的zset中rank >= start <= stop的所有元素
     *
     * @param prefix 前缀
     * @param key    键
     * @param start  起始下标
     * @param stop   结束下标
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long zremrangeByRankWithPrefix(String prefix, String key, long start, long stop);

    /**
     * 删除名称为key的zset中score >= min且score <= max的所有元素
     *
     * @param key 键
     * @param min 最小值
     * @param max 最大值
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long zremrangeByScore(String key, double min, double max);

    /**
     * 含前缀-删除名称为key的zset中score >= min且score <= max的所有元素
     *
     * @param prefix 前缀
     * @param key    键
     * @param min    最小值
     * @param max    最大值
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long zremrangeByScoreWithPrefix(String prefix, String key, double min, double max);

    /**
     * 返回名称为key的zset中的index从start到end的所有元素
     *
     * @param key   键
     * @param start 起始下标
     * @param end   结束下标
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    Set<String> zrange(String key, long start, long end);

    /**
     * 含前缀-返回名称为key的zset中的index从start到end的所有元素
     *
     * @param prefix 前缀
     * @param key    键
     * @param start  起始下标
     * @param end    结束下标
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    Set<String> zrangeWithPrefix(String prefix, String key, long start, long end);

    /**
     * 返回名称为key的zset中score >= min且score <= max的所有元素
     *
     * @param key 键
     * @param min 最小值
     * @param max 最大值
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    Set<String> zrangeByScore(String key, double min, double max);

    /**
     * 含前缀-返回名称为key的zset中score >= min且score <= max的所有元素
     *
     * @param prefix 前缀
     * @param key    键
     * @param min    最小值
     * @param max    最大值
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    Set<String> zrangeByScoreWithPrefix(String prefix, String key, double min, double max);

    /**
     * 返回名称为key的zset中的index从start到end的所有元素
     *
     * @param key   键
     * @param start 起始下标
     * @param end   结束下标
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    Set<String> zrevrange(String key, long start, long end);

    /**
     * 含前缀-返回名称为key的zset中的index从start到end的所有元素
     *
     * @param prefix 前缀
     * @param key    键
     * @param start  起始下标
     * @param end    结束下标
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    Set<String> zrevrangeWithPrefix(String prefix, String key, long start, long end);

    /**
     * 返回名称为key的zset中元素element的score
     *
     * @param key    键
     * @param member 值
     * @return {@link Double}
     * @author luyuhao
     * @since 2021/8/11
     */
    Double zscore(String key, String member);

    /**
     * 含前缀-返回名称为key的zset中元素element的score
     *
     * @param prefix 前缀
     * @param key    键
     * @param member 值
     * @return {@link Double}
     * @author luyuhao
     * @since 2021/8/11
     */
    Double zscoreWithPrefix(String prefix, String key, String member);

    /**
     * 如果在名称为key的zset中已经存在元素member，则该元素的score增加increment
     * 否则向集合中添加该元素，其score的值为increment
     *
     * @param key       键
     * @param increment 自增值
     * @param member    值
     * @return {@link Double}
     * @author luyuhao
     * @since 2021/8/11
     */
    Double zincrby(String key, double increment, String member);

    /**
     * 含前缀-如果在名称为key的zset中已经存在元素member，则该元素的score增加increment
     * 否则向集合中添加该元素，其score的值为increment
     *
     * @param prefix    前缀
     * @param key       键
     * @param increment 自增值
     * @param member    值
     * @return {@link Double}
     * @author luyuhao
     * @since 2021/8/11
     */
    Double zincrbyWithPrefix(String prefix, String key, double increment, String member);

    /**
     * 分数值在 min 和 max 之间的元素的数量
     *
     * @param key 键
     * @param min 最小值
     * @param max 最大值
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long zcount(String key, double min, double max);

    /**
     * 含前缀-分数值在 min 和 max 之间的元素的数量
     *
     * @param prefix 前缀
     * @param key    键
     * @param min    最小值
     * @param max    最大值
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    Long zcountWithPrefix(String prefix, String key, double min, double max);

    /**
     * jedis信息
     *
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    String info();

    /**
     * 执行lua脚本
     *
     * @param script lua脚本
     * @param keys   键集合
     * @param args   参数集合
     * @return {@link Object}
     * @author luyuhao
     * @since 2021/8/11
     */
    Object eval(String script, List<String> keys, List<String> args);

    /**
     * 获取key的偏移量
     *
     * @param key    键
     * @param offset 偏移量
     * @return {@link boolean}
     * @author luyuhao
     * @since 2021/8/11
     */
    boolean getBit(String key, long offset);

    /**
     * 含前缀-获取key的偏移量
     *
     * @param prefix 前缀
     * @param key    键
     * @param offset 偏移量
     * @return {@link boolean}
     * @author luyuhao
     * @since 2021/8/11
     */
    boolean getBitWithPrefix(String prefix, String key, long offset);

    /**
     * 设置key的偏移量
     *
     * @param key    键
     * @param offset 偏移量
     * @param value  值
     * @return {@link boolean}
     * @author luyuhao
     * @since 2021/8/11
     */
    boolean setBit(String key, long offset, boolean value);

    /**
     * 含前缀-设置key的偏移量
     *
     * @param prefix 前缀
     * @param key    键
     * @param offset 偏移量
     * @param value  值
     * @return {@link boolean}
     * @author luyuhao
     * @since 2021/8/11
     */
    boolean setBitWithPrefix(String prefix, String key, long offset, boolean value);

    /**
     * 统计key的偏移量
     *
     * @param key 键
     * @return {@link long}
     * @author luyuhao
     * @since 2021/8/11
     */
    long bitCount(String key);

    /**
     * 含前缀-统计key的偏移量
     *
     * @param prefix 前缀
     * @param key    键
     * @return {@link long}
     * @author luyuhao
     * @since 2021/8/11
     */
    long bitCountWithPrefix(String prefix, String key);

    /**
     * 设置前缀
     *
     * @param prefix 前缀
     * @author luyuhao
     * @since 2021/8/11
     */
    void setPrefix(String prefix);

    /**
     * 设置最大连接数
     *
     * @param maxTotal 最大连接数
     * @author luyuhao
     * @since 2021/8/11
     */
    void setMaxTotal(int maxTotal);

    /**
     * 设置最大空闲连接数
     *
     * @param maxIdle 最大空闲连接数
     * @author luyuhao
     * @since 2021/8/11
     */
    void setMaxIdle(int maxIdle);

    /**
     * 设置获取连接时的最大等待毫秒数
     *
     * @param maxWaitMills 最大等待毫秒数
     * @author luyuhao
     * @since 2021/8/11
     */
    void setMaxWaitMills(long maxWaitMills);

    /**
     * 设置ip
     *
     * @param ip ip
     * @author luyuhao
     * @since 2021/8/11
     */
    void setIp(String ip);

    /**
     * 设置密码
     *
     * @param password 密码
     * @author luyuhao
     * @since 2021/8/11
     */
    void setPassword(String password);

    /**
     * 设置是否启用心跳检测
     *
     * @param enableHeartbeat 是否启用心跳检测
     * @author luyuhao
     * @since 2021/8/11
     */
    void setEnableHeartbeat(boolean enableHeartbeat);

}
