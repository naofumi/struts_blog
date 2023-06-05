# README

## Running the application 

The application is build using Maven. The IDE should pick this up.

```shell
mvn jetty:start
```

## Checking configuration

The Config browser plugin is installed. Access at the following URL.

http://localhost:8080/struts_blog/config-browser/actionNames.action

Information is available here https://struts.apache.org/plugins/config-browser/


## Things to study about Struts

### CSRF protection

1. You can do this using the token interceptor.
2. However, I haven't found a good way to work around this when testing. I'm leaving it off for the time being.

### POST for destructive actions

1. This is difficult to enforce since Struts2 does not expose the HTTP method to the Actions.
2. It is possible to create a custom interceptor for this, but this is a bit too much.
3. However, we can use the `Token Interceptor` which can also be used for CSRF tokens to enforce the use of forms. 
   This is not the same as enforcing a POST request, but it does enforce the use of a form which will help.

### Testing

The Struts framework provides the `struts2-junit-plugin` package which can be used to write tests that involve the 
framework. Specifically, it provides a mock request in `StrutsTestCase` on which you can set HTTP parameters, etc. and 
then call the action on that.

However, the design of Struts2 is such that the Actions can easily be tested in isolation. This is due to the fact 
that you can inject the HTTP parameters and session values into the Action through setters, and then run the `execute
()` method.

The limitations are

1. You cannot test the JSP views.
2. Documentation on the `StrutsTestCase` is scarce. I am having difficulty getting the sessions to work, for example.

As such, my tentative plan for testing is as follows;

1. Keep logic out of the JSP views as much as possible.
2. Do most of the testing using the simple `TestCase`. Try to avoid using `StrutsTestCase` unless necessary.
3. Specific reasons for using `StrutsTestCase` would be as follows
   1. You need to test validations
   2. You need to test how the Interceptors work
   3. You want to test action chaining

#### Testing the iDeco project

With regard to the iDeCo project, it directly refers to the `ActionContext` or `ServletActionContext` and this 
complicates testing the Actions inside a `TestCase` (I'm not sure how you should mock it). Also, database access 
is not designed around DI, so that is an additional challenge.

Therefore, testing these Actions becomes difficult. I'm not sure how you can work around this.

Ideally, you would like to remove all reference to `ActionContext` and use a different way to achieve the same functionality.

