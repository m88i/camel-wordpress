package org.m88i.camel.component.wordpress.api.service;

import java.util.List;

import org.m88i.camel.component.wordpress.api.model.Context;
import org.m88i.camel.component.wordpress.api.model.Tag;
import org.m88i.camel.component.wordpress.api.model.TagSearchCriteria;

public interface WordpressServiceTags extends WordpressService {

    Tag create(Tag tag);

    void delete(Integer tagId, Boolean force);

    List<Tag> list(TagSearchCriteria criteria);

    Tag retrieve(Integer tagId, Context context);

    Tag update(Integer tagId, Tag tag);

}
