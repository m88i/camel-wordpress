package br.com.tecnobiz.camel.component.wordpress.service;

import java.util.List;

import br.com.tecnobiz.camel.component.wordpress.model.PostContext;
import br.com.tecnobiz.camel.component.wordpress.model.PostRevision;

public interface WordpressServicePostRevision {
    
    void delete(int postId, int revisionId);
    
    PostRevision retrieve(int postId, int revisionId, PostContext context);
    
    List<PostRevision> list(int postId, PostContext context);

}
