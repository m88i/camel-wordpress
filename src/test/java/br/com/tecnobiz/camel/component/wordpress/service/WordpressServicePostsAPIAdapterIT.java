package br.com.tecnobiz.camel.component.wordpress.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.tecnobiz.camel.component.wordpress.WordpressServicePosts;
import br.com.tecnobiz.camel.component.wordpress.WordpressServiceProvider;
import br.com.tecnobiz.camel.component.wordpress.WordpressTestConstants;
import br.com.tecnobiz.camel.component.wordpress.model.Post;

public class WordpressServicePostsAPIAdapterIT {

    private static WordpressServicePosts servicePosts;

    @BeforeClass
    public static void before() {
        final WordpressServiceProvider serviceProvider = WordpressServiceProvider.getInstance();
        serviceProvider.init(WordpressTestConstants.WORDPRESS_DEMO_URL);
        servicePosts = serviceProvider.getServicePosts();
    }

    @Test
    public void testRetrievePost() {
        final Post post = servicePosts.retrievePost(1);
        assertThat(post, not(nullValue()));
        assertThat(post.getId(), is(1));
    }

}
