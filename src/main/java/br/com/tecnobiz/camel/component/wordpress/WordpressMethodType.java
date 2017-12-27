package br.com.tecnobiz.camel.component.wordpress;

import br.com.tecnobiz.camel.component.wordpress.api.service.WordpressService;
import br.com.tecnobiz.camel.component.wordpress.api.service.WordpressServicePosts;

public enum WordpressMethodType {
    
    POST(WordpressServicePosts.class, "post");
    
    private Class<? extends WordpressService> serviceType;
    private String methodName;
    
    private WordpressMethodType(Class<? extends WordpressService> serviceType, String methodName) {
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
    
    public WordpressMethodType fromMethodName(String methodName) {
        return WordpressMethodType.valueOf(methodName.toUpperCase());
    }

}
