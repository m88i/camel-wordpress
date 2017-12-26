package br.com.tecnobiz.camel.component.wordpress;

import java.util.Map;

import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;

/**
 * Represents the component that manages {@link WordpressEndpoint}.
 */
public class WordpressComponent extends  DefaultComponent {
    
    public WordpressComponent() {
    }

    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        Endpoint endpoint = new WordpressEndpoint(uri, this);
        setProperties(endpoint, parameters);
        return endpoint;
    }
}
