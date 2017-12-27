package br.com.tecnobiz.camel.component.wordpress;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;
import org.apache.camel.spi.Metadata;
import org.apache.camel.util.IntrospectionSupport;

import br.com.tecnobiz.camel.component.wordpress.config.WordpressConfiguration;
import br.com.tecnobiz.camel.component.wordpress.config.WordpressEndpointConfiguration;

/**
 * Represents the component that manages {@link WordpressEndpoint}.
 */
public class WordpressComponent extends  DefaultComponent {
    
    @Metadata(label = "advanced")
    private WordpressConfiguration configuration;
    
    public WordpressComponent() {
        this(new WordpressConfiguration());
    }
    
    public WordpressComponent(WordpressConfiguration configuration) {
        this.configuration = configuration;
    }

    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {       
        final WordpressEndpointConfiguration endpointConfiguration = this.copyComponentProperties();
        
        WordpressEndpoint endpoint = new WordpressEndpoint(uri, this, endpointConfiguration);
        setProperties(endpoint, parameters);
        
        endpoint.setMethod(remaining);
        endpoint.configureProperties(parameters);
        
        return endpoint;
    }
    
    public WordpressConfiguration getConfiguration() {
        return configuration;
    }
    
    public void setConfiguration(WordpressConfiguration configuration) {
        this.configuration = configuration;
    }
    

    private WordpressEndpointConfiguration copyComponentProperties() throws Exception {
        Map<String, Object> componentProperties = new HashMap<String, Object>();
        IntrospectionSupport.getProperties(configuration, componentProperties, null, false);

        // create endpoint configuration with component properties
        WordpressEndpointConfiguration config = new WordpressEndpointConfiguration();
        IntrospectionSupport.setProperties(config, componentProperties);
        return config;
    }
}
