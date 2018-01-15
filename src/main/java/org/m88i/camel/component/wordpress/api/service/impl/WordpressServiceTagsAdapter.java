package org.m88i.camel.component.wordpress.api.service.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.m88i.camel.component.wordpress.api.model.Context;
import org.m88i.camel.component.wordpress.api.model.Tag;
import org.m88i.camel.component.wordpress.api.model.TagSearchCriteria;
import org.m88i.camel.component.wordpress.api.service.WordpressServiceTags;
import org.m88i.camel.component.wordpress.api.service.spi.TagsSPI;

public class WordpressServiceTagsAdapter extends AbstractWordpressCrudServiceAdapter<TagsSPI, Tag, TagSearchCriteria> implements WordpressServiceTags {

    public WordpressServiceTagsAdapter(String wordpressUrl, String apiVersion) {
        super(wordpressUrl, apiVersion);
    }

    @Override
    protected Class<TagsSPI> getSpiType() {
        return TagsSPI.class;
    }

    //@formatter:off
    @Override
    public List<Tag> list(TagSearchCriteria criteria) {
        checkNotNull(criteria, "The search criteria must be defined");
        return this.getSpi().list(this.getApiVersion(), 
                             criteria.getContext(), 
                             criteria.getPage(), 
                             criteria.getPerPage(), 
                             criteria.getSearch(), 
                             criteria.getExclude(), 
                             criteria.getInclude(),
                             criteria.getOffset(),
                             criteria.getOrder(), 
                             criteria.getOrderBy(), 
                             criteria.isHideEmpty(), 
                             criteria.getPostId(), 
                             criteria.getSlug());
    }
    //@formatter:on

    @Override
    protected Tag doCreate(Tag object) {
        return getSpi().create(getApiVersion(), object);
    }

    @Override
    protected void doDelete(Integer id, Boolean force) {
        getSpi().delete(getApiVersion(), id, force);
    }

    @Override
    protected Tag doUpdate(Integer id, Tag object) {
        return getSpi().update(getApiVersion(), id, object);
    }

    @Override
    protected Tag doRetrieve(Integer entityID, Context context) {
        return getSpi().retrieve(getApiVersion(), entityID, context);
    }

}
