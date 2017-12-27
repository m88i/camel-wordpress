package org.m88i.camel.component.wordpress.api.service;

import java.util.Map;

import org.m88i.camel.component.wordpress.api.model.Context;
import org.m88i.camel.component.wordpress.api.model.Taxonomy;

public interface WordpressServiceTaxonomy extends WordpressService {

    Map<String, Taxonomy> list(Context context, String postType);
    
    Taxonomy retrieve(Context context, String taxonomy);

}
