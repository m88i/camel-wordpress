package org.m88i.camel.component.wordpress.api.service;

import java.util.List;

import org.m88i.camel.component.wordpress.api.model.Context;
import org.m88i.camel.component.wordpress.api.model.PostRevision;

public interface WordpressServicePostRevision extends WordpressService {
    
    void delete(Integer postId, Integer revisionId);
    
    PostRevision retrieve(Integer postId, Integer revisionId, Context context);
    
    List<PostRevision> list(Integer postId, Context context);

}
