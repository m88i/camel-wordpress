package br.com.tecnobiz.camel.component.wordpress.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.m88i.camel.component.wordpress.WordpressServiceProvider;
import org.m88i.camel.component.wordpress.api.model.PostRevision;
import org.m88i.camel.component.wordpress.api.service.WordpressServicePostRevision;

import br.com.tecnobiz.camel.component.wordpress.WordpressTestConstants;

/*
 * TODO fix authentication problem (when implementing global authentication) 
 * javax.ws.rs.NotAuthorizedException: HTTP 401 Unauthorized
 */
@Ignore
public class WordpressServicePostRevisionAdapterIT {

    private static WordpressServicePostRevision servicePostRevision;

    @BeforeClass
    public static void before() {
        final WordpressServiceProvider serviceProvider = WordpressServiceProvider.getInstance();
        serviceProvider.init(WordpressTestConstants.WORDPRESS_DEMO_URL);
        servicePostRevision = serviceProvider.getService(WordpressServicePostRevision.class);
    }

    @Test
    public void testRetrieve() {
        final PostRevision revision = servicePostRevision.retrieve(1, 1, null);
        assertThat(revision, not(nullValue()));
        assertThat(revision.getId(), is(1));
        assertThat(revision.getGuid(), notNullValue());
    }

    @Test
    public void testList() {
        final List<PostRevision> revisions = servicePostRevision.list(1, null);
        assertThat(revisions, is(not(emptyCollectionOf(PostRevision.class))));
        assertThat(revisions.size(), greaterThan(0));
    }
}
