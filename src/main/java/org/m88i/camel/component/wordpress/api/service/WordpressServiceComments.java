package org.m88i.camel.component.wordpress.api.service;

import java.util.List;

import org.m88i.camel.component.wordpress.api.model.Comment;
import org.m88i.camel.component.wordpress.api.model.CommentSearchCriteria;
import org.m88i.camel.component.wordpress.api.model.Context;

public interface WordpressServiceComments extends WordpressService {

    Comment create(Comment category);

    void delete(Integer categoryId, Boolean force);

    List<Comment> list(CommentSearchCriteria criteria);

    Comment retrieve(Integer categoryId, Context context);

    Comment update(Integer categoryId, Comment category);

}
