package org.m88i.camel.component.wordpress.api.service.impl;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.m88i.camel.component.wordpress.api.model.Context;
import org.m88i.camel.component.wordpress.api.model.Page;
import org.m88i.camel.component.wordpress.api.model.PageSearchCriteria;
import org.m88i.camel.component.wordpress.api.service.WordpressServicePages;
import org.m88i.camel.component.wordpress.api.service.WordpressServicePosts;
import org.m88i.camel.component.wordpress.api.service.spi.PagesAPI;
import org.m88i.camel.component.wordpress.api.service.spi.PostsAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link WordpressServicePosts} implementation. Aggregates the
 * {@link PostsAPI} interface using {@link JAXRSClientFactory} to make the API
 * calls.
 * 
 * @since 0.0.1
 */
public class WordpressServicePagesAdapter extends AbstractWordpressCrudServiceAdapter<PagesAPI, Page> implements WordpressServicePages {

    public WordpressServicePagesAdapter(String wordpressUrl, String apiVersion) {
        super(wordpressUrl, apiVersion);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(WordpressServicePagesAdapter.class);

    @Override
    protected Class<PagesAPI> getApiType() {
        return PagesAPI.class;
    }

    //@formatter:off
    @Override
    public List<Page> list(PageSearchCriteria c) {
        LOGGER.debug("Calling list pages: searchCriteria {}", c);
        checkNotNull(c, "Please provide a search criteria");
        return getApi().list(this.getApiVersion(), 
                        c.getContext(), 
                        c.getPage(), 
                        c.getPerPage(), 
                        c.getSearch(), 
                        c.getAfter(), 
                        c.getAuthor(), 
                        c.getAuthorExclude(), 
                        c.getBefore(), 
                        c.getExclude(), 
                        c.getInclude(), 
                        c.getMenuOrder(), 
                        c.getOffset(), 
                        c.getOrder(), 
                        c.getOrderBy(),
                        c.getParent(), 
                        c.getParentExclude(), 
                        c.getSlug(), 
                        c.getStatus(), 
                        c.getFilter());
    }
    //@formatter:on

    @Override
    public Page retrieve(Integer pageId, Context context, String password) {
        LOGGER.debug("Calling retrieve: postId {};  context: {}", pageId, context);
        checkArgument(pageId > 0, "Please provide a non zero post id");
        return getApi().retrieve(this.getApiVersion(), pageId, context, password);
    }

}
