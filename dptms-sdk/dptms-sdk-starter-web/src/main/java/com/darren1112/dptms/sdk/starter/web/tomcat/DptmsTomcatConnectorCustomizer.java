package com.darren1112.dptms.sdk.starter.web.tomcat;

import com.darren1112.dptms.sdk.starter.web.properties.TomcatConnectorProperties;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.ProtocolHandler;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;

/**
 * 自定义tomcat容器的连接器配置
 *
 * @author darren
 * @since 2021/5/10 9:17
 */
public class DptmsTomcatConnectorCustomizer implements TomcatConnectorCustomizer {

    private TomcatConnectorProperties tomcatConnectorProperties;

    public DptmsTomcatConnectorCustomizer(TomcatConnectorProperties tomcatConnectorProperties) {
        this.tomcatConnectorProperties = tomcatConnectorProperties;
    }

    @Override
    public void customize(Connector connector) {
        if (null == connector) {
            return;
        }
        connector.setURIEncoding(tomcatConnectorProperties.getUriEncoding());
        connector.setUseBodyEncodingForURI(tomcatConnectorProperties.isUseBodyEncodingForUri());
        connector.setEnableLookups(tomcatConnectorProperties.isEnableLookups());
        if (null == connector.getProtocolHandler()) {
            return;
        }
        ProtocolHandler handler = connector.getProtocolHandler();
        if (handler instanceof Http11NioProtocol) {
            Http11NioProtocol protocol = (Http11NioProtocol) handler;
            protocol.setCompression(tomcatConnectorProperties.getCompression());
            protocol.setCompressionMinSize(tomcatConnectorProperties.getCompressionMinSize());
            protocol.setNoCompressionUserAgents(tomcatConnectorProperties.getNoCompressionUserAgents());
            protocol.setCompressibleMimeType(tomcatConnectorProperties.getCompressibleMimeType());
            protocol.setConnectionTimeout(tomcatConnectorProperties.getConnectionTimeOut());
            protocol.setMaxThreads(tomcatConnectorProperties.getMaxThreads());
            protocol.setMinSpareThreads(tomcatConnectorProperties.getMinSpareThreads());
            protocol.setKeepAliveTimeout(tomcatConnectorProperties.getKeepAliveTimeOut());
            protocol.setMaxKeepAliveRequests(tomcatConnectorProperties.getMaxKeepAliveRequests());
            protocol.setAcceptCount(tomcatConnectorProperties.getAcceptCount());
            protocol.setDisableUploadTimeout(tomcatConnectorProperties.isDisableUploadTimeOut());
        }
    }
}
