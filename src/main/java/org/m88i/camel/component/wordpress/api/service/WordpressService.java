package org.m88i.camel.component.wordpress.api.service;

import org.m88i.camel.component.wordpress.api.auth.WordpressAuthentication;

/**
 * Common interface for Wordpress Service adapters.
 */
public interface WordpressService {
    
    /**
     * Sets the Wordpress Authentication Model
     * @param authentication
     */
    void setWordpressAuthentication(WordpressAuthentication authentication);

}
