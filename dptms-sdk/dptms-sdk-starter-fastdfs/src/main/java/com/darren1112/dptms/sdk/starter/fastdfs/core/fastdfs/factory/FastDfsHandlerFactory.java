package com.darren1112.dptms.sdk.starter.fastdfs.core.fastdfs.factory;

import com.darren1112.dptms.sdk.starter.fastdfs.core.fastdfs.factory.handle.FastDfsHandler;
import com.darren1112.dptms.sdk.starter.fastdfs.properties.FastDfsProperties;

/**
 * fastDfs处理器工厂类
 *
 * @author darren
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
     * @author darren
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
