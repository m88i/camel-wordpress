package br.com.tecnobiz.camel.component.wordpress.api.service.impl;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.emptyToNull;

import java.util.Map;

import br.com.tecnobiz.camel.component.wordpress.api.model.Context;
import br.com.tecnobiz.camel.component.wordpress.api.model.Taxonomy;
import br.com.tecnobiz.camel.component.wordpress.api.service.WordpressServiceTaxonomy;
import br.com.tecnobiz.camel.component.wordpress.api.service.spi.TaxonomyAPI;

public class WordpressServiceTaxonomyAdapter extends AbstractWordpressServiceAdapter<TaxonomyAPI> implements WordpressServiceTaxonomy {

    public WordpressServiceTaxonomyAdapter(String wordpressUrl, String apiVersion) {
        super(wordpressUrl, apiVersion);
    }

    @Override
    protected Class<TaxonomyAPI> getApiType() {
        return TaxonomyAPI.class;
    }
    
    @Override
    public Map<String, Taxonomy> list(Context context, String postType) {
        return getApi().list(this.getApiVersion(), context, postType);
    }
    
    @Override
    public Taxonomy retrieve(Context context, String taxonomy) {
        checkNotNull(emptyToNull(taxonomy), "Please define a taxonomy");
        return getApi().retrieve(this.getApiVersion(), context, taxonomy);
    }

}
