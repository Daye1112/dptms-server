package com.darren1112.dptms.sdk.starter.redis.hint;

/**
 * 基于暗示的路由规则管理
 *
 * @author darren
 * @since 2021/8/4
 */
public class RedisHintRouteManager implements AutoCloseable {

    private static final ThreadLocal<RedisHintRouteManager> HINT_MANAGER_HOLDER = new ThreadLocal<>();

    private boolean masterRouteOnly;

    public static RedisHintRouteManager getInstance() {
        if (null != HINT_MANAGER_HOLDER.get()) {
            return HINT_MANAGER_HOLDER.get();
        }
        RedisHintRouteManager result = new RedisHintRouteManager();
        HINT_MANAGER_HOLDER.set(result);
        return result;
    }

    public void setMasterRouteOnly() {
        this.masterRouteOnly = true;
    }

    public static boolean isMasterRouteOnly() {
        return null != HINT_MANAGER_HOLDER.get() && HINT_MANAGER_HOLDER.get().masterRouteOnly;
    }

    public static void clear() {
        HINT_MANAGER_HOLDER.remove();
    }

    @Override
    public void close() throws Exception {
        clear();
    }
}
