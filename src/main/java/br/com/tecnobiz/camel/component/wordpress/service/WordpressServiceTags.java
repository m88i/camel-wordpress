package br.com.tecnobiz.camel.component.wordpress.service;

import java.util.List;

import br.com.tecnobiz.camel.component.wordpress.model.Context;
import br.com.tecnobiz.camel.component.wordpress.model.Tag;
import br.com.tecnobiz.camel.component.wordpress.model.TagSearchCriteria;

public interface WordpressServiceTags {

    Tag create(Tag tag);

    void delete(int tagId, boolean force);

    List<Tag> list(TagSearchCriteria criteria);

    Tag retrieve(int tagId, Context context);

    Tag update(int tagId, Tag tag);

}
