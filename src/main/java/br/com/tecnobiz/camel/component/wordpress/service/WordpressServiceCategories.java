package br.com.tecnobiz.camel.component.wordpress.service;

import java.util.List;

import br.com.tecnobiz.camel.component.wordpress.model.Category;
import br.com.tecnobiz.camel.component.wordpress.model.CategorySearchCriteria;
import br.com.tecnobiz.camel.component.wordpress.model.Context;

public interface WordpressServiceCategories {
    
    Category create(Category category);
    
    void delete(int categoryId, boolean force);
    
    List<Category> list(CategorySearchCriteria criteria);
    
    Category retrieve(int categoryId, Context context);
    
    Category update(int categoryId, Category category);
}
