package com.darren1112.dptms.common.fastdfs.starter.core.fastdfs.factory;

import com.darren1112.dptms.common.fastdfs.starter.core.fastdfs.factory.handle.FastDfsHandler;
import com.darren1112.dptms.common.fastdfs.starter.properties.FastDfsProperties;

/**
 * fastDfs处理器工厂类
 *
 * @author luyuhao
 * @since 2021/12/7
 */
public class FastDfsHandlerFactory {

    private FastDfsProperties fastDfsProperties;

    private FastDfsHandler retryFastDfsHandler;

    private FastDfsHandler simpleFastDfsHandler;

    public FastDfsHandlerFactory(FastDfsProperties fastDfsProperties,
                                 FastDfsHandler retryFastDfsHandler,
                                 FastDfsHandler simpleFastDfsHandler) {
        this.fastDfsProperties = fastDfsProperties;
        this.retryFastDfsHandler = retryFastDfsHandler;
        this.simpleFastDfsHandler = simpleFastDfsHandler;
    }


    /**
     * 创建fastDfs处理器
     *
     * @return {@link FastDfsHandler}
     * @author luyuhao
     * @since 2021/12/7
     */
    public FastDfsHandler create() {
        if (fastDfsProperties.getRetryTimes() > 1) {
            return simpleFastDfsHandler;
        } else {
            return retryFastDfsHandler;
        }
    }
}
