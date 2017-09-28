package br.com.tecnobiz.camel.component.wordpress;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.emptyToNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.tecnobiz.camel.component.wordpress.service.WordpressServiceCategories;
import br.com.tecnobiz.camel.component.wordpress.service.WordpressServicePages;
import br.com.tecnobiz.camel.component.wordpress.service.WordpressServicePostRevision;
import br.com.tecnobiz.camel.component.wordpress.service.WordpressServicePosts;
import br.com.tecnobiz.camel.component.wordpress.service.WordpressServiceTags;
import br.com.tecnobiz.camel.component.wordpress.service.impl.WordpressServiceCategoriesAdapter;
import br.com.tecnobiz.camel.component.wordpress.service.impl.WordpressServicePagesAdapter;
import br.com.tecnobiz.camel.component.wordpress.service.impl.WordpressServicePostsAdapter;
import br.com.tecnobiz.camel.component.wordpress.service.impl.WordpressServiceTagsAdapter;
import br.com.tecnobiz.camel.component.wordpress.service.impl.WordpressSevicePostRevisionAdapter;

public class WordpressServiceProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(WordpressServiceProvider.class);
    private static final String SRV_NULL_MSG = "Please set the Wordpress Url at init(URL)";

    private WordpressServicePosts servicePosts;
    private WordpressServicePostRevision servicePostRevision;
    private WordpressServiceCategories serviceCategories;
    private WordpressServiceTags serviceTags;
    private WordpressServicePages servicePages;

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
        this.servicePosts = new WordpressServicePostsAdapter(wordpressApiUrl);
        this.servicePostRevision = new WordpressSevicePostRevisionAdapter(wordpressApiUrl);
        this.serviceCategories = new WordpressServiceCategoriesAdapter(wordpressApiUrl);
        this.serviceTags = new WordpressServiceTagsAdapter(wordpressApiUrl);
        this.servicePages = new WordpressServicePagesAdapter(wordpressApiUrl);
        LOGGER.info("Wordpress Component initialized using base URL: {}", wordpressApiUrl);
    }

    public WordpressServicePosts getServicePosts() {
        checkNotNull(servicePosts, SRV_NULL_MSG);
        return servicePosts;
    }

    public WordpressServicePostRevision getServicePostRevision() {
        checkNotNull(servicePostRevision, SRV_NULL_MSG);
        return servicePostRevision;
    }

    public WordpressServiceCategories getServiceCategories() {
        checkNotNull(serviceCategories, SRV_NULL_MSG);
        return serviceCategories;
    }

    public WordpressServiceTags getServiceTags() {
        checkNotNull(serviceTags, SRV_NULL_MSG);
        return serviceTags;
    }
    
    public WordpressServicePages getServicePages() {
        checkNotNull(servicePages, SRV_NULL_MSG);
        return servicePages;
    }

}
