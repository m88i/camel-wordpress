package org.m88i.camel.component.wordpress.api.service;

import java.util.List;

import org.m88i.camel.component.wordpress.api.model.Context;
import org.m88i.camel.component.wordpress.api.model.PostRevision;

public interface WordpressServicePostRevision extends WordpressService {
    
    void delete(int postId, int revisionId);
    
    PostRevision retrieve(int postId, int revisionId, Context context);
    
    List<PostRevision> list(int postId, Context context);

}
