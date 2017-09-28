package br.com.tecnobiz.camel.component.wordpress.service.impl;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import br.com.tecnobiz.camel.component.wordpress.WordpressConstants;
import br.com.tecnobiz.camel.component.wordpress.api.WordpressAPI;

/**
 * Base service adapter implementation with C(R)UD commons operations.
 * 
 * @param <A>
 * @param <T>
 */
abstract class AbstractWordpressCrudServiceAdapter<A extends WordpressAPI<T>, T> extends AbstractWordpressServiceAdapter<A> {

    AbstractWordpressCrudServiceAdapter(final String wordpressUrl) {
        super(wordpressUrl);
    }

    public T create(T object) {
        checkNotNull(object, "Please define an object to create");
        return getApi().create(WordpressConstants.API_VERSION, object);
    }

    public void delete(int id, boolean force) {
        checkArgument(id > 0, "The id is mandatory");
        getApi().delete(WordpressConstants.API_VERSION, id, force);
    }

    public T update(int id, T object) {
        checkNotNull(object, "Please define an object to update");
        checkArgument(id > 0, "The id is mandatory");
        return getApi().update(WordpressConstants.API_VERSION, id, object);
    }

}
