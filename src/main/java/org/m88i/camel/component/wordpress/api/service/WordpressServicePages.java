package org.m88i.camel.component.wordpress.api.service;

import java.util.List;

import org.m88i.camel.component.wordpress.api.model.Context;
import org.m88i.camel.component.wordpress.api.model.Page;
import org.m88i.camel.component.wordpress.api.model.PageSearchCriteria;

public interface WordpressServicePages extends WordpressService {

    List<Page> list(PageSearchCriteria searchCriteria);

    Page retrieve(Integer pageId, Context context, String password);

    Page create(Page post);

    Page update(Integer id, Page post);

    void delete(Integer id, Boolean force);

}
