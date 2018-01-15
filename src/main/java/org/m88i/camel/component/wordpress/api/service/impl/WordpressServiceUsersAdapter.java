package org.m88i.camel.component.wordpress.api.service.impl;

import java.util.List;

import org.m88i.camel.component.wordpress.api.model.Context;
import org.m88i.camel.component.wordpress.api.model.User;
import org.m88i.camel.component.wordpress.api.model.UserSearchCriteria;
import org.m88i.camel.component.wordpress.api.service.WordpressServiceUsers;
import org.m88i.camel.component.wordpress.api.service.spi.UsersSPI;

public class WordpressServiceUsersAdapter extends AbstractWordpressCrudServiceAdapter<UsersSPI, User, UserSearchCriteria> implements WordpressServiceUsers {

    public WordpressServiceUsersAdapter(String wordpressUrl, String apiVersion) {
        super(wordpressUrl, apiVersion);
    }

    @Override
    public List<User> list(UserSearchCriteria s) {
        // @formatter:off
        return getSpi().list(getApiVersion(), 
                             s.getContext(), 
                             s.getPage(), 
                             s.getPerPage(), 
                             s.getSearch(),
                             s.getExclude(), 
                             s.getInclude(), 
                             s.getOffset(), 
                             s.getOrder(),
                             s.getOrderBy(), 
                             s.getSlug(), 
                             s.getRoles());
        // @formatter:on
    }

    @Override
    protected Class<UsersSPI> getSpiType() {
        return UsersSPI.class;
    }

    @Override
    protected User doCreate(User object) {
        return getSpi().create(getApiVersion(), object);
    }

    @Override
    protected void doDelete(Integer id, Boolean force) {
        getSpi().delete(getApiVersion(), id, force, 1);
    }

    @Override
    protected User doUpdate(Integer id, User object) {
        return getSpi().update(getApiVersion(), id, object);
    }

    @Override
    protected User doRetrieve(Integer entityID, Context context) {
        return getSpi().retrieve(getApiVersion(), entityID, context);
    }

}
