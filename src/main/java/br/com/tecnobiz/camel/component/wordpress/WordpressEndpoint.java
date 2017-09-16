package br.com.tecnobiz.camel.component.wordpress;

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultEndpoint;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriPath;

/**
 * Represents a Wordpress endpoint.
 */
@UriEndpoint(scheme = "wordpress", title = "Wordpress", syntax="wordpress:name", consumerClass = WordpressConsumer.class, label = "Wordpress")
public class WordpressEndpoint extends DefaultEndpoint {
    @UriPath @Metadata(required = "true")
    private String name;
    @UriParam(defaultValue = "10")
    private int option = 10;

    public WordpressEndpoint() {
    }

    public WordpressEndpoint(String uri, WordpressComponent component) {
        super(uri, component);
    }

    public WordpressEndpoint(String endpointUri) {
        super(endpointUri);
    }

    public Producer createProducer() throws Exception {
        return new WordpressProducer(this);
    }

    public Consumer createConsumer(Processor processor) throws Exception {
        return new WordpressConsumer(this, processor);
    }

    public boolean isSingleton() {
        return true;
    }

    /**
     * Some description of this option, and what it does
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Some description of this option, and what it does
     */
    public void setOption(int option) {
        this.option = option;
    }

    public int getOption() {
        return option;
    }
}
