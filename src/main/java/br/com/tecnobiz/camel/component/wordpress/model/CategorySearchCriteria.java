package br.com.tecnobiz.camel.component.wordpress.model;

public class CategorySearchCriteria extends SearchCriteria {

    private CategoryOrderBy orderBy;
    private boolean hideEmpty;
    private Integer parent;
    private Integer postId;
    private String slug;
    
    public CategorySearchCriteria() {

    }

    public CategoryOrderBy getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(CategoryOrderBy orderBy) {
        this.orderBy = orderBy;
    }

    public boolean isHideEmpty() {
        return hideEmpty;
    }

    public void setHideEmpty(boolean hideEmpty) {
        this.hideEmpty = hideEmpty;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
    
        
}
