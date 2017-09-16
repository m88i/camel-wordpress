package br.com.tecnobiz.camel.component.wordpress;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

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
                from("wordpress://foo")
                  .to("wordpress://bar")
                  .to("mock:result");
            }
        };
    }
}
