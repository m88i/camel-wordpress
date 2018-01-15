package org.m88i.camel.component.wordpress.api.service;

import org.m88i.camel.component.wordpress.api.model.Context;
import org.m88i.camel.component.wordpress.api.model.Post;
import org.m88i.camel.component.wordpress.api.model.PostSearchCriteria;

public interface WordpressServicePosts extends WordpressCrudService<Post, PostSearchCriteria> {
    
    /**
     * Default endpoint.
     * 
     * @param postId
     * @param context
     * @param password
     * @return
     */
    Post retrieve(Integer postId, Context context, String password);

}
