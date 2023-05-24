# README

## Things to study about Struts

### CSRF protection

1. You can do this using the token interceptor.

### POST for destructive actions

1. This is difficult to enforce since Struts2 does not expose the HTTP method to the Actions.
2. It is possible to create a custom interceptor for this, but this is a bit too much.
3. However, we can use the `Token Interceptor` which can also be used for CSRF tokens to enforce the use of forms. 
   This is not the same as enforcing a POST request, but it does enforce the use of a form which will help.

### Validations

