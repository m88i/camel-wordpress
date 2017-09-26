package br.com.tecnobiz.camel.component.wordpress.service.impl;

import java.util.Collections;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

abstract class AbstractWordpressServiceAdapter<A> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractWordpressServiceAdapter.class);

    AbstractWordpressServiceAdapter(final String wordpressUrl) {
        this.setApi(JAXRSClientFactory.create(wordpressUrl, 
                                                 this.getType(), 
                                                 Collections.singletonList(new JacksonJsonProvider())));
        /*
         * TODO: aggregate a configuration object to customize the JAXRS
         * behavior, eg.: adding handlers or interceptors
         */
        WebClient.getConfig(this.getApi()).getInInterceptors().add(new LoggingInInterceptor());
        WebClient.getConfig(this.getApi()).getOutInterceptors().add(new LoggingOutInterceptor());
        LOGGER.info("******* {} API initialized *********", this.getApi().getClass().getSimpleName());

    }
    
    protected abstract Class<A> getType();
    
    protected abstract A getApi();
    
    protected abstract void setApi(A api);
    
}
