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

    private A api;
    
    AbstractWordpressServiceAdapter(final String wordpressUrl) {
        //@formatter:off
        this.api = JAXRSClientFactory.create(wordpressUrl, 
                                              this.getApiType(), 
                                              Collections.singletonList(new JacksonJsonProvider()));
        //@formatter:on
        /*
         * TODO: aggregate a configuration object to customize the JAXRS
         * behavior, eg.: adding handlers or interceptors
         */
        WebClient.getConfig(api).getInInterceptors().add(new LoggingInInterceptor());
        WebClient.getConfig(api).getOutInterceptors().add(new LoggingOutInterceptor());
        LOGGER.info("******* {} API initialized *********", api.getClass().getSimpleName());
    }

    protected abstract Class<A> getApiType();

    protected final A getApi() {
        return api;
    }

}
