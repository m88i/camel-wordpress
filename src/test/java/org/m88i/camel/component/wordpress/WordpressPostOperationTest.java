package org.m88i.camel.component.wordpress;

import static org.hamcrest.CoreMatchers.is;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Test;
import org.m88i.camel.component.wordpress.config.WordpressComponentConfiguration;
import org.wordpress4j.WordpressConstants;
import org.wordpress4j.model.Content;
import org.wordpress4j.model.Post;
import org.wordpress4j.model.PublishableStatus;

public class WordpressPostOperationTest extends WordpressComponentTestSupport {

    public WordpressPostOperationTest() {

    }

    @Test
    public void testPostSingleRequest() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:resultSingle");
        mock.expectedMinimumMessageCount(1);
        mock.expectedBodyReceived().body(Post.class);

        assertMockEndpointsSatisfied();
    }

    @Test
    public void testPostListRequest() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:resultList");
        mock.expectedMinimumMessageCount(1);
        mock.expectedBodyReceived().body(Post.class);

        assertMockEndpointsSatisfied();
    }
    
    @Test
    public void testInsertPost() throws InterruptedException {
        MockEndpoint mock = getMockEndpoint("mock:resultInsert");
        mock.expectedBodyReceived().body(Post.class);
        mock.expectedMessageCount(1);
        
        final Post request = new Post();
        request.setAuthor(2);
        request.setTitle(new Content("hello from postman 2"));
        
        final Post response = (Post) template.requestBody("direct:insertPost", request);
        assertThat(response.getId(), is(9));
        assertThat(response.getStatus(), is(PublishableStatus.draft));
        
        assertMockEndpointsSatisfied();
    }
    
    @Test
    public void testUpdatePost() throws InterruptedException {
        MockEndpoint mock = getMockEndpoint("mock:resultUpdate");
        mock.expectedBodyReceived().body(Post.class);
        mock.expectedMessageCount(1);
        
        final Post request = new Post();
        request.setAuthor(2);
        request.setTitle(new Content("hello from postman 2 - update"));
        
        final Post response = (Post) template.requestBody("direct:updatePost", request);
        assertThat(response.getId(), is(9));
        assertThat(response.getStatus(), is(PublishableStatus.draft));
        assertThat(response.getTitle().getRaw(), is("hello from postman 2 - update"));
        assertThat(response.getTitle().getRendered(), is("hello from postman 2 &#8211; update"));
        
        assertMockEndpointsSatisfied();
    }
    
    @Test
    public void testDeletePost() throws InterruptedException {
        MockEndpoint mock = getMockEndpoint("mock:resultDelete");
        mock.expectedBodyReceived().body(Post.class);
        mock.expectedMessageCount(1);
        
        final Post response = (Post) template.requestBody("direct:deletePost", "");
        assertThat(response.getId(), is(9));
        assertThat(response.getStatus(), is(PublishableStatus.trash));
        
        assertMockEndpointsSatisfied();
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                final WordpressComponentConfiguration configuration = new WordpressComponentConfiguration();
                final WordpressComponent component = new WordpressComponent();
                configuration.setApiVersion(WordpressConstants.API_VERSION);
                configuration.setUrl(getServerBaseUrl());
                component.setConfiguration(configuration);
                getContext().addComponent("wordpress", component);

                from("wordpress:post?criteria.perPage=10&criteria.orderBy=author&criteria.categories=camel,dozer,json").to("mock:resultList");

                from("wordpress:post?id=114913").to("mock:resultSingle");
                
                from("direct:deletePost").to("wordpress:post:delete?id=9&user=ben&password=password123").to("mock:resultDelete");
                from("direct:insertPost").to("wordpress:post?user=ben&password=password123").to("mock:resultInsert");
                from("direct:updatePost").to("wordpress:post?id=9&user=ben&password=password123").to("mock:resultUpdate");
            }
        };
    }

}
