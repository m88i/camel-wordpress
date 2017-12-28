package org.m88i.camel.component.wordpress.api.service;

import java.util.List;

import org.m88i.camel.component.wordpress.api.model.Category;
import org.m88i.camel.component.wordpress.api.model.CategorySearchCriteria;
import org.m88i.camel.component.wordpress.api.model.Context;

public interface WordpressServiceCategories extends WordpressService {
    
    Category create(Category category);
    
    void delete(Integer categoryId, Boolean force);
    
    List<Category> list(CategorySearchCriteria criteria);
    
    Category retrieve(Integer categoryId, Context context);
    
    Category update(Integer categoryId, Category category);
}
