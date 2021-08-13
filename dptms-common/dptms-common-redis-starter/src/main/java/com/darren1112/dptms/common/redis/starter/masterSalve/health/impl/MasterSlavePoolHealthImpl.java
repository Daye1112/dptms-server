package com.darren1112.dptms.common.redis.starter.masterSalve.health.impl;

import com.darren1112.dptms.common.redis.starter.core.RedisConstants;
import com.darren1112.dptms.common.redis.starter.core.RedisInfoToPropertiesConvert;
import com.darren1112.dptms.common.redis.starter.masterSalve.MasterSlaveOperationCallback;
import com.darren1112.dptms.common.redis.starter.masterSalve.entry.MasterSlavePoolEntry;
import com.darren1112.dptms.common.redis.starter.masterSalve.health.MasterSlavePoolHealth;
import com.darren1112.dptms.common.redis.starter.masterSalve.state.MasterSlaveStateEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.Properties;

/**
 * @author luyuhao
 * @since 2021/8/6
 */
public class MasterSlavePoolHealthImpl implements MasterSlavePoolHealth {

    private static final Logger LOGGER = LoggerFactory.getLogger(MasterSlavePoolHealthImpl.class);

    private static final int HEALTH_RETRY_COUNT = 3;

    private int currentCount = 0;

    private MasterSlavePoolEntry masterSlavePoolEntry;

    public MasterSlavePoolHealthImpl(MasterSlavePoolEntry masterSlavePoolEntry) {
        this.masterSlavePoolEntry = masterSlavePoolEntry;
    }

    @Override
    public void doHeartbeat(MasterSlaveOperationCallback callback) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("health check {}", masterSlavePoolEntry.getName());
        }

        currentCount++;

        MasterSlaveStateEnum remoteStatus = getServerStatus();

        if (MasterSlaveStateEnum.INVALID != remoteStatus) {
            if (MasterSlaveStateEnum.MASTER.equals(remoteStatus)) {
                masterSlavePoolEntry.getState().doMaster(masterSlavePoolEntry, callback);
            } else if (MasterSlaveStateEnum.SLAVE.equals(remoteStatus)) {
                masterSlavePoolEntry.getState().doSlave(masterSlavePoolEntry, callback);
            }
            currentCount = 0;
        } else {
            if (currentCount >= HEALTH_RETRY_COUNT) {
                masterSlavePoolEntry.getState().doInvalid(masterSlavePoolEntry, callback);
                currentCount = 0;
            }
        }
    }

    private MasterSlaveStateEnum getServerStatus() {
        try (Jedis client = masterSlavePoolEntry.getResource()) {
            String replication = client.info("Replication");
            Properties properties = RedisInfoToPropertiesConvert.convert(replication);
            String role = properties.getProperty(RedisConstants.INFO_ROLE);
            if (RedisConstants.INFO_ROLE_MASTER.equals(role)) {
                return MasterSlaveStateEnum.MASTER;
            } else if (RedisConstants.INFO_ROLE_SLAVE.equals(role)) {
                return MasterSlaveStateEnum.SLAVE;
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return MasterSlaveStateEnum.INVALID;
    }
}
