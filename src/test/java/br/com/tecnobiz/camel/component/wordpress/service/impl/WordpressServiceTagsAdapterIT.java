package br.com.tecnobiz.camel.component.wordpress.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.tecnobiz.camel.component.wordpress.WordpressServiceProvider;
import br.com.tecnobiz.camel.component.wordpress.WordpressTestConstants;
import br.com.tecnobiz.camel.component.wordpress.api.model.Tag;
import br.com.tecnobiz.camel.component.wordpress.api.model.TagSearchCriteria;
import br.com.tecnobiz.camel.component.wordpress.api.service.WordpressServiceTags;

public class WordpressServiceTagsAdapterIT {

    private static WordpressServiceTags serviceTags;

    @BeforeClass
    public static void before() {
        final WordpressServiceProvider serviceProvider = WordpressServiceProvider.getInstance();
        serviceProvider.init(WordpressTestConstants.WORDPRESS_DEMO_URL);
        serviceTags = serviceProvider.getService(WordpressServiceTags.class);
    }

    @Test
    public void testRetrieve() {
        final Tag tag = serviceTags.retrieve(6, null);
        assertThat(tag, not(nullValue()));
        assertThat(tag.getId(), is(6));
        assertThat(tag.getName(), not(isEmptyOrNullString()));
    }

    @Test
    public void testList() {
        final TagSearchCriteria criteria = new TagSearchCriteria();
        criteria.setPage(1);
        criteria.setPerPage(2);
        final List<Tag> revisions = serviceTags.list(criteria);
        assertThat(revisions, is(not(emptyCollectionOf(Tag.class))));
        assertThat(revisions.size(), is(2));
    }
}
