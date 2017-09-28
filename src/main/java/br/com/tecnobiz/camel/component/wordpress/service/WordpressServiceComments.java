package br.com.tecnobiz.camel.component.wordpress.service;

import java.util.List;

import br.com.tecnobiz.camel.component.wordpress.model.Comment;
import br.com.tecnobiz.camel.component.wordpress.model.CommentSearchCriteria;
import br.com.tecnobiz.camel.component.wordpress.model.Context;

public interface WordpressServiceComments {

    Comment create(Comment category);

    void delete(int categoryId, boolean force);

    List<Comment> list(CommentSearchCriteria criteria);

    Comment retrieve(int categoryId, Context context);

    Comment update(int categoryId, Comment category);

}
