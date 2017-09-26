package br.com.tecnobiz.camel.component.wordpress.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a partial view of a Post, with base properties only.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class PostObject implements Serializable {

    private static final long serialVersionUID = -1901820243945969584L;

    private Integer author;

    private Date date;

    @JsonProperty("date_gmt")
    private Date dateGmt;

    private Date modified;

    @JsonProperty("modified_gmt")
    private Date modifiedGmt;

    private String slug;


    public PostObject() {

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDateGmt() {
        return dateGmt;
    }

    public void setDateGmt(Date dateGmt) {
        this.dateGmt = dateGmt;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Date getModifiedGmt() {
        return modifiedGmt;
    }

    public void setModifiedGmt(Date modifiedGmt) {
        this.modifiedGmt = modifiedGmt;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }


    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return this.slug;
    }
}
