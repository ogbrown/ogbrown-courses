O.G. Brown Dev Courses Demo - Monolithic: ogbrown-courses
=================================

## Table of contents

- [Introduction](#introduction)
- [Live Demo](#live-demo)
- [Creator/Author](#creator)
- [Copyright and license](#copyright-and-license)

# Introduction

This project is a monolithic Java Web Application that provides an Educational Website to host online class materials for students attending the various courses. This is a rough-up of a monolithic web application that we will incrementally rearchitect 
into a RESTful Web Service and FrontEnd Web Application that consumes the data from the Web Service. The general business flow of the website supports:

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

To see a current version with the demo data visit <https://web.sbogb.com/ogbrown-courses/>

## Local Demo
In order to run the application you should configure your Database server correctly.
You can do this by modifying the values in `src/main/resources/jdbc-demo.properties`.

You can deploy the application on the Java servlet container after `mvn install`.

## Live Site

To see a live site derived from this project visit <https://dev.ogbrown.com/>


# Technologies/Dependencies

This is a demo Java Web Application built using IntelliJ Idea (JetBrains's Java+ IDE) configured for Maven with the following dependences: 
- Spring Framework 4, Spring Web MVC, Spring Data JPA
- Logback, JUnit 4, Mockito
- Hibernate ORM 5, MySQL, EHCache, 
- Jackson, Castor XML
- Thymeleaf 3
- JSPs
- WebJars, Bootstrap 3, JQuery, HTML, CSS

Runs on Apache Tomcat 7+ and Java 8.

## Creator

**Oz Brown**
- <https://www.linkedin.com/in/ogbrown/>
- <https://twitter.com/devogb>
- <https://github.com/ogbrown>


## Copyright and license

Code and documentation copyright 2017-2019 by the creator/author, Oz Brown. Code released under the [MIT License](https://github.com/ogbrown/ogbrown-courses/blob/master/LICENSE).
