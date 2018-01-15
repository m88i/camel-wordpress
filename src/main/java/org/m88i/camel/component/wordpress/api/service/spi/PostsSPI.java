package org.m88i.camel.component.wordpress.api.service.spi;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.m88i.camel.component.wordpress.api.model.Context;
import org.m88i.camel.component.wordpress.api.model.Order;
import org.m88i.camel.component.wordpress.api.model.Post;
import org.m88i.camel.component.wordpress.api.model.PostOrderBy;
import org.m88i.camel.component.wordpress.api.model.PublishableStatus;

/**
 * Describes the Wordpress Posts API.
 * 
 * @see <a href="https://developer.wordpress.org/rest-api/reference/posts/">Posts API Reference</a>
 * @since 0.0.1
 */
@Path("/wp")
public interface PostsSPI {

    //@formatter:off
    @GET
    @Path("/v{apiVersion}/posts")
    @Produces(MediaType.APPLICATION_JSON)
    List<Post> list(@PathParam("apiVersion") String apiVersion,
                         @QueryParam("context") Context context, 
                         @QueryParam("page") Integer page, 
                         @QueryParam("per_page") Integer perPage, 
                         @QueryParam("search") String search, 
                         @QueryParam("after") Date after, 
                         @QueryParam("author") List<Integer> author,
                         @QueryParam("author_exclude") List<Integer> authorExclude,
                         @QueryParam("before") Date before,
                         @QueryParam("exclude") List<Integer> exclude, 
                         @QueryParam("include") List<Integer> include,
                         @QueryParam("offset") List<Integer> offset,
                         @QueryParam("order") Order order,
                         @QueryParam("orderby") PostOrderBy orderBy,
                         @QueryParam("slug") List<String> slug,
                         @QueryParam("status") PublishableStatus status,
                         @QueryParam("categories") List<String> categories,
                         @QueryParam("categories_exclude") List<String> categoriesExclude,
                         @QueryParam("tags") List<String> tags,
                         @QueryParam("tags_exclude") List<String> tagsExclude,
                         @QueryParam("stick") Boolean stick);
    
    //@formatter:off
    @GET
    @Path("/v{apiVersion}/posts/{postId}")
    @Produces(MediaType.APPLICATION_JSON)
    Post retrieve(@PathParam("apiVersion") String apiVersion, 
                      @PathParam("postId") int postId, 
                      @QueryParam("context") Context context, 
                      @QueryParam("password") String password);
    //@formatter:on
    @POST
    @Path("/v{apiVersion}/posts")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Post create(@PathParam("apiVersion") String apiVersion, Post post);

    //@formatter:off
    @POST
    @Path("/v{apiVersion}/posts/{postId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Post update(@PathParam("apiVersion") String apiVersion, 
                @PathParam("postId") int postId, 
                Post post);

    @DELETE
    @Path("/v{apiVersion}/posts/{postId}")
    void delete(@PathParam("apiVersion") String apiVersion, 
                @PathParam("postId") int postId, 
                @QueryParam("force") boolean force);

}
