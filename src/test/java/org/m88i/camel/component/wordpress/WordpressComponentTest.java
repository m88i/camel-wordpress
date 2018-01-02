package org.m88i.camel.component.wordpress;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.apache.camel.CamelContext;
import org.junit.Test;
import org.m88i.camel.component.wordpress.api.model.PostSearchCriteria;
import org.mockito.Mockito;

public class WordpressComponentTest {

    @Test
    public void testParseUriPropertiesCriteria() throws Exception {
        final WordpressComponent component = new WordpressComponent(Mockito.mock(CamelContext.class));
        final WordpressEndpoint endpoint = (WordpressEndpoint)component
            .createEndpoint("wordpress:post?apiVersion=2&url=http://mysite.com/&criteria.search=test&criteria.page=1&criteria.perPage=10");

        assertThat(endpoint.getConfiguration().getSearchCriteria(), instanceOf(PostSearchCriteria.class));
        assertNotNull(endpoint.getConfiguration().getSearchCriteria());
        assertThat(endpoint.getConfiguration().getSearchCriteria().getPage(), is(1));
        assertThat(endpoint.getConfiguration().getSearchCriteria().getPerPage(), is(10));
        assertThat(endpoint.getConfiguration().getSearchCriteria().getSearch(), is("test"));
    }

}
