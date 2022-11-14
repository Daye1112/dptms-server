package com.darren1112.dptms.sdk.starter.redis.masterSalve;

import com.darren1112.dptms.sdk.starter.redis.masterSalve.entry.MasterSlavePoolEntry;

import java.util.Map;
import java.util.concurrent.Executor;

/**
 * 健康检查
 *
 * @author luyuhao
 * @since 2021/8/5
 */
public class MasterSlaveHolderRefresh {

    public static void refresh(Executor executor, MasterSlaveHolder masterSlaveHolder) {
        Map<String, MasterSlavePoolEntry> allMasterSlavePoolEntryMap = masterSlaveHolder.getListSlavePoolEntry();
        if (null == allMasterSlavePoolEntryMap) {
            return;
        }
        for (Map.Entry<String, MasterSlavePoolEntry> masterSlavePoolEntry : allMasterSlavePoolEntryMap.entrySet()) {
            executor.execute(() -> masterSlavePoolEntry.getValue().getHealth().doHeartbeat(masterSlaveHolder));
        }
    }
}
