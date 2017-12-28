package org.m88i.camel.component.wordpress.api.service;

import java.util.List;

import org.m88i.camel.component.wordpress.api.model.Context;
import org.m88i.camel.component.wordpress.api.model.Post;
import org.m88i.camel.component.wordpress.api.model.PostSearchCriteria;

public interface WordpressServicePosts extends WordpressService {
    
    /**
     * Lists the posts based on a {@link PostSearchCriteria}.
     * @param searchCriteria
     * @return a post list with the matched search criteria
     */
    List<Post> list(PostSearchCriteria searchCriteria);
    
    /**
     * Default endpoint.
     * 
     * @param postId
     * @param context
     * @param password
     * @return
     */
    Post retrieve(Integer postId, Context context, String password);
    
    /**
     * Call to posts without password
     * 
     * @param postId
     * @param context
     * @return
     */
    Post retrieve(Integer postId, Context context);
    
    /**
     * Posts in view mode, without password.
     * 
     * @param postId
     * @return
     */
    Post retrieve(Integer postId);
    
    /**
     * Creates a new post
     * 
     * @param post
     * @return
     */
    Post create(Post post);
    
    /**
     * Updates a post. The {@link Post#getId()} is mandatory
     * 
     * @param post
     * @return
     */
    Post update(Integer id, Post post);
    
    /**
     * Deletes a post.
     * 
     * @param id
     * @param force true to send straight to the heaven
     */
    void delete(Integer id, Boolean force);

}
