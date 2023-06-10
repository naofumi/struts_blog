# README

## Running the application 

The application is build using Maven. The IDE should pick this up.

```shell
mvn jetty:start
```

You can access the top page of the site from http://localhost:8080/struts_blog/index

## Database

This application depends on a MySQL database. 

### Installation

1. Install MySQL for your local development environment.
2. Configure the connection settings in `src/main/java/struts_blog/daos/DaoBase.java`.
3. To set up the tables, consult the SQL dump file in the `db` directory.

### Seeding the database

You can seed the database by running the `main()` method in `struts_blog.setup.TestSetup`.

## Checking configuration

The Config browser plugin is installed. Access at the following URL.

http://localhost:8080/struts_blog/config-browser/actionNames.action

Information is available here https://struts.apache.org/plugins/config-browser/


## Things to study about Struts

### CSRF protection

1. You can do this using the token interceptor.
2. However, I haven't found a good way to work around this when testing. I'm leaving the token interceptor off for the time being.

### POST for destructive actions

1. This is difficult to enforce since Struts2 does not expose the HTTP method to the Actions.
2. It is possible to create a custom interceptor for this, but this is a bit too much.
3. However, we can use the `Token Interceptor` which can also be used for CSRF tokens to enforce the use of forms. 
   This is not the same as enforcing a POST request, but it does enforce the use of a form which will help.
