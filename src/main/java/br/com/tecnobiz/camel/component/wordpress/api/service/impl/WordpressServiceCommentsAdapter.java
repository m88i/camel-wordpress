package br.com.tecnobiz.camel.component.wordpress.api.service.impl;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import br.com.tecnobiz.camel.component.wordpress.api.model.Comment;
import br.com.tecnobiz.camel.component.wordpress.api.model.CommentSearchCriteria;
import br.com.tecnobiz.camel.component.wordpress.api.model.Context;
import br.com.tecnobiz.camel.component.wordpress.api.service.WordpressServiceComments;
import br.com.tecnobiz.camel.component.wordpress.api.service.spi.CommentsAPI;

public class WordpressServiceCommentsAdapter extends AbstractWordpressCrudServiceAdapter<CommentsAPI, Comment> implements WordpressServiceComments {

    public WordpressServiceCommentsAdapter(final String wordpressUrl, final String apiVersion) {
        super(wordpressUrl, apiVersion);
    }

    @Override
    protected Class<CommentsAPI> getApiType() {
        return CommentsAPI.class;
    }

    //@formatter:off
    @Override
    public List<Comment> list(CommentSearchCriteria c) {
        checkNotNull(c, "The search criteria must be defined");
        return getApi().list(this.getApiVersion(),
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
    public Comment retrieve(int id, Context context) {
        checkArgument(id > 0, "Please define a comment id");
        return getApi().retrieve(this.getApiVersion(), id, context);
    }

}
