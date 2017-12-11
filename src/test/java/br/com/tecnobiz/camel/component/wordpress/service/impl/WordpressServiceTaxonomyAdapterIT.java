package br.com.tecnobiz.camel.component.wordpress.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.junit.Assert.assertThat;

import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.tecnobiz.camel.component.wordpress.WordpressServiceProvider;
import br.com.tecnobiz.camel.component.wordpress.WordpressTestConstants;
import br.com.tecnobiz.camel.component.wordpress.model.Taxonomy;
import br.com.tecnobiz.camel.component.wordpress.service.WordpressServiceTaxonomy;

public class WordpressServiceTaxonomyAdapterIT {

    private static WordpressServiceTaxonomy serviceTaxonomy;

    @BeforeClass
    public static void before() {
        final WordpressServiceProvider serviceProvider = WordpressServiceProvider.getInstance();
        serviceProvider.init(WordpressTestConstants.WORDPRESS_DEMO_URL);
        serviceTaxonomy = serviceProvider.getServiceTaxonomy();
    }

    @Test
    public void testRetrieve() {
        final Taxonomy taxonomy = serviceTaxonomy.retrieve(null, "category");
        assertThat(taxonomy, not(nullValue()));
        assertThat(taxonomy.getName(), not(isEmptyOrNullString()));
    }

    @Test
    public void testList() {
        final Map<String, Taxonomy> taxs = serviceTaxonomy.list(null, null);
        assertThat(taxs, is(not(nullValue())));
        assertThat(taxs.size(), is(2));
    }
}
