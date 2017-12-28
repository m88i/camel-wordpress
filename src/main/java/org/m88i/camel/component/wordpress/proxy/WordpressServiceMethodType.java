package org.m88i.camel.component.wordpress.proxy;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.m88i.camel.component.wordpress.api.model.Post;
import org.m88i.camel.component.wordpress.api.model.PostSearchCriteria;

import com.google.common.collect.ImmutableMap;

public enum WordpressServiceMethodType {

    LIST_POSTS(WordpressServiceType.POST, "list", true, Collections.singletonMap("searchCriteria", PostSearchCriteria.class)), 
    READ_POST(WordpressServiceType.POST, "retrieve", true, Collections.singletonMap("id", Integer.class)), 
    CREATE_POST(WordpressServiceType.POST, "create", false, Collections.singletonMap("post", Post.class)), 
    UPDATE_POST(WordpressServiceType.POST, "update", false, ImmutableMap.of("id", Integer.class, "post", Post.class)), 
    DELETE_POST(WordpressServiceType.POST, "delete", false, ImmutableMap.of("id", Integer.class, "force", Boolean.class));

    public static final String LIST = "LIST";
    public static final String READ = "READ";
    public static final String CREATE = "CREATE";
    public static final String UPDATE = "UPDATE";
    public static final String DELETE = "DELETE";
    
    private WordpressServiceType service;
    private String method;
    private Map<String, Class<?>> args;
    private boolean readOnly;

    private WordpressServiceMethodType(WordpressServiceType service, String method, boolean readOnly, Map<String, Class<?>> args) {
        this.service = service;
        this.method = method;
        this.args = args;
        this.readOnly = readOnly;
    }

    public Map<String, Class<?>> getArgs() {
        return args;
    }

    public String getMethod() {
        return method;
    }

    public WordpressServiceType getService() {
        return service;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public static List<WordpressServiceMethodType> fromServiceType(WordpressServiceType serviceType, boolean methodReadOnly) {
        return Stream.of(WordpressServiceMethodType.values())
            .filter(m -> m.readOnly == methodReadOnly)
            .collect(Collectors.toList());
    }
}
