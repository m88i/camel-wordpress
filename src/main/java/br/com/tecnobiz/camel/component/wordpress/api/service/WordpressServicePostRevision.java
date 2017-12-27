package br.com.tecnobiz.camel.component.wordpress.api.service;

import java.util.List;

import br.com.tecnobiz.camel.component.wordpress.api.model.Context;
import br.com.tecnobiz.camel.component.wordpress.api.model.PostRevision;

public interface WordpressServicePostRevision extends WordpressService {
    
    void delete(int postId, int revisionId);
    
    PostRevision retrieve(int postId, int revisionId, Context context);
    
    List<PostRevision> list(int postId, Context context);

}
