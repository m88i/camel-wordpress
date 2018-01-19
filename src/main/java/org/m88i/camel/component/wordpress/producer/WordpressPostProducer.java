package org.m88i.camel.component.wordpress.producer;

import org.apache.camel.Exchange;
import org.m88i.camel.component.wordpress.WordpressEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wordpress4j.WordpressServiceProvider;
import org.wordpress4j.model.Post;
import org.wordpress4j.service.WordpressServicePosts;

/**
 * The Wordpress Post producer.
 */
public class WordpressPostProducer extends AbstractWordpressProducer {
    private static final Logger LOG = LoggerFactory.getLogger(WordpressPostProducer.class);
    private WordpressEndpoint endpoint;
    private WordpressServicePosts servicePosts;

    public WordpressPostProducer(WordpressEndpoint endpoint) {
        super(endpoint);
        this.endpoint = endpoint;
        this.servicePosts = WordpressServiceProvider.getInstance().getService(WordpressServicePosts.class);
        if(!WordpressServiceProvider.getInstance().hasAuthentication()) {
            LOG.warn("Wordpress Producer hasn't authentication. This may lead to errors during route execution. Wordpress writing operations need authentication.");
        }
    }

    public void process(Exchange exchange) throws Exception {
        if (this.getConfiguration().getId() == null) {
            exchange.getOut().setBody(this.processInsert(exchange));
        } else {
            if (this.endpoint.getOperationDetail() == null) {
                exchange.getOut().setBody(this.processUpdate(exchange));
            } else {
                exchange.getOut().setBody(this.processDelete(exchange));
            }
        }
    }

    private Post processInsert(Exchange exchange) {
        LOG.debug("Trying to create a new blog post with {}", exchange.getIn().getBody());
        return servicePosts.create(exchange.getIn().getBody(Post.class));
    }

    private Post processUpdate(Exchange exchange) {
        LOG.debug("Trying to update the post {} with id {}", exchange.getIn().getBody(),
                this.getConfiguration().getId());
        return servicePosts.update(this.getConfiguration().getId(), exchange.getIn().getBody(Post.class));
    }

    private Post processDelete(Exchange exchange) {
        LOG.debug("Trying to delete a post with id {}", this.getConfiguration().getId());
        
        if(this.getConfiguration().isForce()) {
            return servicePosts.forceDelete(this.getConfiguration().getId()).getPrevious();
        } else {
            return servicePosts.delete(this.getConfiguration().getId());
        }
    }

}
