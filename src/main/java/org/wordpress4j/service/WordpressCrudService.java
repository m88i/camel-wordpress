package org.wordpress4j.service;

import java.util.List;

import org.wordpress4j.model.Context;
import org.wordpress4j.model.SearchCriteria;

/**
 * Common interface for services performing CRUD operations
 * 
 * @param <T> model type
 * @param <S> {@link SearchCriteria} used for this model
 */
public interface WordpressCrudService<T, S extends SearchCriteria> extends WordpressService {

    T retrieve(Integer entityID, Context context);

    T retrieve(Integer entityID);
    
    T create(T entity);

    void delete(Integer entityID, Boolean force);

    List<T> list(S searchCriteria);

    T update(Integer entityID, T entity);
}
