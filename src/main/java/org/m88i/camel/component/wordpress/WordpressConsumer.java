package org.m88i.camel.component.wordpress;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.impl.ScheduledPollConsumer;

/**
 * The Wordpress consumer.
 */
public class WordpressConsumer extends ScheduledPollConsumer {
    private final WordpressEndpoint endpoint;

    public WordpressConsumer(WordpressEndpoint endpoint, Processor processor) {
        super(endpoint, processor);
        this.endpoint = endpoint;
    }

    
    
    @Override
    protected int poll() throws Exception {
        Exchange exchange = endpoint.createExchange();
        // create a message body

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
