package br.com.tecnobiz.camel.component.wordpress.service.impl;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;

import br.com.tecnobiz.camel.component.wordpress.WordpressConstants;
import br.com.tecnobiz.camel.component.wordpress.api.PostRevisionsAPI;
import br.com.tecnobiz.camel.component.wordpress.model.Context;
import br.com.tecnobiz.camel.component.wordpress.model.PostRevision;
import br.com.tecnobiz.camel.component.wordpress.service.WordpressServicePostRevision;

public class WordpressSevicePostRevisionAdapter extends AbstractWordpressServiceAdapter<PostRevisionsAPI> implements WordpressServicePostRevision {

    public WordpressSevicePostRevisionAdapter(final String wordpressUrl) {
        super(wordpressUrl);
    }

    private PostRevisionsAPI api;

    @Override
    protected Class<PostRevisionsAPI> getType() {
        return PostRevisionsAPI.class;
    }

    @Override
    protected PostRevisionsAPI getApi() {
        return api;
    }

    @Override
    protected void setApi(PostRevisionsAPI api) {
        this.api = api;
    }

    @Override
    public void delete(int postId, int revisionId) {
        checkArgument(postId > 0, "Please define a post id");
        checkArgument(revisionId > 0, "Please define a revision id");
        this.api.delete(WordpressConstants.API_VERSION, postId, revisionId);
    }

    @Override
    public PostRevision retrieve(int postId, int revisionId, Context context) {
        checkArgument(postId > 0, "Please define a post id");
        checkArgument(revisionId > 0, "Please define a revision id");
        return this.api.retrieveRevision(WordpressConstants.API_VERSION, postId, revisionId, context);
    }

    @Override
    public List<PostRevision> list(int postId, Context context) {
        checkArgument(postId > 0, "Please define a post id");
        return this.api.list(WordpressConstants.API_VERSION, postId, context);
    }

}
