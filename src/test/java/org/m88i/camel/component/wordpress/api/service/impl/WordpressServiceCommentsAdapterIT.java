package org.m88i.camel.component.wordpress.api.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.m88i.camel.component.wordpress.WordpressTestConstants;
import org.m88i.camel.component.wordpress.api.model.Category;
import org.m88i.camel.component.wordpress.api.model.CategorySearchCriteria;
import org.m88i.camel.component.wordpress.api.service.WordpressServiceCategories;
import org.m88i.camel.component.wordpress.proxy.WordpressServiceProvider;

public class WordpressServiceCommentsAdapterIT {

    private static WordpressServiceCategories serviceCategories;

    @BeforeClass
    public static void before() {
        final WordpressServiceProvider serviceProvider = WordpressServiceProvider.getInstance();
        serviceProvider.init(WordpressTestConstants.WORDPRESS_DEMO_URL);
        serviceCategories = serviceProvider.getService(WordpressServiceCategories.class);
    }

    @Test
    public void testRetrieve() {
        final Category cat = serviceCategories.retrieve(1, null);
        assertThat(cat, not(nullValue()));
        assertThat(cat.getId(), is(1));
        assertThat(cat.getName(), not(isEmptyOrNullString()));
    }

    @Test
    public void testList() {
        final CategorySearchCriteria criteria = new CategorySearchCriteria();
        criteria.setPage(1);
        criteria.setPerPage(2);
        final List<Category> revisions = serviceCategories.list(criteria);
        assertThat(revisions, is(not(emptyCollectionOf(Category.class))));
        assertThat(revisions.size(), is(2));
    }
}
