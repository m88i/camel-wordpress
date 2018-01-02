package org.m88i.camel.component.wordpress.consumer;

import java.util.List;

import org.apache.camel.Processor;
import org.m88i.camel.component.wordpress.WordpressEndpoint;
import org.m88i.camel.component.wordpress.api.model.Post;
import org.m88i.camel.component.wordpress.api.model.PostSearchCriteria;
import org.m88i.camel.component.wordpress.api.service.WordpressServicePosts;
import org.m88i.camel.component.wordpress.proxy.WordpressServiceProvider;

/**
 * Consumer for Posts. Adapter for {@link WordpressServicePosts} read only methods (list and retrieve).
 */
public class WordpressPostConsumer extends AbstractWordpressConsumer {
    
    private WordpressServicePosts servicePosts;

    public WordpressPostConsumer(WordpressEndpoint endpoint, Processor processor) {
        super(endpoint, processor);
        servicePosts = WordpressServiceProvider.getInstance().getService(WordpressServicePosts.class);
    }
    
    @Override
    protected int poll() throws Exception {
        if(this.getConfiguration().getId() == null) {
            return this.pollForPostList();
        } else {
            return this.pollForSingle();
        }
    }
    
    private int pollForPostList() {
        final List<Post> posts = this.servicePosts.list((PostSearchCriteria)getConfiguration().getSearchCriteria());
        posts.stream().forEach(p -> this.process(p));
        return posts.size();
    }
    
    private int pollForSingle() {
        final Post post = this.servicePosts.retrieve(getConfiguration().getId());
        if(post == null) {
            return 0;
        }
        this.process(post);
        return 1;
    }
}
