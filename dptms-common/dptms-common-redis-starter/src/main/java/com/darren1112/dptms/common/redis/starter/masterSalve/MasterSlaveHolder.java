package com.darren1112.dptms.common.redis.starter.masterSalve;

import cn.hutool.core.util.RandomUtil;
import com.darren1112.dptms.common.core.util.CollectionUtil;
import com.darren1112.dptms.common.core.util.StringUtil;
import com.darren1112.dptms.common.redis.starter.hint.RedisHintRouteManager;
import com.darren1112.dptms.common.redis.starter.masterSalve.entry.MasterSlavePoolEntry;
import com.darren1112.dptms.common.redis.starter.masterSalve.entry.impl.MasterSlavePoolEntryImpl;
import com.darren1112.dptms.common.redis.starter.masterSalve.state.MasterSlaveStateContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 主从切换持有者
 * MasterSlaveHolderRefreshTask -> MasterSlaveHolderRefresh
 * MasterSlaveHolderRefresh -> 并发调用All MasterSlavePoolEntry的MasterSlavePoolHealth
 * MasterSlavePoolHealth解析远程服务器的redis状态，并交由对应的MasterSlavePoolEntry的MasterSlaveState状态对象进行操作
 * MasterSlaveState根据状态变更调用MasterSlavePoolEntry的回调接口MasterSlaveOperationCallback进行操作
 *
 * @author luyuhao
 * @since 2021/8/6
 */
public class MasterSlaveHolder implements MasterSlaveOperationCallback {

    private static final Logger LOGGER = LoggerFactory.getLogger(MasterSlaveHolder.class);

    private static final int DEFAULT_LOCK_WAIT_SECOND = 3;

    private String prefix;
    private int maxTotal;
    private int maxIdle;
    private long maxWaitMillis;
    private String ip;
    private String password;
    private boolean enableHeartbeat = true;

    private MasterSlavePoolEntry master;

    private ReentrantReadWriteLock masterLock = new ReentrantReadWriteLock();

    private List<MasterSlavePoolEntry> listSlavePoolEntry = new ArrayList<>();

    private ReentrantReadWriteLock slaveLock = new ReentrantReadWriteLock();

    private Map<String, MasterSlavePoolEntry> allMasterSlavePoolEntryMap = new ConcurrentHashMap<>();

    private ScheduledExecutorService scheduler;

    private Executor executor = Executors.newCachedThreadPool();

    public MasterSlaveHolder() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
    }

    private void openScheduledTasks() {
        scheduler.scheduleAtFixedRate(new MasterSlaveHolderRefreshTask(executor, this), MasterSlaveHolderRefreshTask.DELAY_SECONDS, MasterSlaveHolderRefreshTask.PERIOD_SECONDS, TimeUnit.SECONDS);
    }

    private void setMaster(String host, int port, String password, int maxTotal, int maxIdle, long maxWaitMillis) {
        MasterSlavePoolEntry newObject = new MasterSlavePoolEntryImpl(MasterSlaveStateContext.STATE_MASTER, host, port, password, maxTotal, maxIdle, maxWaitMillis);
        this.master = newObject;
        allMasterSlavePoolEntryMap.put(newObject.getName(), newObject);
    }

    private void addSlave(String host, int port, String password, int maxTotal, int maxIdle, long maxWaitMillis) {
        MasterSlavePoolEntry newObject = new MasterSlavePoolEntryImpl(MasterSlaveStateContext.STATE_SLAVE, host, port, password, maxTotal, maxIdle, maxWaitMillis);
        this.listSlavePoolEntry.add(newObject);
        allMasterSlavePoolEntryMap.put(newObject.getName(), newObject);
    }

    public void init() {
        if (StringUtil.isBlank(ip)) {
            LOGGER.error("redis init error, ip is null");
            return;
        }
        try {
            String[] ips = ip.split(",");

            this.setMaster(resolveHost(ips[0]), resolvePort(ips[0]), password, maxTotal, maxIdle, maxWaitMillis);

            if (ips.length > 1) {
                for (int i = 1; i < ips.length; i++) {
                    this.addSlave(resolveHost(ips[i]), resolvePort(ips[i]), password, maxTotal, maxIdle, maxWaitMillis);
                }
            }

            if (enableHeartbeat) {
                this.openScheduledTasks();
            }
        } catch (Exception e) {
            LOGGER.error("redis init error " + ip);
        }
    }

    public void close() {
        if (enableHeartbeat && this.scheduler != null) {
            scheduler.shutdownNow();
        }
        if (CollectionUtil.isNotEmpty(allMasterSlavePoolEntryMap)) {
            for (MasterSlavePoolEntry masterSlavePoolEntry : allMasterSlavePoolEntryMap.values()) {
                if (masterSlavePoolEntry != null) {
                    masterSlavePoolEntry.close();
                }
            }
        }
    }

    private int resolvePort(String ips) {
        String[] uri = ips.split(":");
        if (uri.length > 1) {
            return Integer.valueOf(uri[1]);
        }
        return 80;
    }

    private String resolveHost(String ips) {
        String[] uri = ips.split(":");
        if (uri.length > 0) {
            return uri[0];
        }
        return null;
    }

    public Jedis getWriteResource() {
        ReentrantReadWriteLock.ReadLock lock = masterLock.readLock();
        if (lock.tryLock()) {
            try {
                return master.getResource();
            } finally {
                lock.unlock();
            }
        }
        return null;
    }

    public Jedis getReadResorce() {
        ReentrantReadWriteLock.ReadLock lock = slaveLock.readLock();
        if (lock.tryLock()) {
            try {
                int size = listSlavePoolEntry.size();
                if (size == 0) {
                    return getWriteResource();
                }
                if (RedisHintRouteManager.isMasterRouteOnly()) {
                    return getWriteResource();
                }
                if (size == 1) {
                    return listSlavePoolEntry.get(0).getResource();
                }
                int index = RandomUtil.randomInt(0, size - 1);
                return listSlavePoolEntry.get(index).getResource();
            } finally {
                lock.unlock();
            }
        }
        return null;
    }

    @Override
    public void delistMaster(String name) {

    }

    @Override
    public void enlistMaster(String name) {

    }

    @Override
    public void delistSalve(String name) {

    }

    @Override
    public void enlistSalve(String name) {

    }

    public Map<String, MasterSlavePoolEntry> getListSlavePoolEntry() {
        return this.allMasterSlavePoolEntryMap;
    }
}
