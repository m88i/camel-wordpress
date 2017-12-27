package org.m88i.camel.component.wordpress.api.service.impl;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.emptyToNull;

import java.util.Collections;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.m88i.camel.component.wordpress.api.service.WordpressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

abstract class AbstractWordpressServiceAdapter<A> implements WordpressService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractWordpressServiceAdapter.class);

    private A api;
    
    private final String apiVersion;
    
    AbstractWordpressServiceAdapter(final String wordpressUrl, final String apiVersion) {
        checkNotNull(emptyToNull(apiVersion));
        //@formatter:off
        this.api = JAXRSClientFactory.create(wordpressUrl, 
                                              this.getApiType(), 
                                              Collections.singletonList(new JacksonJsonProvider()));
        this.apiVersion = apiVersion;
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

    protected final String getApiVersion() {
        return this.apiVersion;
    }
}
