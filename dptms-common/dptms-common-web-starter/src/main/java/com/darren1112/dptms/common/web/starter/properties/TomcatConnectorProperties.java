package com.darren1112.dptms.common.web.starter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * tomcat连接器配置
 *
 * @author luyuhao
 * @since 2021/5/8 17:12
 */
@ConfigurationProperties(prefix = "dptms.tomcat.connector")
public class TomcatConnectorProperties {

    private String uriEncoding = "UTF-8";

    private boolean useBodyEncodingForUri = true;

    private boolean enableLookups = false;

    private String compression = "on";

    private int compressionMinSize = 2048;

    private String noCompressionUserAgents = "gozilla,traviata";

    private String compressibleMimeType = "text/html,text/html,text/javascript,text/css,text/plain,application/json,application/javascript,application/ecmascript,application/xhtml+xml,application/xml";

    /**
     * 等待超时时间
     */
    private int connectionTimeOut = 30000;

    /**
     * 最大并发线程数
     */
    private int maxThreads = 512;

    /**
     * 初始化线程数量
     */
    private int minSpareThreads = 16;

    /**
     * 保持存活超时时间
     */
    private int keepAliveTimeOut;

    /**
     * 单次keepAlive最大请求数
     */
    private int maxKeepAliveRequests = 300;

    /**
     * 请求等待队列
     */
    private int acceptCount = 35000;

    private boolean disableUploadTimeOut = true;

    public String getUriEncoding() {
        return uriEncoding;
    }

    public void setUriEncoding(String uriEncoding) {
        this.uriEncoding = uriEncoding;
    }

    public boolean isUseBodyEncodingForUri() {
        return useBodyEncodingForUri;
    }

    public void setUseBodyEncodingForUri(boolean useBodyEncodingForUri) {
        this.useBodyEncodingForUri = useBodyEncodingForUri;
    }

    public boolean isEnableLookups() {
        return enableLookups;
    }

    public void setEnableLookups(boolean enableLookups) {
        this.enableLookups = enableLookups;
    }

    public String getCompression() {
        return compression;
    }

    public void setCompression(String compression) {
        this.compression = compression;
    }

    public int getCompressionMinSize() {
        return compressionMinSize;
    }

    public void setCompressionMinSize(int compressionMinSize) {
        this.compressionMinSize = compressionMinSize;
    }

    public String getNoCompressionUserAgents() {
        return noCompressionUserAgents;
    }

    public void setNoCompressionUserAgents(String noCompressionUserAgents) {
        this.noCompressionUserAgents = noCompressionUserAgents;
    }

    public String getCompressibleMimeType() {
        return compressibleMimeType;
    }

    public void setCompressibleMimeType(String compressibleMimeType) {
        this.compressibleMimeType = compressibleMimeType;
    }

    public int getConnectionTimeOut() {
        return connectionTimeOut;
    }

    public void setConnectionTimeOut(int connectionTimeOut) {
        this.connectionTimeOut = connectionTimeOut;
    }

    public int getMaxThreads() {
        return maxThreads;
    }

    public void setMaxThreads(int maxThreads) {
        this.maxThreads = maxThreads;
    }

    public int getMinSpareThreads() {
        return minSpareThreads;
    }

    public void setMinSpareThreads(int minSpareThreads) {
        this.minSpareThreads = minSpareThreads;
    }

    public int getKeepAliveTimeOut() {
        return keepAliveTimeOut;
    }

    public void setKeepAliveTimeOut(int keepAliveTimeOut) {
        this.keepAliveTimeOut = keepAliveTimeOut;
    }

    public int getMaxKeepAliveRequests() {
        return maxKeepAliveRequests;
    }

    public void setMaxKeepAliveRequests(int maxKeepAliveRequests) {
        this.maxKeepAliveRequests = maxKeepAliveRequests;
    }

    public int getAcceptCount() {
        return acceptCount;
    }

    public void setAcceptCount(int acceptCount) {
        this.acceptCount = acceptCount;
    }

    public boolean isDisableUploadTimeOut() {
        return disableUploadTimeOut;
    }

    public void setDisableUploadTimeOut(boolean disableUploadTimeOut) {
        this.disableUploadTimeOut = disableUploadTimeOut;
    }
}
