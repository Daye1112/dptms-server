package com.darren1112.dptms.common.redis.starter.core;

import com.darren1112.dptms.common.core.util.CollectionUtil;
import com.darren1112.dptms.common.core.util.SerializeUtil;
import com.darren1112.dptms.common.core.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;

import java.io.IOException;
import java.util.*;

/**
 * redis集群模式实现
 *
 * @author luyuhao
 * @since 2021/8/4
 */
public class RedisClusterUtil implements RedisUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisClusterUtil.class);

    /**
     * 前缀
     */
    private String prefix;

    /**
     * 最大连接数
     */
    private int maxTotal;

    /**
     * 最大空闲连接数
     */
    private int maxIdle;

    /**
     * 最大等待毫秒数
     */
    private long maxWaitMillis;

    /**
     * ip
     */
    private String ip;

    /**
     * 密码
     */
    private String password;

    private JedisCluster jedisCluster;

    /**
     * 存放集群的ip及端口
     */
    private Set<HostAndPort> nodes = new LinkedHashSet<>();

    /**
     * 资源初始化
     *
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public void init() {
        if (StringUtil.isEmpty(this.ip)) {
            return;
        }
        String[] ips = this.ip.split(",");

        for (String subIp : ips) {
            String[] ipAndPort = subIp.split(":");
            this.nodes.add(new HostAndPort(ipAndPort[0], Integer.valueOf(ipAndPort[1])));
        }

        this.initCluster();
    }

    private void initCluster() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(this.maxTotal);
        poolConfig.setMaxIdle(this.maxIdle);
        poolConfig.setMaxWaitMillis(this.maxWaitMillis);
        if (StringUtil.isEmpty(this.password)) {
            this.jedisCluster = new JedisCluster(this.nodes, 2000, 2000, 5, this.password, poolConfig);
        } else {
            this.jedisCluster = new JedisCluster(this.nodes, poolConfig);
        }

    }

    /**
     * 关闭资源
     *
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public void close() {
        if (this.jedisCluster != null) {
            try {
                this.jedisCluster.close();
            } catch (IOException e) {
                LOGGER.warn(e.getMessage(), e);
            }
            this.jedisCluster = null;
        }
    }

    /**
     * 获取value
     *
     * @param key 键
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public String get(String key) {
        return getWithPrefix(this.prefix, key);
    }

    /**
     * 含前缀-获取value
     *
     * @param prefix 前缀
     * @param key    键
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public String getWithPrefix(String prefix, String key) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.get(key);
    }

    /**
     * 获取object value
     *
     * @param key 键
     * @return {@link T}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public <T> T getObject(String key) {
        return getObjectWithPrefix(this.prefix, key);
    }

    /**
     * 含前缀-获取object value
     *
     * @param prefix 前缀
     * @param key    键
     * @return {@link T}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public <T> T getObjectWithPrefix(String prefix, String key) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.deserialize(this.getBytesWithPrefix(prefix, key));
    }

    /**
     * 设置key value
     *
     * @param key   键
     * @param value 值
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public String set(String key, String value) {
        return setWithPrefix(this.prefix, key, value);
    }

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
    @Override
    public String setWithPrefix(String prefix, String key, String value) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.set(key, value);
    }

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
    @Override
    public String set(String key, String value, int seconds) {
        return setWithPrefix(this.prefix, key, value, seconds);
    }

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
    @Override
    public String setWithPrefix(String prefix, String key, String value, int seconds) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.setex(key, seconds, value);
    }

    /**
     * 根据前缀匹配所有key
     *
     * @param keyPrefix 前缀
     * @return {@link String}
     * @author luyuhao
     * @since 2021/11/14
     */
    @Override
    public Set<String> getKeys(String keyPrefix) {
        return getKeysWithKeyPrefix(this.prefix, keyPrefix);
    }

    /**
     * 根据前缀匹配所有key
     *
     * @param prefix    前缀
     * @param keyPrefix 查询前缀
     * @return {@link String}
     * @author luyuhao
     * @since 2021/11/14
     */
    @Override
    public Set<String> getKeysWithKeyPrefix(String prefix, String keyPrefix) {
        if (prefix != null) {
            keyPrefix = prefix + keyPrefix;
        }
        Set<String> keys = new HashSet<>();
        Map<String, JedisPool> clusterNodes = this.jedisCluster.getClusterNodes();

        for (String key : clusterNodes.keySet()) {
            JedisPool jedisPool = clusterNodes.get(key);
            Jedis resource = jedisPool.getResource();
            keys.addAll(resource.keys(keyPrefix));
        }

        return keys;
    }

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
    @Override
    public String set(String key, String value, String nxxx, String expx, long seconds) {
        return setWithPrefix(this.prefix, key, value, nxxx, expx, seconds);
    }

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
    @Override
    public String setWithPrefix(String prefix, String key, String value, String nxxx, String expx, long seconds) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.set(key, value, nxxx, expx, seconds);
    }

    /**
     * 设置object value
     *
     * @param key   键
     * @param value 值
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public <T> String setObject(String key, T value) {
        return setObjectWithPrefix(this.prefix, key, value);
    }

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
    @Override
    public <T> String setObjectWithPrefix(String prefix, String key, T value) {
        return this.setBytesWithPrefix(prefix, key, SerializeUtil.serialize(value));
    }

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
    @Override
    public String setEx(String key, String value, Integer seconds) {
        return setExWithPrefix(this.prefix, key, value, seconds);
    }

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
    @Override
    public String setExWithPrefix(String prefix, String key, String value, Integer seconds) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.setex(key, seconds, value);
    }

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
    @Override
    public <T> String setExObject(String key, T value, Integer seconds) {
        return setExObjectWithPrefix(this.prefix, key, value, seconds);
    }

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
    @Override
    public <T> String setExObjectWithPrefix(String prefix, String key, T value, Integer seconds) {
        return this.setExBytesWithPrefix(prefix, key, SerializeUtil.serialize(value), seconds);
    }

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
    @Override
    public String setExBytes(String key, byte[] value, Integer seconds) {
        return setExBytesWithPrefix(this.prefix, key, value, seconds);
    }

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
    @Override
    public String setExBytesWithPrefix(String prefix, String key, byte[] value, Integer seconds) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.setex(key.getBytes(), seconds, value);
    }

    /**
     * 获取byteValue
     *
     * @param key 键
     * @return {@link byte[]}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public byte[] getBytes(String key) {
        return getBytesWithPrefix(this.prefix, key);
    }

    /**
     * 含前缀-获取byteValue
     *
     * @param prefix 前缀
     * @param key    键
     * @return {@link byte[]}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public byte[] getBytesWithPrefix(String prefix, String key) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.get(key.getBytes());
    }

    /**
     * 设置key byteValue
     *
     * @param key   键
     * @param value 值
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public String setBytes(String key, byte[] value) {
        return setBytesWithPrefix(this.prefix, key, value);
    }

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
    @Override
    public String setBytesWithPrefix(String prefix, String key, byte[] value) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.set(key.getBytes(), value);
    }

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
    @Override
    public Long setMap(String key, String field, String value) {
        return setMapWithPrefix(this.prefix, key, field, value);
    }

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
    @Override
    public Long setMapWithPrefix(String prefix, String key, String field, String value) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.hset(key, field, value);
    }

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
    @Override
    public <T> Long setMapObject(String key, String field, T value) {
        return setMapObjectWithPrefix(this.prefix, key, field, value);
    }

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
    @Override
    public <T> Long setMapObjectWithPrefix(String prefix, String key, String field, T value) {
        return this.setMapBytesWithPrefix(prefix, key, field, SerializeUtil.serialize(value));
    }

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
    @Override
    public Long hset(String key, String field, String value) {
        return hsetWithPrefix(this.prefix, key, field, value);
    }

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
    @Override
    public Long hsetWithPrefix(String prefix, String key, String field, String value) {
        return this.setMapWithPrefix(prefix, key, field, value);
    }

    /**
     * 获取map域的值
     *
     * @param key   键
     * @param field 域
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public String getMap(String key, String field) {
        return getMapWithPrefix(this.prefix, key, field);
    }

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
    @Override
    public String getMapWithPrefix(String prefix, String key, String field) {
        if (prefix != null) {
            key = prefix + key;
        }

        return this.jedisCluster.hget(key, field);
    }

    /**
     * 获取map域的object值
     *
     * @param key   键
     * @param field 域
     * @return {@link T}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public <T> T getMapObject(String key, String field) {
        return getMapObjectWithPrefix(this.prefix, key, field);
    }

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
    @Override
    public <T> T getMapObjectWithPrefix(String prefix, String key, String field) {
        return this.deserialize(this.getMapBytesWithPrefix(prefix, key, field));
    }

    /**
     * 获取map域的byte值
     *
     * @param key   键
     * @param field 域
     * @return {@link byte[]}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public byte[] getMapBytes(String key, String field) {
        return getMapBytesWithPrefix(this.prefix, key, field);
    }

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
    @Override
    public byte[] getMapBytesWithPrefix(String prefix, String key, String field) {
        if (prefix != null) {
            key = prefix + key;
        }

        return this.jedisCluster.hget(key.getBytes(), field.getBytes());
    }

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
    @Override
    public Long setMapBytes(String key, String field, byte[] value) {
        return setMapBytesWithPrefix(this.prefix, key, field, value);
    }

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
    @Override
    public Long setMapBytesWithPrefix(String prefix, String key, String field, byte[] value) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.hset(key.getBytes(), field.getBytes(), value);
    }

    /**
     * 移除map的域
     *
     * @param key    键
     * @param fields 域
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public Long removeMapField(String key, String... fields) {
        return removeMapFieldWithPrefix(this.prefix, key, fields);
    }

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
    @Override
    public Long removeMapFieldWithPrefix(String prefix, String key, String... fields) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.hdel(key, fields);
    }

    /**
     * 设置key的有效期
     *
     * @param key     键
     * @param seconds 有效时间（秒）
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public Long expire(String key, Integer seconds) {
        return expireWithPrefix(this.prefix, key, seconds);
    }

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
    @Override
    public Long expireWithPrefix(String prefix, String key, Integer seconds) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.expire(key, seconds);
    }

    /**
     * 查看key的有效期
     *
     * @param key 键
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public Long ttl(String key) {
        return ttlWithPrefix(this.prefix, key);
    }

    /**
     * 含前缀-查看key的有效期
     *
     * @param prefix 前缀
     * @param key    键
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public Long ttlWithPrefix(String prefix, String key) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.ttl(key);
    }

    /**
     * 移除key
     *
     * @param key 键
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public Long removeKey(String key) {
        return removeKeyWithPrefix(this.prefix, key);
    }

    /**
     * 含前缀-移除key
     *
     * @param prefix 前缀
     * @param key    键
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public Long removeKeyWithPrefix(String prefix, String key) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.del(key);
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return {@link boolean}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public boolean exists(String key) {
        return existsWithPrefix(this.prefix, key);
    }

    /**
     * 含前缀-判断key是否存在
     *
     * @param prefix 前缀
     * @param key    键
     * @return {@link boolean}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public boolean existsWithPrefix(String prefix, String key) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.exists(key);
    }

    /**
     * 仅当key不存在时设置值
     *
     * @param key   键
     * @param value 值
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public Long setNx(String key, String value) {
        return setNxWithPrefix(this.prefix, key, value);
    }

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
    @Override
    public Long setNxWithPrefix(String prefix, String key, String value) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.setnx(key, value);
    }

    /**
     * 仅当key不存在时设置值
     *
     * @param key   键
     * @param value 值
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public <T> Long setNxObject(String key, T value) {
        return setNxObjectWithPrefix(this.prefix, key, value);
    }

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
    @Override
    public <T> Long setNxObjectWithPrefix(String prefix, String key, T value) {
        return this.setNxBytesWithPrefix(prefix, key, SerializeUtil.serialize(value));
    }

    /**
     * 仅当key不存在时设置值
     *
     * @param key   键
     * @param value 值
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public Long setNxBytes(String key, byte[] value) {
        return setNxBytesWithPrefix(this.prefix, key, value);
    }

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
    @Override
    public Long setNxBytesWithPrefix(String prefix, String key, byte[] value) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.setnx(key.getBytes(), value);
    }

    /**
     * 匹配获取map的域
     *
     * @param pattern 匹配值
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public Set<String> getMapKeys(String pattern) {
        return getMapKeysWithPrefix(this.prefix, pattern);
    }

    /**
     * 含前缀-匹配获取map的域
     *
     * @param prefix  前缀
     * @param pattern 匹配值
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public Set<String> getMapKeysWithPrefix(String prefix, String pattern) {
        if (this.prefix != null) {
            pattern = this.prefix + pattern;
        }
        return this.jedisCluster.hkeys(pattern);
    }

    /**
     * 匹配获取map域的值
     *
     * @param key    键
     * @param fields 域
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public List<String> getMapValues(String key, String... fields) {
        return getMapValuesWithPrefix(this.prefix, key, fields);
    }

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
    @Override
    public List<String> getMapValuesWithPrefix(String prefix, String key, String... fields) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.hmget(key, fields);
    }

    /**
     * 设置map
     *
     * @param key  键
     * @param hash map
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public String setMap(String key, Map<String, String> hash) {
        return setMapWithPrefix(this.prefix, key, hash);
    }

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
    @Override
    public String setMapWithPrefix(String prefix, String key, Map<String, String> hash) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.hmset(key, hash);
    }

    /**
     * 值自增
     *
     * @param key 键
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public Long incr(String key) {
        return incrWithPrefix(this.prefix, key);
    }

    /**
     * 含前缀-值自增
     *
     * @param prefix 前缀
     * @param key    键
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public Long incrWithPrefix(String prefix, String key) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.incr(key);
    }

    /**
     * 值自增
     *
     * @param key       键
     * @param increment 自增值
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public Long incrBy(String key, Long increment) {
        return incrByWithPrefix(this.prefix, key, increment);
    }

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
    @Override
    public Long incrByWithPrefix(String prefix, String key, Long increment) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.incrBy(key, increment);
    }

    /**
     * map域的值自增
     *
     * @param key   键
     * @param field 域
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public Long hincr(String key, String field) {
        return hincrWithPrefix(this.prefix, key, field);
    }

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
    @Override
    public Long hincrWithPrefix(String prefix, String key, String field) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.hincrBy(key, field, 1);
    }

    /**
     * 发布
     *
     * @param jedisPubSub 发布
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public void psubscribe(JedisPubSub jedisPubSub) {
        this.psubscribe(jedisPubSub, "*");
    }

    /**
     * 订阅
     *
     * @param jedisPubSub 发布
     * @param patterns    匹配值
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public void psubscribe(JedisPubSub jedisPubSub, String... patterns) {
        this.jedisCluster.psubscribe(jedisPubSub, patterns);
    }

    /**
     * 订阅
     *
     * @param listener 监听器
     * @param channels 频道
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public void subscribe(JedisPubSub listener, String... channels) {
        this.jedisCluster.subscribe(listener, channels);
    }

    /**
     * 移除并返回list的第一个元素
     *
     * @param key 键
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public String lpop(String key) {
        return lpopWithPrefix(this.prefix, key);
    }

    /**
     * 含前缀-移除并返回list的第一个元素
     *
     * @param prefix 前缀
     * @param key    键
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public String lpopWithPrefix(String prefix, String key) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.lpop(key);
    }

    /**
     * push到list的头
     *
     * @param key     键
     * @param strings 值
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public Long lpush(String key, String... strings) {
        return lpushWithPrefix(this.prefix, key, strings);
    }

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
    @Override
    public Long lpushWithPrefix(String prefix, String key, String... strings) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.lpush(key, strings);
    }

    /**
     * 移除并返回list的最后一个元素
     *
     * @param key 键
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public String rpop(String key) {
        return rpopWithPrefix(this.prefix, key);
    }

    /**
     * 含前缀-移除并返回list的最后一个元素
     *
     * @param prefix 前缀
     * @param key    键
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public String rpopWithPrefix(String prefix, String key) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.rpop(key);
    }

    /**
     * push到list的尾
     *
     * @param key     键
     * @param strings 值
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public Long rpush(String key, String... strings) {
        return rpushWithPrefix(prefix, key, strings);
    }

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
    @Override
    public Long rpushWithPrefix(String prefix, String key, String... strings) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.rpush(key, strings);
    }

    /**
     * 查询list长度
     *
     * @param key 键
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public Long llen(String key) {
        return llenWithPrefix(this.prefix, key);
    }

    /**
     * 含前缀-查询list长度
     *
     * @param prefix 前缀
     * @param key    键
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public Long llenWithPrefix(String prefix, String key) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.llen(key);
    }

    /**
     * 查询list中下标为index的元素
     *
     * @param key   键
     * @param index 索引
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public String lIndex(String key, long index) {
        return lIndexWithPrefix(this.prefix, key, index);
    }

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
    @Override
    public String lIndexWithPrefix(String prefix, String key, long index) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.lindex(key, index);
    }

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
    @Override
    public List<String> lrange(String key, long start, long end) {
        return lrangeWithPrefix(this.prefix, key, start, end);
    }

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
    @Override
    public List<String> lrangeWithPrefix(String prefix, String key, long start, long end) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.lrange(key, start, end);
    }

    /**
     * 设置set的值
     *
     * @param key     键
     * @param members 值
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public Long sadd(String key, String... members) {
        return saddWithPrefix(this.prefix, key, members);
    }

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
    @Override
    public Long saddWithPrefix(String prefix, String key, String... members) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.sadd(key, members);
    }

    /**
     * 设置set的值
     *
     * @param key     键
     * @param members 值
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public Long sadd(String key, byte[]... members) {
        return saddWithPrefix(this.prefix, key, members);
    }

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
    @Override
    public Long saddWithPrefix(String prefix, String key, byte[]... members) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.sadd(key.getBytes(), members);
    }

    /**
     * 删除set中的值
     *
     * @param key     键
     * @param members 值
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public Long srem(String key, String... members) {
        return sremWithPrefix(this.prefix, key, members);
    }

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
    @Override
    public Long sremWithPrefix(String prefix, String key, String... members) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.srem(key, members);
    }

    /**
     * 返回名称为key的set的基数
     *
     * @param key 键
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public Long scard(String key) {
        return scardWithPrefix(this.prefix, key);
    }

    /**
     * 含前缀-返回名称为key的set的基数
     *
     * @param prefix 前缀
     * @param key    键
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public Long scardWithPrefix(String prefix, String key) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.scard(key);
    }

    /**
     * 返回名称为key的set的所有元素
     *
     * @param key 键
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public Set<String> smembers(String key) {
        return smembersWithPrefix(this.prefix, key);
    }

    /**
     * 含前缀-返回名称为key的set的所有元素
     *
     * @param prefix 前缀
     * @param key    键
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public Set<String> smembersWithPrefix(String prefix, String key) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.smembers(key);
    }

    /**
     * 随机返回名称为key的set的一个元素
     *
     * @param key   键
     * @param count 返回的元素数量
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public List<String> srandmember(String key, int count) {
        return srandmemberWithPrefix(this.prefix, key, count);
    }

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
    @Override
    public List<String> srandmemberWithPrefix(String prefix, String key, int count) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.srandmember(key, count);
    }

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
    @Override
    public Long zadd(String key, double score, String member) {
        return zaddWithPrefix(this.prefix, key, score, member);
    }

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
    @Override
    public Long zaddWithPrefix(String prefix, String key, double score, String member) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.zadd(key, score, member);
    }

    /**
     * 删除名称为key的zset中的元素member
     *
     * @param key     键
     * @param members 值
     * @return {@link Long}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public Long zrem(String key, String... members) {
        return zremWithPrefix(this.prefix, key, members);
    }

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
    @Override
    public Long zremWithPrefix(String prefix, String key, String... members) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.zrem(key, members);
    }

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
    @Override
    public Long zremrangeByRank(String key, long start, long stop) {
        return zremrangeByRankWithPrefix(this.prefix, key, start, stop);
    }

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
    @Override
    public Long zremrangeByRankWithPrefix(String prefix, String key, long start, long stop) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.zremrangeByRank(key, start, stop);
    }

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
    @Override
    public Long zremrangeByScore(String key, double min, double max) {
        return zremrangeByScoreWithPrefix(this.prefix, key, min, max);
    }

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
    @Override
    public Long zremrangeByScoreWithPrefix(String prefix, String key, double min, double max) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.zremrangeByScore(key, min, max);
    }

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
    @Override
    public Set<String> zrange(String key, long start, long end) {
        return zrangeWithPrefix(this.prefix, key, start, end);
    }

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
    @Override
    public Set<String> zrangeWithPrefix(String prefix, String key, long start, long end) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.zrange(key, start, end);
    }

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
    @Override
    public Set<String> zrangeByScore(String key, double min, double max) {
        return zrangeByScoreWithPrefix(this.prefix, key, min, max);
    }

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
    @Override
    public Set<String> zrangeByScoreWithPrefix(String prefix, String key, double min, double max) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.zrangeByScore(key, min, max);
    }

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
    @Override
    public Set<String> zrevrange(String key, long start, long end) {
        return zrevrangeWithPrefix(this.prefix, key, start, end);
    }

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
    @Override
    public Set<String> zrevrangeWithPrefix(String prefix, String key, long start, long end) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.zrevrange(key, start, end);
    }

    /**
     * 返回名称为key的zset中元素element的score
     *
     * @param key    键
     * @param member 值
     * @return {@link Double}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public Double zscore(String key, String member) {
        return zscoreWithPrefix(this.prefix, key, member);
    }

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
    @Override
    public Double zscoreWithPrefix(String prefix, String key, String member) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.zscore(key, member);
    }

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
    @Override
    public Double zincrby(String key, double increment, String member) {
        return zincrbyWithPrefix(this.prefix, key, increment, member);
    }

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
    @Override
    public Double zincrbyWithPrefix(String prefix, String key, double increment, String member) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.zincrby(key, increment, member);
    }

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
    @Override
    public Long zcount(String key, double min, double max) {
        return zcountWithPrefix(this.prefix, key, min, max);
    }

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
    @Override
    public Long zcountWithPrefix(String prefix, String key, double min, double max) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.zcount(key, min, max);
    }

    /**
     * jedis信息
     *
     * @return {@link String}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public String info() {
        return jedisCluster.info();
    }

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
    @Override
    public Object eval(String script, List<String> keys, List<String> args) {
        if (CollectionUtil.isNotEmpty(keys)) {
            for (int i = 0; i < keys.size(); i++) {
                keys.set(i, prefix + keys.get(i));
            }
        }
        return jedisCluster.eval(script, keys, args);
    }

    /**
     * 获取key的偏移量
     *
     * @param key    键
     * @param offset 偏移量
     * @return {@link boolean}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public boolean getBit(String key, long offset) {
        return getBitWithPrefix(this.prefix, key, offset);
    }

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
    @Override
    public boolean getBitWithPrefix(String prefix, String key, long offset) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.getBit(key, offset);
    }

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
    @Override
    public boolean setBit(String key, long offset, boolean value) {
        return setBitWithPrefix(this.prefix, key, offset, value);
    }

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
    @Override
    public boolean setBitWithPrefix(String prefix, String key, long offset, boolean value) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.setbit(key, offset, value);
    }

    /**
     * 统计key的偏移量
     *
     * @param key 键
     * @return {@link long}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public long bitCount(String key) {
        return bitCountWithPrefix(this.prefix, key);
    }

    /**
     * 含前缀-统计key的偏移量
     *
     * @param prefix 前缀
     * @param key    键
     * @return {@link long}
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public long bitCountWithPrefix(String prefix, String key) {
        if (prefix != null) {
            key = prefix + key;
        }
        return this.jedisCluster.bitcount(key);
    }

    /**
     * 设置前缀
     *
     * @param prefix 前缀
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    /**
     * 设置最大连接数
     *
     * @param maxTotal 最大连接数
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    /**
     * 设置最大空闲连接数
     *
     * @param maxIdle 最大空闲连接数
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    /**
     * 设置获取连接时的最大等待毫秒数
     *
     * @param maxWaitMills 最大等待毫秒数
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public void setMaxWaitMills(long maxWaitMills) {
        this.maxWaitMillis = maxWaitMills;
    }

    public long getMaxWaitMillis() {
        return maxWaitMillis;
    }

    /**
     * 设置ip
     *
     * @param ip ip
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    /**
     * 设置是否启用心跳检测
     *
     * @param enableHeartbeat 是否启用心跳检测
     * @author luyuhao
     * @since 2021/8/11
     */
    @Override
    public void setEnableHeartbeat(boolean enableHeartbeat) {
    }

    private <T> T deserialize(byte[] value) {
        try {
            return SerializeUtil.deserialize(value);
        } catch (ClassCastException var3) {
            return null;
        }
    }
}
