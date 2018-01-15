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
import org.m88i.camel.component.wordpress.api.service.spi.PagesSPI;
import org.m88i.camel.component.wordpress.api.service.spi.PostsSPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link WordpressServicePosts} implementation. Aggregates the
 * {@link PostsSPI} interface using {@link JAXRSClientFactory} to make the API
 * calls.
 * 
 * @since 0.0.1
 */
public class WordpressServicePagesAdapter extends AbstractWordpressCrudServiceAdapter<PagesSPI, Page, PageSearchCriteria> implements WordpressServicePages {

    public WordpressServicePagesAdapter(String wordpressUrl, String apiVersion) {
        super(wordpressUrl, apiVersion);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(WordpressServicePagesAdapter.class);

    @Override
    protected Class<PagesSPI> getSpiType() {
        return PagesSPI.class;
    }

    //@formatter:off
    @Override
    public List<Page> list(PageSearchCriteria c) {
        LOGGER.debug("Calling list pages: searchCriteria {}", c);
        checkNotNull(c, "Please provide a search criteria");
        return getSpi().list(this.getApiVersion(), 
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
        return getSpi().retrieve(this.getApiVersion(), pageId, context, password);
    }

    @Override
    protected Page doCreate(Page object) {
        return getSpi().create(getApiVersion(), object);
    }

    @Override
    protected void doDelete(Integer id, Boolean force) {
        getSpi().delete(getApiVersion(), id, force);
    }

    @Override
    protected Page doUpdate(Integer id, Page object) {
        return getSpi().update(getApiVersion(), id, object);
    }

    @Override
    protected Page doRetrieve(Integer entityID, Context context) {
        return getSpi().retrieve(getApiVersion(), entityID, context, null);
    }

}
