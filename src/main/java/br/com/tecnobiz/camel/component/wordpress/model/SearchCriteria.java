package br.com.tecnobiz.camel.component.wordpress.model;

import static com.google.common.base.MoreObjects.toStringHelper;

public class SearchCriteria {

    private int page;
    private int perPage;
    private String search;
    private Order order;

    public SearchCriteria() {

    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        //@formatter:off
        return toStringHelper(this)
            .add("Query", this.search)
            .add("Page", page)
            .add("Per Page", perPage)
            .addValue(this.order).toString();
    }

}
