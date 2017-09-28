package br.com.tecnobiz.camel.component.wordpress.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.tecnobiz.camel.component.wordpress.WordpressServiceProvider;
import br.com.tecnobiz.camel.component.wordpress.WordpressTestConstants;
import br.com.tecnobiz.camel.component.wordpress.model.Page;
import br.com.tecnobiz.camel.component.wordpress.model.PageSearchCriteria;
import br.com.tecnobiz.camel.component.wordpress.service.WordpressServicePages;

public class WordpressServicePagesAdapterIT {

    private static WordpressServicePages servicePages;

    @BeforeClass
    public static void before() {
        final WordpressServiceProvider serviceProvider = WordpressServiceProvider.getInstance();
        serviceProvider.init(WordpressTestConstants.WORDPRESS_DEMO_URL);
        servicePages = serviceProvider.getServicePages();
    }

    @Test
    public void testRetrieve() {
        final Page page = servicePages.retrieve(2, null, null);
        assertThat(page, not(nullValue()));
        assertThat(page.getId(), is(2));
    }

    @Test
    public void testList() {
        final PageSearchCriteria criteria = new PageSearchCriteria();
        criteria.setPage(1);
        criteria.setPerPage(5);
        final List<Page> posts = servicePages.list(criteria);
        assertThat(posts, is(not(emptyCollectionOf(Page.class))));
        assertThat(posts.size(), is(5));
    }
}
