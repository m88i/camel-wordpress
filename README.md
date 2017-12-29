# Camel Wordpress

Camel component for [Wordpress API](https://developer.wordpress.org/rest-api/reference/). This is a work in progress, but if you are willing for this component, please reach me at ricardozanini at gmail dot com.

## Usage

Currently only the [Posts API](https://developer.wordpress.org/rest-api/reference/posts/#schema) is supported. Stay tuned for further releases. :)

### Clone & Build

Just clone this repo and build it using Maven:

`mvn install`

Add to your project:

```xml
<dependency>
	<groupId>org.m88i.camel</groupId>
	<artifactId>camel-wordpress</artifactId>
	<version>${camel-wordpress.version}</version>
</dependency>

```

### Configure your route

Use this code piece to set up your route:

```java
public void configure() {
    final WordpressConfiguration configuration = new WordpressConfiguration();
    final WordpressComponent component = new WordpressComponent();
    configuration.setApiVersion("2");
    configuration.setUrl("http://yoursite/wp-json/");
    component.setConfiguration(configuration);
    getContext().addComponent("wordpress", component);
    
    from("wordpress:post?id=1")
      .to("mock:result");
}
```

And you're set!

### Consumer

`wordpress:post` retrieves posts (defaults to 10 posts)  
`wordpress:post?id=1` search for a specific post

<!---
### Producer

`wordpress:post` creates a new post from the `org.m88i.camel.component.wordpress.api.model.Post` class in the message body.  
`wordpress:post?id=1` updates a post based on data `org.m88i.camel.component.wordpress.api.model.Post` from the message body.  
`wordpress:post?id=1&operation=delete` deletes a specific post  
-->

## Future Releases

- Custom Post Types 
- API Authentication
- Producers