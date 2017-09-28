package br.com.tecnobiz.camel.component.wordpress.service.impl;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.emptyToNull;

import java.util.List;

import br.com.tecnobiz.camel.component.wordpress.WordpressConstants;
import br.com.tecnobiz.camel.component.wordpress.api.TagsAPI;
import br.com.tecnobiz.camel.component.wordpress.model.Tag;
import br.com.tecnobiz.camel.component.wordpress.model.TagSearchCriteria;
import br.com.tecnobiz.camel.component.wordpress.model.Context;
import br.com.tecnobiz.camel.component.wordpress.service.WordpressServiceTags;

public class WordpressServiceTagsAdapter extends AbstractWordpressCrudServiceAdapter<TagsAPI, Tag> implements WordpressServiceTags {

    public WordpressServiceTagsAdapter(String wordpressUrl) {
        super(wordpressUrl);
    }

    @Override
    protected Class<TagsAPI> getApiType() {
        return TagsAPI.class;
    }

    @Override
    public Tag create(Tag tag) {
        checkNotNull(emptyToNull(tag.getName()), "Name is mandatory");
        return super.create(tag);
    }

    @Override
    public Tag update(int id, Tag tag) {
        checkNotNull(emptyToNull(tag.getName()), "Name is mandatory");
        return super.update(id, tag);
    }

    //@formatter:off
    @Override
    public List<Tag> list(TagSearchCriteria criteria) {
        checkNotNull(criteria, "The search criteria must be defined");
        return this.getApi().list(WordpressConstants.API_VERSION, 
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
    public Tag retrieve(int tagId, Context context) {
        checkArgument(tagId > 0, "Please define a tag");
        return getApi().retrieve(WordpressConstants.API_VERSION, tagId, context);
    }

}
