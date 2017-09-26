package br.com.tecnobiz.camel.component.wordpress;

import java.util.List;

import br.com.tecnobiz.camel.component.wordpress.model.Post;
import br.com.tecnobiz.camel.component.wordpress.model.PostContext;
import br.com.tecnobiz.camel.component.wordpress.model.PostSearchCriteria;

public interface WordpressServicePosts {
    
    /**
     * Lists the posts based on a {@link PostSearchCriteria}.
     * @param searchCriteria
     * @return a post list with the matched search criteria
     */
    public List<Post> list(PostSearchCriteria searchCriteria);
    
    /**
     * Default endpoint.
     * 
     * @param postId
     * @param context
     * @param password
     * @return
     */
    public Post retrievePost(int postId, PostContext context, String password);
    
    /**
     * Call to posts without password
     * 
     * @param postId
     * @param context
     * @return
     */
    public Post retrievePost(int postId, PostContext context);
    
    /**
     * Posts in view mode, without password.
     * 
     * @param postId
     * @return
     */
    public Post retrievePost(int postId);
    
    /**
     * Creates a new post
     * 
     * @param post
     * @return
     */
    public Post create(Post post);
    
    /**
     * Updates a post. The {@link Post#getId()} is mandatory
     * 
     * @param post
     * @return
     */
    public Post update(Post post);
    
    /**
     * Deletes a post.
     * 
     * @param id
     * @param force true to send straight to the heaven
     */
    public void delete(int id, boolean force);

}
