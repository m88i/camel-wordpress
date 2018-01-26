**Note:** This component will be available in Apache Camel 2.21.0 out of the box. This repository will no longer be maintained. If you want the most recent version, please switch to Apache Camel.  


# Camel Wordpress

Camel component for [Wordpress API](https://developer.wordpress.org/rest-api/reference/). This is a work in progress, but if you are willing for this component, please reach me at ricardozanini at gmail dot com.

## Usage

Currently only the **Posts** and **Users** operations are supported. Stay tuned for further releases. :)

### Clone & Build

Just clone this repo and build it using Maven:

`mvn clean install -DskipTests`

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
    configuration.setUrl("http://yoursite.com/wp-json/");
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

### Producer

`wordpress:post` creates a new post from the `org.wordpress4j.model.Post` class in the message body.  
`wordpress:post?id=1` updates a post based on data `org.wordpress4j.model.Post` from the message body.  
`wordpress:post:delete?id=1` deletes a specific post

## Authentication

Producers that perform write operations (e.g. create a new post) [must have an authenticated user](https://developer.wordpress.org/rest-api/using-the-rest-api/authentication/) to do so. The standard authentication mechanism used by Wordpress is cookie. Unfortunately this method is not supported outside Wordpress environment because it's rely on [nonce](https://codex.wordpress.org/WordPress_Nonces) internal function.

There's some alternatives to use the Wordpress API without nonces, but requires specific plugin installations.

At this time, `camel-wordpress` only supports Basic Authentication (more to come). To configure it, you must install the [Basic-Auth Wordpress plugin](https://github.com/WP-API/Basic-Auth) and pass the credentials to the endpoint:

`from("direct:deletePost").to("wordpress:post:delete?id=9&user=ben&password=password123").to("mock:resultDelete");`

**It's not recommend to use Basic Authentication in production!!**. If you plan to use this component in production environments, please let me know so I may prioritize this feature in future releases.


## Examples

TBD

## Future Releases

- Search for producers, e.g. `to("wordpress:post:search?criteria.name='Ben'")`
- Custom Namespaces 
- More Wordpress API operations e.g. Media, Comments, etc.
- Discovery
- Custom Converters
