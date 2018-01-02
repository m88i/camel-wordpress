package org.m88i.camel.component.wordpress;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Ignore;
import org.junit.Test;
import org.m88i.camel.component.wordpress.api.model.Post;
import org.m88i.camel.component.wordpress.config.WordpressConfiguration;
import org.m88i.camel.component.wordpress.support.WordpressMockServerTestSupport;

public class WordpressPostOperationTest extends WordpressMockServerTestSupport {

    public WordpressPostOperationTest() {
        
    }
    
    @Test
    public void testPostSingleRequest() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:resultSingle");
        mock.expectedMinimumMessageCount(1);       
        mock.expectedBodyReceived().body(Post.class);
        
        assertMockEndpointsSatisfied(3, TimeUnit.SECONDS);
    }
    
    @Ignore
    @Test
    public void testPostListRequest() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:resultList");
        mock.expectedMinimumMessageCount(1);       
        mock.expectedBodyReceived().body(List.class);
        
        assertMockEndpointsSatisfied(90, TimeUnit.SECONDS);
    }
    
    
    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                final WordpressConfiguration configuration = new WordpressConfiguration();
                final WordpressComponent component = new WordpressComponent();
                configuration.setApiVersion(WordpressConstants.API_VERSION);
                configuration.setUrl(getServerBaseUrl());
                component.setConfiguration(configuration);
                getContext().addComponent("wordpress", component);
                
                from("wordpress:post?criteria.perPage=10")
                    .to("mock:resultList");
                
                from("wordpress:post?id=114913")
                  .to("mock:resultSingle");
            }
        };
    }

}
