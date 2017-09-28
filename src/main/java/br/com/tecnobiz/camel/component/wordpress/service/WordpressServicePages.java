package br.com.tecnobiz.camel.component.wordpress.service;

import java.util.List;

import br.com.tecnobiz.camel.component.wordpress.model.Context;
import br.com.tecnobiz.camel.component.wordpress.model.Page;
import br.com.tecnobiz.camel.component.wordpress.model.PageSearchCriteria;

public interface WordpressServicePages {

    List<Page> list(PageSearchCriteria searchCriteria);

    Page retrieve(int pageId, Context context, String password);

    Page create(Page post);

    Page update(int id, Page post);

    void delete(int id, boolean force);

}
