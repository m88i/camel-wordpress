package br.com.tecnobiz.camel.component.wordpress.service;

import java.util.List;

import br.com.tecnobiz.camel.component.wordpress.model.Context;
import br.com.tecnobiz.camel.component.wordpress.model.PostRevision;

public interface WordpressServicePostRevision {
    
    void delete(int postId, int revisionId);
    
    PostRevision retrieve(int postId, int revisionId, Context context);
    
    List<PostRevision> list(int postId, Context context);

}
