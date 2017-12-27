package br.com.tecnobiz.camel.component.wordpress.api.service.impl;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.tecnobiz.camel.component.wordpress.api.model.Context;
import br.com.tecnobiz.camel.component.wordpress.api.model.Post;
import br.com.tecnobiz.camel.component.wordpress.api.model.PostSearchCriteria;
import br.com.tecnobiz.camel.component.wordpress.api.service.WordpressServicePosts;
import br.com.tecnobiz.camel.component.wordpress.api.service.spi.PostsAPI;

/**
 * The {@link WordpressServicePosts} implementation. Aggregates the
 * {@link PostsAPI} interface using {@link JAXRSClientFactory} to make the API
 * calls.
 * 
 * @since 0.0.1
 */
public class WordpressServicePostsAdapter extends AbstractWordpressCrudServiceAdapter<PostsAPI, Post> implements WordpressServicePosts {

    private static final Logger LOGGER = LoggerFactory.getLogger(WordpressServicePostsAdapter.class);

    public WordpressServicePostsAdapter(final String wordpressUrl, final String apiVersion) {
        super(wordpressUrl, apiVersion);
    }

    @Override
    protected Class<PostsAPI> getApiType() {
        return PostsAPI.class;
    }

    @Override
    public List<Post> list(PostSearchCriteria criteria) {
        LOGGER.debug("Calling list posts: searchCriteria {}", criteria);
        checkNotNull(criteria, "Please provide a search criteria");
        return getApi().list(this.getApiVersion(), criteria.getContext(), criteria.getPage(), criteria.getPerPage(), criteria.getSearch(), criteria.getAfter(),
                        criteria.getAuthor(), criteria.getAuthorExclude(), criteria.getBefore(), criteria.getExclude(), criteria.getInclude(), criteria.getOffset(),
                        criteria.getOrder(), criteria.getOrderBy(), criteria.getSlug(), criteria.getStatus(), criteria.getCategories(), criteria.getCategoriesExclude(),
                        criteria.getTags(), criteria.getTagsExclude(), criteria.getStick());
    }

    @Override
    public Post retrieve(int postId, Context context, String password) {
        LOGGER.debug("Calling retrievePosts: postId {};  postContext: {}", postId, context);
        checkArgument(postId > 0, "Please provide a non zero post id");
        checkNotNull(context, "Provide a post context");
        return getApi().retrieve(this.getApiVersion(), postId, context, password);
    }

    @Override
    public Post retrievePost(int postId, Context context) {
        return this.retrieve(postId, context, "");
    }

    @Override
    public Post retrievePost(int postId) {
        return this.retrieve(postId, Context.view, "");
    }

}
