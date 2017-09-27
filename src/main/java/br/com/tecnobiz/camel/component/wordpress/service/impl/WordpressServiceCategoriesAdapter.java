package br.com.tecnobiz.camel.component.wordpress.service.impl;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.emptyToNull;

import java.util.List;

import br.com.tecnobiz.camel.component.wordpress.WordpressConstants;
import br.com.tecnobiz.camel.component.wordpress.api.CategoriesAPI;
import br.com.tecnobiz.camel.component.wordpress.model.Category;
import br.com.tecnobiz.camel.component.wordpress.model.CategorySearchCriteria;
import br.com.tecnobiz.camel.component.wordpress.model.Context;
import br.com.tecnobiz.camel.component.wordpress.service.WordpressServiceCategories;

public class WordpressServiceCategoriesAdapter extends AbstractWordpressServiceAdapter<CategoriesAPI> implements WordpressServiceCategories {

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
    protected Class<CategoriesAPI> getType() {
        return CategoriesAPI.class;
    }

    @Override
    public Category create(Category category) {
        checkNotNull(category, "Please define a category");
        checkNotNull(emptyToNull(category.getName()), "Name is mandatory");
        return this.api.create(WordpressConstants.API_VERSION, category);
    }

    @Override
    public void delete(int categoryId, boolean force) {
        checkArgument(categoryId > 0, "Please define a category");
        this.api.delete(WordpressConstants.API_VERSION, categoryId, force);
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

    @Override
    public Category update(int categoryId, Category category) {
        checkArgument(categoryId > 0, "Please define a category");
        checkNotNull(category, "Which category are you trying to update?");
        return api.update(WordpressConstants.API_VERSION, categoryId, category);
    }

}
