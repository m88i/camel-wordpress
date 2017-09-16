package br.com.tecnobiz.camel.component.wordpress;

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Wordpress producer.
 */
public class WordpressProducer extends DefaultProducer {
    private static final Logger LOG = LoggerFactory.getLogger(WordpressProducer.class);
    private WordpressEndpoint endpoint;

    public WordpressProducer(WordpressEndpoint endpoint) {
        super(endpoint);
        this.endpoint = endpoint;
    }

    public void process(Exchange exchange) throws Exception {
        System.out.println(exchange.getIn().getBody());    
    }

}
