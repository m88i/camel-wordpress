package org.m88i.camel.component.wordpress;

import java.util.Map;

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultEndpoint;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriPath;
import org.apache.camel.util.EndpointHelper;
import org.apache.camel.util.StringHelper;
import org.m88i.camel.component.wordpress.config.WordpressEndpointConfiguration;

/**
 * Represents a Wordpress endpoint.
 */
@UriEndpoint(firstVersion = "2.20.1", scheme = "wordpress", title = "Wordpress", syntax = "wordpress:method", consumerClass = WordpressConsumer.class, label = "Wordpress")
public class WordpressEndpoint extends DefaultEndpoint {

    public static final String ENDPOINT_METHOD_POST = "post";

    @UriPath(description = "The endpoint method. Currently, only the 'post' method is supported.", enums = "org.m88i.camel.component.wordpress.WordpressMethodType")
    @Metadata(required = "true")
    private String method;

    @UriParam
    private WordpressEndpointConfiguration configuration;

    private WordpressServiceProvider serviceProvider;
    
    public WordpressEndpoint(String uri, WordpressComponent component, WordpressEndpointConfiguration configuration) {
        super(uri, component);
        this.configuration = configuration;
    }

    public WordpressEndpointConfiguration getConfiguration() {
        return configuration;
    }
    
    public WordpressServiceProvider getServiceProvider() {
        return serviceProvider;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
    
    public Producer createProducer() throws Exception {
        return new WordpressProducer(this);
    }

    public Consumer createConsumer(Processor processor) throws Exception {
        return new WordpressConsumer(this, processor);
    }

    @Override
    public void configureProperties(Map<String, Object> options) {
        super.configureProperties(options);

        // set configuration properties first
        try {
            if (configuration == null) {
                configuration = new WordpressEndpointConfiguration();
            }
            EndpointHelper.setReferenceProperties(getCamelContext(), configuration, options);
            EndpointHelper.setProperties(getCamelContext(), configuration, options);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
        // validate configuration
        configuration.validate();
        this.initServiceProvider();
    }
    
    private void initServiceProvider() {
        this.checkMethodSupport();
        this.serviceProvider = WordpressServiceProvider.getInstance();
        this.serviceProvider.init(configuration.getUrl(), configuration.getId());
    }
    
    private void checkMethodSupport() {
        StringHelper.notEmpty(this.method, "method");
        if(!method.equals(ENDPOINT_METHOD_POST)) {
            throw new IllegalArgumentException(String.format("Invalid method, supported method type is: %s", ENDPOINT_METHOD_POST));
        }
    }
    
    public boolean isSingleton() {
        return true;
    }

}
