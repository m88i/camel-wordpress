package br.com.tecnobiz.camel.component.wordpress.config;

import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriParams;

@UriParams
public class WordpressEndpointConfiguration extends WordpressConfiguration {

    @UriParam(description = "The entity ID")
    private String id;

    public WordpressEndpointConfiguration() {

    }

    /**
     * The entity id
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    

}
