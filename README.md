O.G. Brown Dev Courses Demo: ogbrown-courses
=================================

# Introduction

This project is a Java Web Application that provides an Educational Website to host online class materials for students attending the various courses. The general flow of the website supports:

## Course
- Multiple course offerings, e.g. Start Date 8/1/2017, or 10/25/2017. Most recent accessible directly.
- Multiple Course Labels, e.g. JAVA-1111, XJAV-1122, tracked as the educational agency changes over time. Most recent accessible directly.
- A list of Prerequisites
- Most recent Instructor available
- A list of textbooks
- A default number of class meeting sessions
- A list of course objectives
- A lesson plan by session

 
## CourseOffering
- Provides a specific number of class meeting sessions for each offering.
- allows for numerous instructors per offering
- tracks time logistics (e.g. start date, start time, end date, end time, days of weeks, term) for the offering
- has location logistics information (e.g. location, room, seats)

## Common Course Pages
  Programmatically generates the typical starting pages unique for each course and offering. This saves 100's of statically stored web pages and greatly reduces the setup time for new courses and saves web design time needed for routine materials. 


# Live Demo

To see latest version visit https://dev.ogbrown.com

## Local Demo
In order to run the application you should configure your Database server correctly.
You can do this by modifying the values in `src/main/resources/jdbc-demo.properties`.


You can deploy the application on the Java servlet container after `mvn install`.

# Technologies/Dependencies

This is a demo Java Web Application built using the [Spring Tool Suite](https://spring.io/tools) ([Eclipse](http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/oxygen1a)) configured for Maven with the following dependences: 
- [Spring Framework 4](https://projects.spring.io/spring-framework/)
- [Spring Web MVC](https://projects.spring.io/spring-framework/)
- [Spring Data JPA](https://projects.spring.io/spring-data/)
- Logback
- JUnit 4
- Mockito
- [Hibernate ORM 5](http://hibernate.org/orm/)
- [MySQL](http://mysql.com/)
- EHCache
- Jackson
- Castor XML
- Thymeleaf 2.1
- JSPs
- WebJars
- Bootstrap 3
- JQuery
- HTML
- CSS

Runs on [Apache Tomcat 7+](http://tomcat.apache.org/) and [Java 8](https://java.com/en/).