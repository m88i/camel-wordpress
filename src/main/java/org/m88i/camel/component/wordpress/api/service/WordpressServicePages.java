package org.m88i.camel.component.wordpress.api.service;

import java.util.List;

import org.m88i.camel.component.wordpress.api.model.Context;
import org.m88i.camel.component.wordpress.api.model.Page;
import org.m88i.camel.component.wordpress.api.model.PageSearchCriteria;

public interface WordpressServicePages extends WordpressService {

    List<Page> list(PageSearchCriteria searchCriteria);

    Page retrieve(int pageId, Context context, String password);

    Page create(Page post);

    Page update(int id, Page post);

    void delete(int id, boolean force);

}
