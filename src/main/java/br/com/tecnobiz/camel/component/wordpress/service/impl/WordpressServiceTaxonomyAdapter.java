package br.com.tecnobiz.camel.component.wordpress.service.impl;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.emptyToNull;

import java.util.Map;

import br.com.tecnobiz.camel.component.wordpress.WordpressConstants;
import br.com.tecnobiz.camel.component.wordpress.api.TaxonomyAPI;
import br.com.tecnobiz.camel.component.wordpress.model.Context;
import br.com.tecnobiz.camel.component.wordpress.model.Taxonomy;
import br.com.tecnobiz.camel.component.wordpress.service.WordpressServiceTaxonomy;

public class WordpressServiceTaxonomyAdapter extends AbstractWordpressServiceAdapter<TaxonomyAPI> implements WordpressServiceTaxonomy {

    public WordpressServiceTaxonomyAdapter(String wordpressUrl) {
        super(wordpressUrl);
    }

    @Override
    protected Class<TaxonomyAPI> getApiType() {
        return TaxonomyAPI.class;
    }
    
    @Override
    public Map<String, Taxonomy> list(Context context, String postType) {
        return getApi().list(WordpressConstants.API_VERSION, context, postType);
    }
    
    @Override
    public Taxonomy retrieve(Context context, String taxonomy) {
        checkNotNull(emptyToNull(taxonomy), "Please define a taxonomy");
        return getApi().retrieve(WordpressConstants.API_VERSION, context, taxonomy);
    }

}
