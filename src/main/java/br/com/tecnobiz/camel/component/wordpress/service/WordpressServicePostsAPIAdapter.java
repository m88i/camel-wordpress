package br.com.tecnobiz.camel.component.wordpress.service;

import java.util.Collections;
import java.util.List;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import br.com.tecnobiz.camel.component.wordpress.WordpressConstants;
import br.com.tecnobiz.camel.component.wordpress.WordpressServicePosts;
import br.com.tecnobiz.camel.component.wordpress.model.Post;
import br.com.tecnobiz.camel.component.wordpress.model.PostContext;

public class WordpressServicePostsAPIAdapter implements WordpressServicePosts {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(WordpressServicePostsAPIAdapter.class);

    private PostsAPI api;

    public WordpressServicePostsAPIAdapter(final String wordpressUrl) {
        this.api = JAXRSClientFactory.create(wordpressUrl, PostsAPI.class, Collections.singletonList(new JacksonJsonProvider()));
        WebClient.getConfig(this.api).getInInterceptors().add(new LoggingInInterceptor());
        WebClient.getConfig(this.api).getOutInterceptors().add(new LoggingOutInterceptor());
        LOGGER.info("******* Posts API initialized *********");
    }

    @Override
    public List<Post> list() {
        return null;
    }

    @Override
    public Post retrievePost(int postId, PostContext context, String password) {
        LOGGER.debug("Calling retrievePosts: postId {};  postContext: {}", postId, context);
        return api.retrievePost(WordpressConstants.API_VERSION, postId, context, password);
    }
    
    @Override
    public Post retrievePost(int postId, PostContext context) {
        return this.retrievePost(postId, context, "");
    }
    
    @Override
    public Post retrievePost(int postId) {
        return this.retrievePost(postId, PostContext.view, "");
    }

}
