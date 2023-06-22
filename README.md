# README

## Running the application

The application is built using Maven. The IDE should pick this up.

```shell
mvn jetty:start
```

You can access the top page of the site from http://localhost:8080/struts_blog/index

## Database

This application depends on a MySQL database. You need to configure it and set up your database tables before running this application.

### Installation

1. Install MySQL for your local development environment.
2. Configure the connection settings in `src/main/java/struts_blog/daos/DaoBase.java`.
3. To set up the tables
   1. Confirm that you have a MySQL dump file in the `db` directory.
   2. Run `mysql -u root -p struts_blog < db/[MySQL dump file name]` (enter password when prompted)

### Seeding the database

You can seed the database by running the `main()` method in `struts_blog.setup.TestSetup`.

## Checking configuration

The Config browser plugin is installed. Access at the following URL.

http://localhost:8080/struts_blog/config-browser/actionNames.action

Information is available here https://struts.apache.org/plugins/config-browser/

## Purpose of this application

This is a Struts2 application that I built to teach developers. A lot of what I intend to teach is not just about Java, but also applicable to web development in general.

Please take a look at the [wiki](https://github.com/naofumi/struts_blog/wiki) for more information.
