package org.m88i.camel.component.wordpress.api.service;

import java.util.List;

import org.m88i.camel.component.wordpress.api.model.Context;
import org.m88i.camel.component.wordpress.api.model.Tag;
import org.m88i.camel.component.wordpress.api.model.TagSearchCriteria;

public interface WordpressServiceTags extends WordpressService {

    Tag create(Tag tag);

    void delete(int tagId, boolean force);

    List<Tag> list(TagSearchCriteria criteria);

    Tag retrieve(int tagId, Context context);

    Tag update(int tagId, Tag tag);

}
