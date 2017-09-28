package br.com.tecnobiz.camel.component.wordpress.model;

public class PageSearchCriteria extends PublishableSearchCriteria {

    private Integer menuOrder;
    private Integer parent;
    private Integer parentExclude;
    private String filter;
    private PageOrderBy orderBy;

    public PageOrderBy getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(PageOrderBy orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public Integer getParentExclude() {
        return parentExclude;
    }

    public void setParentExclude(Integer parentExclude) {
        this.parentExclude = parentExclude;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

}
