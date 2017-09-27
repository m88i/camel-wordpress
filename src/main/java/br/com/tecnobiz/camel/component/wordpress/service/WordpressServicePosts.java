package br.com.tecnobiz.camel.component.wordpress.service;

import java.util.List;

import br.com.tecnobiz.camel.component.wordpress.model.Post;
import br.com.tecnobiz.camel.component.wordpress.model.Context;
import br.com.tecnobiz.camel.component.wordpress.model.PostSearchCriteria;

public interface WordpressServicePosts {
    
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
    Post retrievePost(int postId, Context context, String password);
    
    /**
     * Call to posts without password
     * 
     * @param postId
     * @param context
     * @return
     */
    Post retrievePost(int postId, Context context);
    
    /**
     * Posts in view mode, without password.
     * 
     * @param postId
     * @return
     */
    Post retrievePost(int postId);
    
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
    Post update(Post post);
    
    /**
     * Deletes a post.
     * 
     * @param id
     * @param force true to send straight to the heaven
     */
    void delete(int id, boolean force);

}
