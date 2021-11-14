package com.darren1112.dptms.common.redis.starter.masterSalve;

import cn.hutool.core.util.RandomUtil;
import com.darren1112.dptms.common.core.util.CollectionUtil;
import com.darren1112.dptms.common.core.util.StringUtil;
import com.darren1112.dptms.common.redis.starter.hint.RedisHintRouteManager;
import com.darren1112.dptms.common.redis.starter.masterSalve.entry.MasterSlavePoolEntry;
import com.darren1112.dptms.common.redis.starter.masterSalve.entry.impl.MasterSlavePoolEntryImpl;
import com.darren1112.dptms.common.redis.starter.masterSalve.state.MasterSlaveStateContext;
import com.darren1112.dptms.common.redis.starter.masterSalve.state.MasterSlaveStateEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Iterator;
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

    public Jedis getReadResource() {
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
        if (null == master || null == name) {
            return;
        }
        ReentrantReadWriteLock.WriteLock lock = masterLock.writeLock();
        try {
            if (lock.tryLock(DEFAULT_LOCK_WAIT_SECOND, TimeUnit.SECONDS)) {
                try {
                    if (master.getName().equals(name)) {
                        loggerSwap(master.getName(), master.getState().getState(), MasterSlaveStateContext.STATE_INVALID.getState());
                        master.setState(MasterSlaveStateContext.STATE_INVALID);
                        master = null;
                    }
                } finally {
                    lock.unlock();
                }
            }
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private void loggerSwap(String name, MasterSlaveStateEnum before, MasterSlaveStateEnum after) {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(":");
        sb.append(before.getStateName());
        sb.append("===>");
        sb.append(after.getStateName());
        LOGGER.info(sb.toString());
    }

    @Override
    public void enlistMaster(String name) {
        if (null == name) {
            return;
        }
        ReentrantReadWriteLock.WriteLock lock = masterLock.writeLock();
        try {
            if (lock.tryLock(DEFAULT_LOCK_WAIT_SECOND, TimeUnit.SECONDS)) {
                try {
                    if (null != master && !master.getName().equals(name)) {
                        loggerSwap(master.getName(), master.getState().getState(), MasterSlaveStateContext.STATE_INVALID.getState());
                        master.setState(MasterSlaveStateContext.STATE_INVALID);
                    }

                    master = allMasterSlavePoolEntryMap.get(name);
                    loggerSwap(master.getName(), master.getState().getState(), MasterSlaveStateContext.STATE_MASTER.getState());

                    master.setState(MasterSlaveStateContext.STATE_MASTER);
                } finally {
                    lock.unlock();
                }
            }
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void delistSlave(String name) {
        if (null == name) {
            return;
        }
        ReentrantReadWriteLock.WriteLock lock = slaveLock.writeLock();
        try {
            if (lock.tryLock(DEFAULT_LOCK_WAIT_SECOND, TimeUnit.SECONDS)) {
                try {
                    Iterator<MasterSlavePoolEntry> iterator = listSlavePoolEntry.iterator();
                    while (iterator.hasNext()) {
                        MasterSlavePoolEntry slave = iterator.next();
                        if (slave.getName().equals(name)) {
                            loggerSwap(slave.getName(), slave.getState().getState(), MasterSlaveStateContext.STATE_INVALID.getState());
                            slave.setState(MasterSlaveStateContext.STATE_INVALID);
                            iterator.remove();
                        }
                    }
                } finally {
                    lock.unlock();
                }
            }
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void enlistSlave(String name) {
        if (null == name) {
            return;
        }
        ReentrantReadWriteLock.WriteLock lock = slaveLock.writeLock();
        try {
            if (lock.tryLock(DEFAULT_LOCK_WAIT_SECOND, TimeUnit.SECONDS)) {
                try {
                    MasterSlavePoolEntry slave = allMasterSlavePoolEntryMap.get(name);
                    loggerSwap(slave.getName(), slave.getState().getState(), MasterSlaveStateContext.STATE_SLAVE.getState());
                    slave.setState(MasterSlaveStateContext.STATE_SLAVE);
                    listSlavePoolEntry.add(slave);
                } finally {
                    lock.unlock();
                }
            }
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public Map<String, MasterSlavePoolEntry> getListSlavePoolEntry() {
        return this.allMasterSlavePoolEntryMap;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public long getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(long maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnableHeartbeat() {
        return enableHeartbeat;
    }

    public void setEnableHeartbeat(boolean enableHeartbeat) {
        this.enableHeartbeat = enableHeartbeat;
    }
}
