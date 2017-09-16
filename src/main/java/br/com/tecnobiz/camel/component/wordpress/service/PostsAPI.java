package br.com.tecnobiz.camel.component.wordpress.service;

import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.tecnobiz.camel.component.wordpress.model.Order;
import br.com.tecnobiz.camel.component.wordpress.model.Post;
import br.com.tecnobiz.camel.component.wordpress.model.PostContext;
import br.com.tecnobiz.camel.component.wordpress.model.PostOrderBy;
import br.com.tecnobiz.camel.component.wordpress.model.PostStatus;

/**
 * Describes the Wordpress Posts API
 */
@Path("/")
public interface PostsAPI {

    //@formatter:off
    @GET
    @Path("/v{apiVersion}/posts")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Post> listPosts(@PathParam("apiVersion") String apiVersion,
                         @QueryParam("context") PostContext context, 
                         @QueryParam("page") int page, 
                         @QueryParam("per_page") int perPage, 
                         @QueryParam("search") String search, 
                         @QueryParam("after") Date after, 
                         @QueryParam("author") List<Integer> author,
                         @QueryParam("author_exclude") List<Integer> authorExclude,
                         @QueryParam("before") Date before,
                         @QueryParam("exclude") List<Integer> exclude, 
                         @QueryParam("include") List<Integer> include,
                         @QueryParam("offset") List<Integer> offset,
                         @QueryParam("order") Order order,
                         @QueryParam("order_by") PostOrderBy orderBy,
                         @QueryParam("slug") List<String> slug,
                         @QueryParam("status") PostStatus status,
                         @QueryParam("categories") List<String> categories,
                         @QueryParam("categories_exclude") List<String> categoriesExclude,
                         @QueryParam("tags") List<String> tags,
                         @QueryParam("tags_exclude") List<String> tagsExclude,
                         @QueryParam("stick") Boolean stick);
    
    //@formatter:off
    @GET
    @Path("/v{apiVersion}/posts/{postId}")
    @Produces(MediaType.APPLICATION_JSON)
    Post retrievePost(@PathParam("apiVersion") String apiVersion, 
                      @PathParam("postId") int postId, 
                      @QueryParam("context") PostContext context, 
                      @QueryParam("password") String password);

}
