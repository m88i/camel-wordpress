package br.com.tecnobiz.camel.component.wordpress.service.impl;

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

    WordpressServiceCategoriesAdapter(String wordpressUrl) {
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
        // TODO Auto-generated method stub

    }

    @Override
    public List<Category> list(CategorySearchCriteria criteria) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Category retrieve(int categoryId, Context context) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Category update(int categoryId, Category category) {
        // TODO Auto-generated method stub
        return null;
    }

}
