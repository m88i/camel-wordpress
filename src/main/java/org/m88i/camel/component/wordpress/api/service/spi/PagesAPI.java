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
import org.m88i.camel.component.wordpress.api.model.Page;
import org.m88i.camel.component.wordpress.api.model.PageOrderBy;
import org.m88i.camel.component.wordpress.api.model.PublishableStatus;

/**
 * Describes the Wordpress Pages API.
 * 
 * @see <a href=
 *      "https://developer.wordpress.org/rest-api/reference/pages/">Pages API
 *      Reference</a>
 * @since 0.0.1
 */
@Path("/wp")
public interface PagesAPI extends WordpressAPI<Page> {

    //@formatter:off
    @GET
    @Path("/v{apiVersion}/pages")
    @Produces(MediaType.APPLICATION_JSON)
    List<Page> list(@PathParam("apiVersion") String apiVersion,
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
                         @QueryParam("menu_order") Integer menuOrder,
                         @QueryParam("offset") List<Integer> offset,
                         @QueryParam("order") Order order,
                         @QueryParam("orderby") PageOrderBy orderBy,
                         @QueryParam("parent") Integer parent,
                         @QueryParam("parent_exclude") Integer parentExclude,
                         @QueryParam("slug") List<String> slug,
                         @QueryParam("status") PublishableStatus status,
                         @QueryParam("filter") String filter);
    
    //@formatter:off
    @GET
    @Path("/v{apiVersion}/pages/{pageId}")
    @Produces(MediaType.APPLICATION_JSON)
    Page retrieve(@PathParam("apiVersion") String apiVersion, 
                      @PathParam("pageId") int pageId, 
                      @QueryParam("context") Context context, 
                      @QueryParam("password") String password);
    //@formatter:on
    @POST
    @Path("/v{apiVersion}/pages")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Page create(@PathParam("apiVersion") String apiVersion, Page page);

    //@formatter:off
    @POST
    @Path("/v{apiVersion}/pages/{pageId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Page update(@PathParam("apiVersion") String apiVersion, 
                @PathParam("pageId") int pageId, 
                Page page);

    @DELETE
    @Path("/v{apiVersion}/pages/{pageId}")
    void delete(@PathParam("apiVersion") String apiVersion, 
                @PathParam("pageId") int pageId, 
                @QueryParam("force") boolean force);

}
