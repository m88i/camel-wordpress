package br.com.tecnobiz.camel.component.wordpress;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.emptyToNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.tecnobiz.camel.component.wordpress.service.WordpressServicePostRevision;
import br.com.tecnobiz.camel.component.wordpress.service.WordpressServicePosts;
import br.com.tecnobiz.camel.component.wordpress.service.impl.WordpressServicePostsAdapter;
import br.com.tecnobiz.camel.component.wordpress.service.impl.WordpressSevicePostRevisionAdapter;

public class WordpressServiceProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(WordpressServiceProvider.class);
    
    private String wordpressApiUrl;
    private WordpressServicePosts servicePosts;
    private WordpressServicePostRevision servicePostRevision;

    private WordpressServiceProvider() {

    }

    private static class ServiceProviderHolder {
        private static final WordpressServiceProvider INSTANCE = new WordpressServiceProvider();
    }

    public static WordpressServiceProvider getInstance() {
        return ServiceProviderHolder.INSTANCE;
    }

    public void init(String wordpressApiUrl) {
        checkNotNull(emptyToNull(wordpressApiUrl), "Please inform the Wordpress API url , eg.: http://myblog.com/wp-json/wp");
        this.wordpressApiUrl = wordpressApiUrl;
        this.servicePosts = new WordpressServicePostsAdapter(this.wordpressApiUrl);
        this.servicePostRevision = new WordpressSevicePostRevisionAdapter(wordpressApiUrl);
        LOGGER.info("Wordpress Component initialized using base URL: {}", wordpressApiUrl);
    }

    public WordpressServicePosts getServicePosts() {
        checkNotNull(servicePosts, "Please set the Wordpress Url at init(URL)");
        return servicePosts;
    }
    
    public WordpressServicePostRevision getServicePostRevision() {
        checkNotNull(servicePostRevision, "Please set the Wordpress Url at init(URL)");
        return servicePostRevision;
    }
}
