package com.darren1112.dptms.common.redis.starter.masterSalve;

/**
 * 主从操作回调接口
 *
 * @author luyuhao
 * @since 2021/8/4
 */
public interface MasterSlaveOperationCallback {

    /**
     * 从master中移除，状态变更为invalid
     *
     * @param name host:port
     * @author luyuhao
     * @since 2021/8/4
     */
    void delistMaster(String name);

    /**
     * 加入到master中，状态变更为master
     *
     * @param name host:port
     * @author luyuhao
     * @since 2021/8/4
     */
    void enlistMaster(String name);

    /**
     * 从salve中移除，状态变更为invalid
     *
     * @param name host:port
     * @author luyuhao
     * @since 2021/8/4
     */
    void delistSlave(String name);

    /**
     * 加入到salve中，状态变更为master
     *
     * @param name host:port
     * @author luyuhao
     * @since 2021/8/4
     */
    void enlistSlave(String name);
}
