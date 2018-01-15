package org.m88i.camel.component.wordpress.api.service;

import org.m88i.camel.component.wordpress.api.model.Context;
import org.m88i.camel.component.wordpress.api.model.Page;
import org.m88i.camel.component.wordpress.api.model.PageSearchCriteria;

public interface WordpressServicePages extends WordpressCrudService<Page, PageSearchCriteria> {

    Page retrieve(Integer pageId, Context context, String password);

}
