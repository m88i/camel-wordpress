package br.com.tecnobiz.camel.component.wordpress;

import java.util.List;

import br.com.tecnobiz.camel.component.wordpress.model.Post;
import br.com.tecnobiz.camel.component.wordpress.model.PostContext;

public interface WordpressServicePosts {
    
    public List<Post> list();
    
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

}
