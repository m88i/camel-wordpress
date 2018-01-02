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
import org.apache.camel.util.IntrospectionSupport;
import org.m88i.camel.component.wordpress.api.model.SearchCriteria;
import org.m88i.camel.component.wordpress.config.WordpressEndpointConfiguration;
import org.m88i.camel.component.wordpress.consumer.WordpressPostConsumer;
import org.m88i.camel.component.wordpress.proxy.WordpressOperationType;
import org.m88i.camel.component.wordpress.proxy.WordpressServiceProvider;

/**
 * Represents a Wordpress endpoint.
 */
@UriEndpoint(firstVersion = "2.20.1", scheme = "wordpress", title = "Wordpress", syntax = "wordpress:operation", label = "Wordpress")
public class WordpressEndpoint extends DefaultEndpoint {

    public static final String ENDPOINT_SERVICE_POST = "post";

    @UriPath(description = "The endpoint operation. Currently, only the 'post' operation is supported.", enums = ENDPOINT_SERVICE_POST)
    @Metadata(required = "true")
    private String operation;

    @UriParam
    private WordpressEndpointConfiguration configuration;
    
    public WordpressEndpoint(String uri, WordpressComponent component, WordpressEndpointConfiguration configuration) {
        super(uri, component);
        this.configuration = configuration;
    }

    public WordpressEndpointConfiguration getConfiguration() {
        return configuration;
    }
    
    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
    
    public boolean isSingleton() {
        return true;
    }
    
    public Producer createProducer() throws Exception {
        return new WordpressProducer(this);
    }

    public Consumer createConsumer(Processor processor) throws Exception {
        switch (WordpressOperationType.valueOf(operation)) {
        case post:
            return new WordpressPostConsumer(this, processor);
        default:
            break;
        }
        throw new UnsupportedOperationException(String.format("Operation '%s' not supported.", operation));
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
            
            if(configuration.getSearchCriteria() == null) {
                final SearchCriteria searchCriteria = WordpressOperationType.valueOf(operation).getCriteriaType().newInstance();           
                IntrospectionSupport.setProperties(searchCriteria, options, "criteria.");
                configuration.setSearchCriteria(searchCriteria);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
        // validate configuration
        configuration.validate();
        this.initServiceProvider();
    }
    
    private void initServiceProvider() {
        WordpressServiceProvider.getInstance().init(configuration.getUrl(), configuration.getApiVersion());
    }
    
}
