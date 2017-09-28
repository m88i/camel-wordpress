package br.com.tecnobiz.camel.component.wordpress.service.impl;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import br.com.tecnobiz.camel.component.wordpress.WordpressConstants;
import br.com.tecnobiz.camel.component.wordpress.api.CategoriesAPI;
import br.com.tecnobiz.camel.component.wordpress.model.Category;
import br.com.tecnobiz.camel.component.wordpress.model.CategorySearchCriteria;
import br.com.tecnobiz.camel.component.wordpress.model.Context;
import br.com.tecnobiz.camel.component.wordpress.service.WordpressServiceCategories;

public class WordpressServiceCategoriesAdapter extends AbstractWordpressCrudServiceAdapter<CategoriesAPI, Category> implements WordpressServiceCategories {

    private CategoriesAPI api;

    public WordpressServiceCategoriesAdapter(String wordpressUrl) {
        super(wordpressUrl);
    }

    @Override
    protected CategoriesAPI getApi() {
        return api;
    }

    @Override
    protected void setApi(CategoriesAPI api) {
        this.api = api;
    }

    @Override
    protected Class<CategoriesAPI> getApiType() {
        return CategoriesAPI.class;
    }

    //@formatter:off
    @Override
    public List<Category> list(CategorySearchCriteria criteria) {
        checkNotNull(criteria, "The search criteria must be defined");
        return this.api.list(WordpressConstants.API_VERSION, 
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
        return api.retrieve(WordpressConstants.API_VERSION, categoryId, context);
    }

}
