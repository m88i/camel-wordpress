package br.com.tecnobiz.camel.component.wordpress.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WpObject implements Serializable {

    private static final long serialVersionUID = -6355688058047458932L;

    private String rendered;
    
    @JsonProperty("protected")
    private Boolean protected_;
    
    public WpObject() {
        
    }

    public String getRendered() {
        return rendered;
    }

    public void setRendered(String rendered) {
        this.rendered = rendered;
    }

    public Boolean getProtected_() {
        return protected_;
    }

    public void setProtected_(Boolean protected_) {
        this.protected_ = protected_;
    }

}
