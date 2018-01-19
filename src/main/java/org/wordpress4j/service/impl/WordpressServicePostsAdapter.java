package org.wordpress4j.service.impl;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wordpress4j.model.Context;
import org.wordpress4j.model.Post;
import org.wordpress4j.model.PostSearchCriteria;
import org.wordpress4j.service.WordpressServicePosts;
import org.wordpress4j.service.spi.PostsSPI;

/**
 * The {@link WordpressServicePosts} implementation. Aggregates the
 * {@link PostsSPI} interface using {@link JAXRSClientFactory} to make the API
 * calls.
 * 
 * @since 0.0.1
 */
public class WordpressServicePostsAdapter extends AbstractWordpressCrudServiceAdapter<PostsSPI, Post, PostSearchCriteria> implements WordpressServicePosts {

    private static final Logger LOGGER = LoggerFactory.getLogger(WordpressServicePostsAdapter.class);

    public WordpressServicePostsAdapter(final String wordpressUrl, final String apiVersion) {
        super(wordpressUrl, apiVersion);
    }

    @Override
    protected Class<PostsSPI> getSpiType() {
        return PostsSPI.class;
    }

    @Override
    public List<Post> list(PostSearchCriteria criteria) {
        LOGGER.debug("Calling list posts: searchCriteria {}", criteria);
        checkNotNull(criteria, "Please provide a search criteria");
        return getSpi().list(this.getApiVersion(), criteria.getContext(), criteria.getPage(), criteria.getPerPage(), criteria.getSearch(), criteria.getAfter(),
                        criteria.getAuthor(), criteria.getAuthorExclude(), criteria.getBefore(), criteria.getExclude(), criteria.getInclude(), criteria.getOffset(),
                        criteria.getOrder(), criteria.getOrderBy(), criteria.getSlug(), criteria.getStatus(), criteria.getCategories(), criteria.getCategoriesExclude(),
                        criteria.getTags(), criteria.getTagsExclude(), criteria.getStick());
    }

    @Override
    public Post retrieve(Integer postId, Context context, String password) {
        LOGGER.debug("Calling retrievePosts: postId {};  postContext: {}", postId, context);
        checkArgument(postId > 0, "Please provide a non zero post id");
        checkNotNull(context, "Provide a post context");
        return getSpi().retrieve(this.getApiVersion(), postId, context, password);
    }

    @Override
    protected Post doRetrieve(Integer postId, Context context) {
        return this.retrieve(postId, context, "");
    }

    @Override
    public Post retrieve(Integer postId) {
        return this.retrieve(postId, Context.view, "");
    }

    @Override
    protected Post doCreate(Post object) {
        return getSpi().create(this.getApiVersion(), object);
    }

    @Override
    protected void doDelete(Integer id, Boolean force) {
        getSpi().delete(getApiVersion(), id, force);
    }

    @Override
    protected Post doUpdate(Integer id, Post object) {
        return getSpi().update(getApiVersion(), id, object);
    }

}
