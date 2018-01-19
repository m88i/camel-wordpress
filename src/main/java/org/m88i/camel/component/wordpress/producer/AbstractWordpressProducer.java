package org.m88i.camel.component.wordpress.producer;

import org.apache.camel.impl.DefaultProducer;
import org.m88i.camel.component.wordpress.WordpressEndpoint;
import org.m88i.camel.component.wordpress.config.WordpressEndpointConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractWordpressProducer extends DefaultProducer {

    private WordpressEndpointConfiguration configuration;

    protected static final Logger LOG = LoggerFactory.getLogger(WordpressPostProducer.class);

    public AbstractWordpressProducer(WordpressEndpoint endpoint) {
        super(endpoint);
        this.configuration = endpoint.getConfig();
    }

    public WordpressEndpointConfiguration getConfiguration() {
        return configuration;
    }
    
}
