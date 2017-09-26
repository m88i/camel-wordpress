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
import br.com.tecnobiz.camel.component.wordpress.model.Post;
import br.com.tecnobiz.camel.component.wordpress.model.PostSearchCriteria;
import br.com.tecnobiz.camel.component.wordpress.service.WordpressServicePosts;

public class WordpressServicePostsAdapterIT {

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
    
    @Test
    public void testListPosts() {
        final PostSearchCriteria criteria = new PostSearchCriteria();
        criteria.setPage(1);
        criteria.setPerPage(5);
        final List<Post> posts = servicePosts.list(criteria);
        assertThat(posts, is(not(emptyCollectionOf(Post.class))));
        assertThat(posts.size(), is(5));
    }
}
