O.G. Brown Dev Courses Demo: ogbrown-courses
=================================

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


To see latest version visit https://dev.ogbrown.com


In order to run the application you should configure your Database server correctly.
You can do this by modifying the values in `src/main/resources/jdbc-demo.properties`.


You can deploy the application on the Java servlet container after `mvn install`.
