package br.com.tecnobiz.camel.component.wordpress.service;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

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
import br.com.tecnobiz.camel.component.wordpress.model.PostSearchCriteria;

/**
 * The {@link WordpressServicePosts} implementation. Aggregates the
 * {@link PostsAPI} interface using {@link JAXRSClientFactory} to make the API
 * calls.
 * 
 * @since 0.0.1
 */
public class WordpressServicePostsAdapter implements WordpressServicePosts {

    private static final Logger LOGGER = LoggerFactory.getLogger(WordpressServicePostsAdapter.class);

    private PostsAPI api;

    public WordpressServicePostsAdapter(final String wordpressUrl) {
        this.api = JAXRSClientFactory.create(wordpressUrl, PostsAPI.class, Collections.singletonList(new JacksonJsonProvider()));
        /*
         * TODO: aggregate a configuration object to customize the JAXRS
         * behavior, eg.: adding handlers or interceptors
         */
        WebClient.getConfig(this.api).getInInterceptors().add(new LoggingInInterceptor());
        WebClient.getConfig(this.api).getOutInterceptors().add(new LoggingOutInterceptor());
        LOGGER.info("******* Posts API initialized *********");
    }

    @Override
    public List<Post> list(PostSearchCriteria criteria) {
        LOGGER.debug("Calling list posts: searchCriteria {}", criteria);
        checkNotNull(criteria, "Please provide a search criteria");
        return api.listPosts(WordpressConstants.API_VERSION, criteria.getContext(), criteria.getPage(), criteria.getPerPage(), criteria.getSearch(), criteria.getAfter(),
                             criteria.getAuthor(), criteria.getAuthorExclude(), criteria.getBefore(), criteria.getExclude(), criteria.getInclude(), criteria.getOffset(),
                             criteria.getOrder(), criteria.getOrderBy(), criteria.getSlug(), criteria.getStatus(), criteria.getCategories(), criteria.getCategoriesExclude(),
                             criteria.getTags(), criteria.getTagsExclude(), criteria.getStick());
    }

    @Override
    public Post retrievePost(int postId, PostContext context, String password) {
        LOGGER.debug("Calling retrievePosts: postId {};  postContext: {}", postId, context);
        checkArgument(postId > 0, "Please provide a non zero post id");
        checkNotNull(context, "Provide a post context");
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

    @Override
    public Post create(Post post) {
        checkNotNull(post, "Please define a post to create");
        return api.create(WordpressConstants.API_VERSION, post);
    }

    @Override
    public Post update(Post post) {
        checkNotNull(post, "Please define a post to update");
        checkArgument(post.getId() > 0, "The post id is mandatory");
        return api.update(WordpressConstants.API_VERSION, post.getId(), post);
    }

    @Override
    public void delete(int id, boolean force) {
        checkArgument(id > 0, "The post id is mandatory");
        api.delete(WordpressConstants.API_VERSION, id, force);
    }

}
