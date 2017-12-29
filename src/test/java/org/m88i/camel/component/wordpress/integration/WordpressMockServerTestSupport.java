package org.m88i.camel.component.wordpress.integration;

import java.io.IOException;
import java.net.BindException;

import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.http.impl.bootstrap.HttpServer;
import org.apache.http.impl.bootstrap.ServerBootstrap;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class WordpressMockServerTestSupport extends CamelTestSupport {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(WordpressMockServerTestSupport.class);
    
    protected static HttpServer localServer;
    private static final int PORT = 9009;

    public WordpressMockServerTestSupport() {

    }

    @BeforeClass
    public static void setUpMockServer() throws IOException {
        // @formatter:off
        int i = 0;
        while(true) {
            try {
                localServer = createServer(PORT + i);
                localServer.start();
                break;
            } catch(BindException ex) {
                LOGGER.warn("Port {} already in use, trying next one", PORT + i);
                i++;
            }
        }
            
        // @formatter:on
        LOGGER.info("Local server up and running on address {} and port {}", localServer.getInetAddress(), localServer.getLocalPort());
        
    }
    
    private static HttpServer createServer(int port) {
        return ServerBootstrap
            .bootstrap()
            .setListenerPort(port)
            .registerHandler("/wp/v2/posts", new WordpressServerHttpRequestHandler("/data/posts/list.json"))
            .registerHandler("/wp/v2/posts/*", new WordpressServerHttpRequestHandler("/data/posts/single.json"))
            .create();
    } 
    
    @AfterClass
    public static void tearDownMockServer() {
        LOGGER.info("Stopping local server");
        if (localServer != null) {
            localServer.stop();
        }
    }
    
    protected static String getServerBaseUrl() {
        return "http://" +  localServer.getInetAddress().getHostName() + ":" + localServer.getLocalPort();
    }
}
