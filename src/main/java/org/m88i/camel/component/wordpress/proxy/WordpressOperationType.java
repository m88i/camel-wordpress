package org.m88i.camel.component.wordpress.proxy;

import org.m88i.camel.component.wordpress.api.model.PostSearchCriteria;
import org.m88i.camel.component.wordpress.api.model.SearchCriteria;

public enum WordpressOperationType {

    post(PostSearchCriteria.class);

    private final Class<? extends SearchCriteria> criteriaType;

    private WordpressOperationType(Class<? extends SearchCriteria> criteriaType) {
        this.criteriaType = criteriaType;
    }

    public Class<? extends SearchCriteria> getCriteriaType() {
        return criteriaType;
    }

}
