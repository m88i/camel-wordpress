package org.m88i.camel.component.wordpress.api.service;

import java.util.List;

import org.m88i.camel.component.wordpress.api.model.Comment;
import org.m88i.camel.component.wordpress.api.model.CommentSearchCriteria;
import org.m88i.camel.component.wordpress.api.model.Context;

public interface WordpressServiceComments extends WordpressService {

    Comment create(Comment category);

    void delete(int categoryId, boolean force);

    List<Comment> list(CommentSearchCriteria criteria);

    Comment retrieve(int categoryId, Context context);

    Comment update(int categoryId, Comment category);

}
