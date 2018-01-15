package org.m88i.camel.component.wordpress;

import java.io.IOException;

import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.m88i.camel.component.wordpress.api.test.WordpressMockServerTestSupport;

public class WordpressComponentTestSupport extends CamelTestSupport {

    public WordpressComponentTestSupport() {

    }

    @BeforeClass
    public static void beforeClass() throws IOException {
        WordpressMockServerTestSupport.setUpMockServer();
    }

    @AfterClass
    public static void afterClass() {
        WordpressMockServerTestSupport.tearDownMockServer();
    }
    
    protected String getServerBaseUrl() {
        return WordpressMockServerTestSupport.getServerBaseUrl();
    }
}
