package br.com.tecnobiz.camel.component.wordpress.model;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a Wordpress Post.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Post implements Serializable {

    private static final long serialVersionUID = -2077181715632668792L;

    private Date date;

    @JsonProperty("date_gmt")
    private Date dateGmt;

    private WpObject guid;

    private Integer id;

    private String link;

    private Date modified;

    @JsonProperty("modified_gmt")
    private Date modifiedGmt;

    private String slug;

    private PostStatus status;

    private String type;

    private String password;

    private WpObject title;

    private WpObject content;

    private Integer author;

    private WpObject excerpt;

    @JsonProperty("featured_media")
    private Integer featuredMedia;

    @JsonProperty("comment_status")
    private CommentStatus commentStatus;

    @JsonProperty("ping_status")
    private PingStatus pingStatus;

    private Format format;

    private List<WpObject> meta;

    private Boolean stick;

    private String template;

    private List<Integer> categories;

    private List<Integer> tags;

    @JsonProperty("liveblog_likes")
    private Integer liveblogLikes;

    public Post() {
        this.categories = new ArrayList<>();
        this.tags = new ArrayList<>();
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

    public WpObject getGuid() {
        return guid;
    }

    public void setGuid(WpObject guid) {
        this.guid = guid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

    public PostStatus getStatus() {
        return status;
    }

    public void setStatus(PostStatus status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public WpObject getTitle() {
        return title;
    }

    public void setTitle(WpObject title) {
        this.title = title;
    }

    public WpObject getContent() {
        return content;
    }

    public void setContent(WpObject content) {
        this.content = content;
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public WpObject getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(WpObject excerpt) {
        this.excerpt = excerpt;
    }

    public Integer getFeaturedMedia() {
        return featuredMedia;
    }

    public void setFeaturedMedia(Integer featuredMedia) {
        this.featuredMedia = featuredMedia;
    }

    public CommentStatus getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(CommentStatus commentStatus) {
        this.commentStatus = commentStatus;
    }

    public PingStatus getPingStatus() {
        return pingStatus;
    }

    public void setPingStatus(PingStatus pingStatus) {
        this.pingStatus = pingStatus;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public List<WpObject> getMeta() {
        return meta;
    }

    public void setMeta(List<WpObject> meta) {
        this.meta = meta;
    }

    public Boolean isStick() {
        return stick;
    }

    public void setStick(Boolean stick) {
        this.stick = stick;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public List<Integer> getCategories() {
        return categories;
    }

    public void setCategories(List<Integer> categories) {
        this.categories = categories;
    }

    public List<Integer> getTags() {
        return tags;
    }

    public void setTags(List<Integer> tags) {
        this.tags = tags;
    }

    public Integer getLiveblogLikes() {
        return liveblogLikes;
    }

    public void setLiveblogLikes(Integer liveblogLikes) {
        this.liveblogLikes = liveblogLikes;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
            .add("PostID", this.id)
            .add("Link", this.link)
            .add("Author", this.author)
            .toString();
    }

}
