package org.m88i.camel.component.wordpress.api.service.impl;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.emptyToNull;

import java.util.Map;

import org.m88i.camel.component.wordpress.api.model.Context;
import org.m88i.camel.component.wordpress.api.model.Taxonomy;
import org.m88i.camel.component.wordpress.api.service.WordpressServiceTaxonomy;
import org.m88i.camel.component.wordpress.api.service.spi.TaxonomySPI;

public class WordpressServiceTaxonomyAdapter extends AbstractWordpressServiceAdapter<TaxonomySPI> implements WordpressServiceTaxonomy {

    public WordpressServiceTaxonomyAdapter(String wordpressUrl, String apiVersion) {
        super(wordpressUrl, apiVersion);
    }

    @Override
    protected Class<TaxonomySPI> getSpiType() {
        return TaxonomySPI.class;
    }
    
    @Override
    public Map<String, Taxonomy> list(Context context, String postType) {
        return getSpi().list(this.getApiVersion(), context, postType);
    }
    
    @Override
    public Taxonomy retrieve(Context context, String taxonomy) {
        checkNotNull(emptyToNull(taxonomy), "Please define a taxonomy");
        return getSpi().retrieve(this.getApiVersion(), context, taxonomy);
    }

}
