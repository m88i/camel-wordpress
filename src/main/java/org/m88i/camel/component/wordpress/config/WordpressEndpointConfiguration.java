package org.m88i.camel.component.wordpress.config;

import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriParams;

@UriParams
public class WordpressEndpointConfiguration extends WordpressConfiguration {

    @UriParam(description = "The entity ID. Should be passed when the operation performed requires a specific entity, e.g. deleting a post")
    private String id;

    @UriParam(description = "The operation name. Required when the component can't figure out the operation by itself.")
    private String operation;
    
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
    
    /**
     * The operation name
     * @return
     */
    public String getOperation() {
        return operation;
    }
    
    public void setOperation(String operation) {
        this.operation = operation;
    }
    

}
