package br.com.tecnobiz.camel.component.wordpress.config;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriParams;
import org.apache.camel.util.StringHelper;

import br.com.tecnobiz.camel.component.wordpress.WordpressConstants;

@UriParams
public class WordpressConfiguration {
    
    @UriParam(description = "The Wordpress API URL from your site, e.g. http://myblog.com/wp-json/wp")
    @Metadata(required = "true")
    private String url;
    @UriParam(defaultValue = WordpressConstants.API_VERSION)
    private String apiVersion;
    /**
     * Wordpress URL in {@link URI} format
     */
    private URI uri;

    public WordpressConfiguration() {

    }

    public String getUrl() {
        return url;
    }
    
    public URI getUri() {
        return uri;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }
    
    public void validate() {
        StringHelper.notEmpty(this.apiVersion, "apiVersion");
        StringHelper.notEmpty(this.url, "url");
        try {
            this.uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Impossible to set Wordpress API URL", e);
        }
    }

}
