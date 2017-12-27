package org.m88i.camel.component.wordpress.proxy;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.emptyToNull;

import java.util.HashMap;

import org.apache.camel.util.ObjectHelper;
import org.m88i.camel.component.wordpress.WordpressConstants;
import org.m88i.camel.component.wordpress.api.service.WordpressService;
import org.m88i.camel.component.wordpress.api.service.WordpressServiceCategories;
import org.m88i.camel.component.wordpress.api.service.WordpressServiceComments;
import org.m88i.camel.component.wordpress.api.service.WordpressServicePages;
import org.m88i.camel.component.wordpress.api.service.WordpressServicePosts;
import org.m88i.camel.component.wordpress.api.service.WordpressServiceTags;
import org.m88i.camel.component.wordpress.api.service.WordpressServiceTaxonomy;
import org.m88i.camel.component.wordpress.api.service.impl.WordpressServiceCategoriesAdapter;
import org.m88i.camel.component.wordpress.api.service.impl.WordpressServiceCommentsAdapter;
import org.m88i.camel.component.wordpress.api.service.impl.WordpressServicePagesAdapter;
import org.m88i.camel.component.wordpress.api.service.impl.WordpressServicePostsAdapter;
import org.m88i.camel.component.wordpress.api.service.impl.WordpressServiceTagsAdapter;
import org.m88i.camel.component.wordpress.api.service.impl.WordpressServiceTaxonomyAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WordpressServiceProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(WordpressServiceProvider.class);
    
    private HashMap<Class<? extends WordpressService>, WordpressService> services;
    
     private WordpressServiceProvider() {

    }

    private static class ServiceProviderHolder {
        private static final WordpressServiceProvider INSTANCE = new WordpressServiceProvider();
    }

    public static WordpressServiceProvider getInstance() {
        return ServiceProviderHolder.INSTANCE;
    }

    public void init(String wordpressApiUrl) {
        this.init(wordpressApiUrl, WordpressConstants.API_VERSION);
    }

    public void init(String wordpressApiUrl, String apiVersion) {
        checkNotNull(emptyToNull(wordpressApiUrl), "Please inform the Wordpress API url , eg.: http://myblog.com/wp-json/wp");

        if (ObjectHelper.isEmpty(apiVersion)) {
            apiVersion = WordpressConstants.API_VERSION;
        }

        this.services = new HashMap<>();
        this.services.put(WordpressServicePosts.class, new WordpressServicePostsAdapter(wordpressApiUrl, apiVersion));
        this.services.put(WordpressServiceCategories.class, new WordpressServiceCategoriesAdapter(wordpressApiUrl, apiVersion));
        this.services.put(WordpressServiceComments.class, new WordpressServiceCommentsAdapter(wordpressApiUrl, apiVersion));
        this.services.put(WordpressServicePages.class, new WordpressServicePagesAdapter(wordpressApiUrl, apiVersion));
        this.services.put(WordpressServiceTags.class, new WordpressServiceTagsAdapter(wordpressApiUrl, apiVersion));
        this.services.put(WordpressServiceTaxonomy.class, new WordpressServiceTaxonomyAdapter(wordpressApiUrl, apiVersion));
        
        LOGGER.info("Wordpress Service Provider initialized using base URL: {}, API Version {}", wordpressApiUrl, apiVersion);
    }
    
    @SuppressWarnings("unchecked")
    public <T extends WordpressService> T getService(Class<T> wordpressServiceClazz) {
       return (T)this.services.get(wordpressServiceClazz);
    }

}
