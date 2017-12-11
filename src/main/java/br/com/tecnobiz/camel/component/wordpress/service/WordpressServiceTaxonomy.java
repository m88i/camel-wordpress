package br.com.tecnobiz.camel.component.wordpress.service;

import java.util.Map;

import br.com.tecnobiz.camel.component.wordpress.model.Context;
import br.com.tecnobiz.camel.component.wordpress.model.Taxonomy;

public interface WordpressServiceTaxonomy {

    Map<String, Taxonomy> list(Context context, String postType);
    
    Taxonomy retrieve(Context context, String taxonomy);

}
