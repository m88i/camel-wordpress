package br.com.tecnobiz.camel.component.wordpress.model;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Describes a object that may be published on the Wordpress engine, eg. a Post,
 * a Page etc.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Publishable extends BasePublishable {

    private static final long serialVersionUID = -2913318702739560478L;

    private WPContent guid;

    private String link;

    private PublishableStatus status;

    private String type;

    private WPContent title;

    private WPContent content;

    private WPContent excerpt;

    private String template;

    private List<WPContent> meta;

    @JsonProperty("comment_status")
    private CommentStatus commentStatus;

    @JsonProperty("ping_status")
    private PingStatus pingStatus;

    @JsonProperty("featured_media")
    private Integer featuredMedia;

    public Publishable() {

    }

    public WPContent getTitle() {
        return title;
    }

    public void setTitle(WPContent title) {
        this.title = title;
    }

    public WPContent getContent() {
        return content;
    }

    public void setContent(WPContent content) {
        this.content = content;
    }

    public WPContent getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(WPContent excerpt) {
        this.excerpt = excerpt;
    }

    public WPContent getGuid() {
        return guid;
    }

    public void setGuid(WPContent guid) {
        this.guid = guid;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public PublishableStatus getStatus() {
        return status;
    }

    public void setStatus(PublishableStatus status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public List<WPContent> getMeta() {
        return meta;
    }

    public void setMeta(List<WPContent> meta) {
        this.meta = meta;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
    
    //@formatter:off
    @Override
    public String toString() {
        return toStringHelper(this)
            .add("ID", this.getId())
            .add("Status", this.getStatus())
            .addValue(this.guid)
            .addValue(this.getTitle()).toString();
    }
    //@formatter:on
}

