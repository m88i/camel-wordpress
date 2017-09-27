package br.com.tecnobiz.camel.component.wordpress.model;

import java.util.Date;
import java.util.List;

public class PostSearchCriteria extends SearchCriteria {

    private Date after;
    private Date before;
    private List<Integer> author;
    private List<Integer> authorExclude;
    private List<Integer> offset;
    private PostOrderBy orderBy;
    private List<String> slug;
    private PostStatus status;
    private List<String> categories;
    private List<String> categoriesExclude;
    private List<String> tags;
    private List<String> tagsExclude;
    private Boolean stick;
    private Context context;

    public Date getAfter() {
        return after;
    }

    public void setAfter(Date after) {
        this.after = after;
    }

    public Date getBefore() {
        return before;
    }

    public void setBefore(Date before) {
        this.before = before;
    }

    public List<Integer> getAuthor() {
        return author;
    }

    public void setAuthor(List<Integer> author) {
        this.author = author;
    }

    public List<Integer> getAuthorExclude() {
        return authorExclude;
    }

    public void setAuthorExclude(List<Integer> authorExclude) {
        this.authorExclude = authorExclude;
    }

    public List<Integer> getOffset() {
        return offset;
    }

    public void setOffset(List<Integer> offset) {
        this.offset = offset;
    }

    public PostOrderBy getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(PostOrderBy orderBy) {
        this.orderBy = orderBy;
    }

    public List<String> getSlug() {
        return slug;
    }

    public void setSlug(List<String> slug) {
        this.slug = slug;
    }

    public PostStatus getStatus() {
        return status;
    }

    public void setStatus(PostStatus status) {
        this.status = status;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<String> getCategoriesExclude() {
        return categoriesExclude;
    }

    public void setCategoriesExclude(List<String> categoriesExclude) {
        this.categoriesExclude = categoriesExclude;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getTagsExclude() {
        return tagsExclude;
    }

    public void setTagsExclude(List<String> tagsExclude) {
        this.tagsExclude = tagsExclude;
    }

    public Boolean getStick() {
        return stick;
    }

    public void setStick(Boolean stick) {
        this.stick = stick;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

}
