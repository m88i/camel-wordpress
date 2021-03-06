package org.wordpress4j.service.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.wordpress4j.model.Comment;
import org.wordpress4j.model.CommentSearchCriteria;
import org.wordpress4j.model.Context;
import org.wordpress4j.service.WordpressServiceComments;
import org.wordpress4j.service.spi.CommentsSPI;

public class WordpressServiceCommentsAdapter extends AbstractWordpressCrudServiceAdapter<CommentsSPI, Comment, CommentSearchCriteria> implements WordpressServiceComments {

    public WordpressServiceCommentsAdapter(final String wordpressUrl, final String apiVersion) {
        super(wordpressUrl, apiVersion);
    }

    @Override
    protected Class<CommentsSPI> getSpiType() {
        return CommentsSPI.class;
    }

    //@formatter:off
    @Override
    public List<Comment> list(CommentSearchCriteria c) {
        checkNotNull(c, "The search criteria must be defined");
        return getSpi().list(this.getApiVersion(),
                             c.getContext(),
                             c.getPage(),
                             c.getPerPage(),
                             c.getSearch(),
                             c.getAfter(),
                             c.getAuthor(),
                             c.getAuthorExclude(),
                             c.getAuthorEmail(),
                             c.getBefore(),
                             c.getExclude(),
                             c.getInclude(),
                             c.getKarma(),
                             c.getOffset(),
                             c.getOrder(),
                             c.getOrderBy(),
                             c.getParent(),
                             c.getParentExclude(),
                             c.getPost(),
                             c.getStatus(),
                             c.getType());
    }
    //@formatter:on

    @Override
    protected Comment doCreate(Comment object) {
        return getSpi().create(getApiVersion(), object);
    }

    @Override
    protected Comment doDelete(Integer id) {
        return getSpi().delete(getApiVersion(), id, false);
    }

    @Override
    protected Comment doUpdate(Integer id, Comment object) {
        return getSpi().update(getApiVersion(), id, object);
    }

    @Override
    protected Comment doRetrieve(Integer entityID, Context context) {
        return getSpi().retrieve(getApiVersion(), entityID, context);
    }

}
