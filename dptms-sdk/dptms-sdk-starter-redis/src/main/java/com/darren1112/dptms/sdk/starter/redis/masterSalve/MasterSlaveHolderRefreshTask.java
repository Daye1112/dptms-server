package com.darren1112.dptms.sdk.starter.redis.masterSalve;

import java.util.TimerTask;
import java.util.concurrent.Executor;

/**
 * 主从切换监控
 *
 * @author luyuhao
 * @since 2021/8/5
 */
public class MasterSlaveHolderRefreshTask extends TimerTask {

    /**
     * 第一次延迟
     */
    public static final int DELAY_SECONDS = 5;
    /**
     * 时间间隔(大于redis的超时时间)
     */
    public static final int PERIOD_SECONDS = 20;

    private Executor executor;

    private MasterSlaveHolder masterSlaveHolder;

    public MasterSlaveHolderRefreshTask(Executor executor, MasterSlaveHolder masterSlaveHolder) {
        this.executor = executor;
        this.masterSlaveHolder = masterSlaveHolder;
    }

    @Override
    public void run() {
        MasterSlaveHolderRefresh.refresh(executor, masterSlaveHolder);
    }
}
