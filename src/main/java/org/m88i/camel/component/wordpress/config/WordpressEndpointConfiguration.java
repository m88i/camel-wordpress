package org.m88i.camel.component.wordpress.config;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriParams;
import org.apache.camel.util.IntrospectionSupport;
import org.wordpress4j.model.SearchCriteria;

@UriParams
public class WordpressEndpointConfiguration extends WordpressComponentConfiguration {

    @UriParam(description = "The entity ID. Should be passed when the operation performed requires a specific entity, e.g. deleting a post")
    private Integer id;

    @UriParam(description = "The operation name. Required when the component can't figure out the operation by itself.")
    private String operation;

    @UriParam(description = "The criteria to use with complex searches.", prefix = "criteria.", multiValue = true)
    private Map<String, Object> criteriaProperties;

    private SearchCriteria searchCriteria;

    public WordpressEndpointConfiguration() {
    }

    /**
     * The entity id
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * The operation name
     * 
     * @return
     */
    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    /**
     * The search criteria
     * 
     * @return
     */
    public SearchCriteria getSearchCriteria() {
        return searchCriteria;
    }

    public void setSearchCriteria(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    public Map<String, Object> getCriteriaProperties() {
        if(criteriaProperties != null) {
            return Collections.unmodifiableMap(criteriaProperties);
        }
        return null;
    }

    public void setCriteriaProperties(Map<String, Object> criteriaProperties) {
        this.criteriaProperties = Collections.unmodifiableMap(criteriaProperties);
    }

    /**
     * Return all configuration properties on a map.
     * 
     * @return
     */
    public Map<String, Object> asMap() {
        final Map<String, Object> map = new HashMap<>();
        IntrospectionSupport.getProperties(this, map, null);
        return map;
    }

}
