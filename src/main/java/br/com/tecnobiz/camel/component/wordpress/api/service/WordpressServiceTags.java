package br.com.tecnobiz.camel.component.wordpress.api.service;

import java.util.List;

import br.com.tecnobiz.camel.component.wordpress.api.model.Context;
import br.com.tecnobiz.camel.component.wordpress.api.model.Tag;
import br.com.tecnobiz.camel.component.wordpress.api.model.TagSearchCriteria;

public interface WordpressServiceTags extends WordpressService {

    Tag create(Tag tag);

    void delete(int tagId, boolean force);

    List<Tag> list(TagSearchCriteria criteria);

    Tag retrieve(int tagId, Context context);

    Tag update(int tagId, Tag tag);

}
