package org.m88i.camel.component.wordpress.proxy;

import org.m88i.camel.component.wordpress.api.service.WordpressService;
import org.m88i.camel.component.wordpress.api.service.WordpressServicePosts;

public enum WordpressServiceType {
    
    POST(WordpressServicePosts.class, "post");
    
    private Class<? extends WordpressService> serviceType;
    private String methodName;
    
    private WordpressServiceType(Class<? extends WordpressService> serviceType, String methodName) {
        this.serviceType = serviceType;
        this.methodName = methodName;
    }
    
    public String getMethodName() {
        return methodName;
    }
    
    @SuppressWarnings("unchecked")
    public <T extends WordpressService> Class<T> getServiceType() {
        return (Class<T>)serviceType;
    }
    
    public static WordpressServiceType fromMethodName(String methodName) {
        return WordpressServiceType.valueOf(methodName.toUpperCase());
    }

}
