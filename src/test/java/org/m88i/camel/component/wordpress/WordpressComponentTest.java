package org.m88i.camel.component.wordpress;

import java.util.concurrent.TimeUnit;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Test;
import org.m88i.camel.component.wordpress.config.WordpressConfiguration;
import org.m88i.camel.component.wordpress.integration.WordpressMockServerTestSupport;

public class WordpressComponentTest extends WordpressMockServerTestSupport {

    @Test
    public void testWordpress() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMinimumMessageCount(1);       
        
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
                
                from("wordpress:post?id=1")
                  .to("mock:result");
            }
        };
    }
}
