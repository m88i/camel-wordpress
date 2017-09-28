package br.com.tecnobiz.camel.component.wordpress.api;

/**
 * Mark interface for Wordpress APIs
 * 
 * @param <T>
 */
public interface WordpressAPI<T> {

    T create(String apiVersion, T object);

    T update(String apiVersion, int id, T object);

    void delete(String apiVersion, int id, boolean force);
}
