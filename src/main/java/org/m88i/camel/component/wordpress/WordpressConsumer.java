package org.m88i.camel.component.wordpress;

import java.util.Collection;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.impl.ScheduledPollConsumer;
import org.m88i.camel.component.wordpress.proxy.WordpressMethodHelper;
import org.m88i.camel.component.wordpress.proxy.WordpressServiceMethodType;

/**
 * The Wordpress consumer.
 */
public class WordpressConsumer extends ScheduledPollConsumer {
    private final WordpressEndpoint endpoint;
    private final WordpressServiceMethodType methodType;
    private final Map<String, Object> properties;

    public WordpressConsumer(WordpressEndpoint endpoint, Processor processor) {
        super(endpoint, processor);
        this.endpoint = endpoint;
        this.methodType = WordpressMethodHelper.discoverConsumerMethodCall(endpoint.getServiceType(), endpoint.getConfiguration());
        this.properties = endpoint.getConfiguration().asMap();
    }

    @Override
    public boolean isGreedy() {
        return false;
    }

    @Override
    protected int poll() throws Exception {       
        try {
            // @formatter:off
            final Object result = WordpressMethodHelper
                .invokeMethod(endpoint.getWordpressService(), methodType, this.properties);
            if(result instanceof Collection) {
                for (Object r : (Collection<?>)result) {
                    this.process(r);
                }
                return ((Collection<?>)result).size();
            } else {
                this.process(result);
                return 1; // number of messages polled
            }
            // @formatter:on
        } catch(Exception e) {
            getExceptionHandler().handleException("Error calling inner Wordpress service", e);
            return 0;
        }
    }
    
    protected void process(Object body) {
        Exchange exchange = endpoint.createExchange();
        try {
            exchange.getIn().setBody(body);
            getProcessor().process(exchange);
        } catch(Exception e) {
            if (exchange.getException() != null) {
                getExceptionHandler().handleException("Error processing exchange", exchange, exchange.getException());
            }  
        }
    }
}
