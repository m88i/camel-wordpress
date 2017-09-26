package br.com.tecnobiz.camel.component.wordpress.model;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a Wordpress Post.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Post extends PostObject implements Serializable {

    private static final long serialVersionUID = -2077181715632668792L;

    private WpContent guid;

    private Integer id;

    private WpContent title;

    private WpContent content;

    private WpContent excerpt;

    private String link;

    private PostStatus status;

    private String type;

    private String password;

    @JsonProperty("featured_media")
    private Integer featuredMedia;

    @JsonProperty("comment_status")
    private CommentStatus commentStatus;

    @JsonProperty("ping_status")
    private PingStatus pingStatus;

    private Format format;

    private List<WpContent> meta;

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

    public WpContent getTitle() {
        return title;
    }

    public void setTitle(WpContent title) {
        this.title = title;
    }

    public WpContent getContent() {
        return content;
    }

    public void setContent(WpContent content) {
        this.content = content;
    }

    public WpContent getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(WpContent excerpt) {
        this.excerpt = excerpt;
    }

    public WpContent getGuid() {
        return guid;
    }

    public void setGuid(WpContent guid) {
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

    public List<WpContent> getMeta() {
        return meta;
    }

    public void setMeta(List<WpContent> meta) {
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
        return toStringHelper(this).add("PostID", this.getId()).add("Status", this.status).addValue(this.getTitle()).toString();
    }

}
