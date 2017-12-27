package br.com.tecnobiz.camel.component.wordpress.api.service.impl;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import br.com.tecnobiz.camel.component.wordpress.api.model.Category;
import br.com.tecnobiz.camel.component.wordpress.api.model.CategorySearchCriteria;
import br.com.tecnobiz.camel.component.wordpress.api.model.Context;
import br.com.tecnobiz.camel.component.wordpress.api.service.WordpressServiceCategories;
import br.com.tecnobiz.camel.component.wordpress.api.service.spi.CategoriesAPI;

public class WordpressServiceCategoriesAdapter extends AbstractWordpressCrudServiceAdapter<CategoriesAPI, Category> implements WordpressServiceCategories {

    public WordpressServiceCategoriesAdapter(String wordpressUrl, String apiVersion) {
        super(wordpressUrl, apiVersion);
    }

    @Override
    protected Class<CategoriesAPI> getApiType() {
        return CategoriesAPI.class;
    }

    //@formatter:off
    @Override
    public List<Category> list(CategorySearchCriteria criteria) {
        checkNotNull(criteria, "The search criteria must be defined");
        return getApi().list(this.getApiVersion(), 
                             criteria.getContext(), 
                             criteria.getPage(), 
                             criteria.getPerPage(), 
                             criteria.getSearch(), 
                             criteria.getExclude(), 
                             criteria.getInclude(),
                             criteria.getOrder(), 
                             criteria.getOrderBy(), 
                             criteria.isHideEmpty(), 
                             criteria.getParent(), 
                             criteria.getPostId(), 
                             criteria.getSlug());
    }
    //@formatter:on

    @Override
    public Category retrieve(int categoryId, Context context) {
        checkArgument(categoryId > 0, "Please define a category");
        return getApi().retrieve(this.getApiVersion(), categoryId, context);
    }

}
