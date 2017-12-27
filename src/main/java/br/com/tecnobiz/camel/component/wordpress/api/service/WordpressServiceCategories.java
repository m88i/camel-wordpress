package br.com.tecnobiz.camel.component.wordpress.api.service;

import java.util.List;

import br.com.tecnobiz.camel.component.wordpress.api.model.Category;
import br.com.tecnobiz.camel.component.wordpress.api.model.CategorySearchCriteria;
import br.com.tecnobiz.camel.component.wordpress.api.model.Context;

public interface WordpressServiceCategories extends WordpressService {
    
    Category create(Category category);
    
    void delete(int categoryId, boolean force);
    
    List<Category> list(CategorySearchCriteria criteria);
    
    Category retrieve(int categoryId, Context context);
    
    Category update(int categoryId, Category category);
}
