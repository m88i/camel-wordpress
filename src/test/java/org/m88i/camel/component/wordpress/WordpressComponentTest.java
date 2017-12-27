package org.m88i.camel.component.wordpress;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import org.m88i.camel.component.wordpress.WordpressComponent;
import org.m88i.camel.component.wordpress.WordpressConstants;
import org.m88i.camel.component.wordpress.config.WordpressConfiguration;

public class WordpressComponentTest extends CamelTestSupport {

    @Test
    public void testWordpress() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMinimumMessageCount(1);       
        
        assertMockEndpointsSatisfied();
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                final WordpressConfiguration configuration = new WordpressConfiguration();
                final WordpressComponent component = new WordpressComponent();
                configuration.setApiVersion(WordpressConstants.API_VERSION);
                configuration.setUrl(WordpressTestConstants.WORDPRESS_DEMO_URL);
                component.setConfiguration(configuration);
                getContext().addComponent("wordpress", component);
                
                from("wordpress:post?id=1")
                  .to("wordpress:post")
                  .to("mock:result");
            }
        };
    }
}
