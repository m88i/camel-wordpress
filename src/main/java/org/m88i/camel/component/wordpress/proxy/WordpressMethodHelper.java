package org.m88i.camel.component.wordpress.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.camel.RuntimeCamelException;
import org.apache.camel.util.ObjectHelper;
import org.m88i.camel.component.wordpress.api.service.WordpressService;
import org.m88i.camel.component.wordpress.config.WordpressEndpointConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class WordpressMethodHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(WordpressMethodHelper.class);

    private WordpressMethodHelper() {

    }

    /**
     * Invoke the corresponding method from the service using the args informed.
     * 
     * @param service the service instance provided by
     *            {@link WordpressServiceProvider}
     * @param method the method discovered using
     *            {@link #discoverConsumerMethodCall(WordpressServiceType, WordpressEndpointConfiguration)}
     *            or
     *            {@link #discoverProducerMethodCall(WordpressServiceType, WordpressEndpointConfiguration)}.
     * @param args the mapped values
     * @return the result from the method invocation
     * @throws RuntimeCamelException if something goes wrong. Contains the inner
     *             exception from the method invocation.
     */
    public static Object invokeMethod(WordpressService service, WordpressServiceMethodType method, Map<String, Object> args) throws RuntimeCamelException {
        
        try {
            Map<String, Object> filteredArgs = 
                args.entrySet()
                .stream()
                .filter(a -> method.getArgs().containsKey(a.getKey()))
                .collect(Collectors.toMap(m -> m.getKey(), m -> m.getValue()));
               
            LOGGER.debug("Invoking method {} from service {} with args {}", method, service, filteredArgs);    
            // @formatter:off
            final Method serviceMethod = 
                service
                .getClass()
                .getMethod(method.getMethod(), method.getArgs().values().toArray(new Class[0]));
            
            return serviceMethod.invoke(service, filteredArgs.values().toArray());
            // @formatter:on
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            throw new RuntimeCamelException(String.format("Error while invoking method %s from service %s", method, service), e);
        }
    }

    /**
     * Trying to discover the corresponding method service call for consumer
     * (read only)
     */
    public static WordpressServiceMethodType discoverConsumerMethodCall(WordpressServiceType serviceType, WordpressEndpointConfiguration configuration) {
        List<WordpressServiceMethodType> candidates = WordpressServiceMethodType.fromServiceType(serviceType, true);
        if (ObjectHelper.isEmpty(configuration.getId())) {
            return candidates.stream().filter(c -> c.toString().startsWith(WordpressServiceMethodType.LIST)).findFirst().get();
        } else {
            return candidates.stream().filter(c -> c.toString().startsWith(WordpressServiceMethodType.READ)).findFirst().get();
        }
    }

    /**
     * Trying to discover the corresponding method service call for producer
     * (write only)
     */
    public static WordpressServiceMethodType discoverProducerMethodCall(WordpressServiceType serviceType, WordpressEndpointConfiguration configuration) {
        List<WordpressServiceMethodType> candidates = WordpressServiceMethodType.fromServiceType(serviceType, false);
        if (ObjectHelper.isEmpty(configuration.getId())) {
            return candidates.stream().filter(c -> c.toString().startsWith(WordpressServiceMethodType.CREATE)).findFirst().get();
        } else {
            if (ObjectHelper.isEmpty(configuration.getOperation())) {
                return candidates.stream().filter(c -> c.toString().startsWith(WordpressServiceMethodType.UPDATE)).findFirst().get();
            }
            return candidates.stream().filter(c -> c.toString().toUpperCase().startsWith(configuration.getOperation())).findFirst().get();
        }
    }

}
