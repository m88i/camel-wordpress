package org.m88i.camel.component.wordpress;

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
        Exchange exchange = endpoint.createExchange();
        // @formatter:off
        exchange.getIn().setBody(WordpressMethodHelper
                                 .invokeMethod(endpoint.getWordpressService(), methodType, this.properties));
        // @formatter:on
        try {
            // send message to next processor in the route
            getProcessor().process(exchange);
            return 1; // number of messages polled
        } finally {
            if (exchange.getException() != null) {
                getExceptionHandler().handleException("Error processing exchange", exchange, exchange.getException());
            }
        }
    }
}
