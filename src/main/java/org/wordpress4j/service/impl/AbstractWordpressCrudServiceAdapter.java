package org.wordpress4j.service.impl;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import org.wordpress4j.model.Context;
import org.wordpress4j.model.SearchCriteria;
import org.wordpress4j.service.WordpressCrudService;

/**
 * Base service adapter implementation with CRUD commons operations.
 * 
 * @param <A>
 * @param <T>
 */
abstract class AbstractWordpressCrudServiceAdapter<A, T, S extends SearchCriteria> extends AbstractWordpressServiceAdapter<A>
    implements WordpressCrudService<T, S> {

    AbstractWordpressCrudServiceAdapter(final String wordpressUrl, final String apiVersion) {
        super(wordpressUrl, apiVersion);
    }

    public final T create(T object) {
        checkNotNull(object, "Please define an object to create");
        return this.doCreate(object); //getSpi().create(this.getApiVersion(), object);
    }
    
    protected abstract T doCreate(T object);

    public final void delete(Integer id, Boolean force) {
        checkArgument(id > 0, "The id is mandatory");
        this.doDelete(id, force);
        //getSpi().delete(this.getApiVersion(), id, force);
    }
    
    protected abstract void doDelete(Integer id, Boolean force);

    public final T update(Integer id, T object) {
        checkNotNull(object, "Please define an object to update");
        checkArgument(id > 0, "The id is mandatory");
        //return getSpi().update(this.getApiVersion(), id, object);
        return this.doUpdate(id, object);
    }
    
    protected abstract T doUpdate(Integer id, T object);

    @Override
    public T retrieve(Integer entityID) {
        return this.retrieve(entityID, Context.view);
    }

    @Override
    public final T retrieve(Integer entityID, Context context) {
        checkArgument(entityID > 0, "Please provide a non zero id");
        checkNotNull(context, "Provide a context");
        //return this.getSpi().retrieve(getApiVersion(), entityID, context);
        return doRetrieve(entityID, context);
    }
    
    protected abstract T doRetrieve(Integer entityID, Context context);

}
