package org.m88i.camel.component.wordpress;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Test;
import org.m88i.camel.component.wordpress.config.WordpressComponentConfiguration;
import org.wordpress4j.WordpressConstants;
import org.wordpress4j.model.Post;

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
            }
        };
    }

}
