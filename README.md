# Camel Wordpress

Camel component for [Wordpress API](https://developer.wordpress.org/rest-api/reference/). This is a work in progress, but if you are willing for this component, please reach me at ricardozanini at gmail dot com.

## Usage

Currently only the [Posts API](https://developer.wordpress.org/rest-api/reference/posts/#schema) is supported. Stay tuned for further releases. :)


### Consumer

`wordpress:post` retrieves posts (defaults to 10 posts)  
`wordpress:post?id=1` search for a specific post

### Producer

`wordpress:post` creates a new post from the `org.88mi.camel.component.wordpress.api.model.Post` class in the message body.  
`wordpress:post?id=1` updates a post based on data `org.88mi.camel.component.wordpress.api.model.Post` from the message body.  
`wordpress:post:delete?id=1` deletes a specific post  