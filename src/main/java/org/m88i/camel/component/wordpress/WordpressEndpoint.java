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
import org.apache.camel.util.StringHelper;
import org.m88i.camel.component.wordpress.api.model.SearchCriteria;
import org.m88i.camel.component.wordpress.api.service.WordpressService;
import org.m88i.camel.component.wordpress.config.WordpressEndpointConfiguration;
import org.m88i.camel.component.wordpress.proxy.WordpressServiceProvider;
import org.m88i.camel.component.wordpress.proxy.WordpressServiceType;

/**
 * Represents a Wordpress endpoint.
 */
@UriEndpoint(firstVersion = "2.20.1", scheme = "wordpress", title = "Wordpress", syntax = "wordpress:service", consumerClass = WordpressConsumer.class, label = "Wordpress")
public class WordpressEndpoint extends DefaultEndpoint {

    public static final String ENDPOINT_SERVICE_POST = "post";

    @UriPath(description = "The endpoint service. Currently, only the 'post' service is supported.", enums = ENDPOINT_SERVICE_POST)
    @Metadata(required = "true")
    private String service;

    @UriParam
    private WordpressEndpointConfiguration configuration;

    private WordpressService wordpressService;
    private WordpressServiceType serviceType;
    
    public WordpressEndpoint(String uri, WordpressComponent component, WordpressEndpointConfiguration configuration) {
        super(uri, component);
        this.configuration = configuration;
    }

    public WordpressEndpointConfiguration getConfiguration() {
        return configuration;
    }
    
    public WordpressService getWordpressService() {
        return wordpressService;
    }
    
    public String getService() {
        return service;
    }

    public void setService(String method) {
        this.service = method;
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
            
            if(configuration.getSearchCriteria() == null) {
                final SearchCriteria searchCriteria = new SearchCriteria();           
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
        this.configureMethodType();
        WordpressServiceProvider.getInstance().init(configuration.getUrl(), configuration.getApiVersion());
        this.wordpressService = WordpressServiceProvider.getInstance().getService(serviceType.getServiceType());
    }
    
    private void configureMethodType() {
        StringHelper.notEmpty(this.service, "service");
        if(!service.equals(ENDPOINT_SERVICE_POST)) {
            throw new IllegalArgumentException(String.format("Invalid service, supported service type is: %s", ENDPOINT_SERVICE_POST));
        }
        this.serviceType = WordpressServiceType.fromMethodName(service);
    }
    
    public boolean isSingleton() {
        return true;
    }
    
    public WordpressServiceType getServiceType() {
        return serviceType;
    }

}
