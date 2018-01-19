package org.m88i.camel.component.wordpress.proxy;

import org.wordpress4j.model.PostSearchCriteria;
import org.wordpress4j.model.SearchCriteria;

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
