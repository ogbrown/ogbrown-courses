USE `ogbrown_courses_demo`;
-- MySQL dump 10.13  Distrib 5.7.20, for Linux (x86_64)
--
-- Host: sbogbrdse2.crvmjxdhbtvr.us-east-2.rds.amazonaws.com    Database: ogbrown_courses_demo
-- ------------------------------------------------------
-- Server version	5.6.29-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Course`
--

DROP TABLE IF EXISTS `Course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Course` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `courseLabel` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `instructor` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `numberOfSessions` smallint(6) DEFAULT NULL,
  `shortTitle` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `title` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `urlSlug` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_4q1l1lo98om8pajg4fw8e1tbd` (`urlSlug`)
) ENGINE=InnoDB AUTO_INCREMENT=1000000 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Course`
--

LOCK TABLES `Course` WRITE;
/*!40000 ALTER TABLE `Course` DISABLE KEYS */;
INSERT INTO `Course` VALUES (1,'XJVA 0103 0','Oz Brown',6,'Some1','Some Course 1','some1'),(3,'XJVA 0104 0','Oz Brown',7,'Some2','Some Course 2','some2'),(6,'XJVA 1201 0','Oz Brown',8,'JWAD1','Some Course 3','some3'),(9,'XWEB 0601 0','Oz Brown',5,'Another4','Another Course 4','anot4'),(999999,'ALL','Oz Brown',1,'ALL','All Courses','all');
/*!40000 ALTER TABLE `Course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CourseLabels`
--

DROP TABLE IF EXISTS `CourseLabels`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CourseLabels` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `label` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `course_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb90oea4qrlejb0s0lu8b9lygg` (`course_id`),
  CONSTRAINT `FK_11wj0mh7nemslsqjqd0enmxp0` FOREIGN KEY (`course_id`) REFERENCES `Course` (`id`),
  CONSTRAINT `FKb90oea4qrlejb0s0lu8b9lygg` FOREIGN KEY (`course_id`) REFERENCES `Course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CourseLabels`
--

LOCK TABLES `CourseLabels` WRITE;
/*!40000 ALTER TABLE `CourseLabels` DISABLE KEYS */;
INSERT INTO `CourseLabels` VALUES (1,'XJVA 0103 0','2017-03-30 14:47:28',1),(3,'XJVA 0104 0','2017-03-30 14:47:28',3),(6,'XJVA 1201 0','2017-03-30 14:47:28',6),(9,'XWEB 0601 0','2017-03-30 14:47:28',9),(11,'ALL','2017-03-30 14:47:28',999999);
/*!40000 ALTER TABLE `CourseLabels` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CourseOffering`
--

DROP TABLE IF EXISTS `CourseOffering`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CourseOffering` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `contEdHours` float NOT NULL,
  `courseNumber` int(11) NOT NULL,
  `daysOfWeek` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `end` date DEFAULT NULL,
  `endTime` time DEFAULT NULL,
  `location` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `room` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sessionCount` int(11) NOT NULL,
  `start` date DEFAULT NULL,
  `startTime` time DEFAULT NULL,
  `term` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `termNumber` int(11) NOT NULL,
  `COURSE_ID` bigint(20) DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `seats` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_3pttw0vnf366y9gtm9esfmlsi` (`COURSE_ID`),
  CONSTRAINT `FK_3pttw0vnf366y9gtm9esfmlsi` FOREIGN KEY (`COURSE_ID`) REFERENCES `Course` (`id`),
  CONSTRAINT `FKnfv3l863v08kobe9qumu0tdi3` FOREIGN KEY (`COURSE_ID`) REFERENCES `Course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CourseOffering`
--

LOCK TABLES `CourseOffering` WRITE;
/*!40000 ALTER TABLE `CourseOffering` DISABLE KEYS */;
INSERT INTO `CourseOffering` VALUES (17,1.5,77133,'F','2015-07-31','21:30:00','CYC','202',5,'2015-06-26','18:30:00','Summer',100001,9,'ARCHIVED',16),(27,2.4,70622,'W','2017-10-18','21:30:00','CYC','302',6,'2017-08-30','18:30:00','Fall',100002,1,'COMPLETED',16),(28,2.4,70661,'W','2017-12-20','21:30:00','CYC','111',7,'2017-10-25','18:30:00','Fall',100002,3,'IN_PROGRESS_CLOSED',16),(29,2.4,73506,'S','2016-04-02','12:00:00','CYC','123',8,'2016-01-30','09:00:00','Winter',100003,6,'COMPLETED',16);
/*!40000 ALTER TABLE `CourseOffering` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CourseSession`
--

DROP TABLE IF EXISTS `CourseSession`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CourseSession` (
  `courseid` bigint(20) NOT NULL DEFAULT '0',
  `sessionnumber` smallint(6) NOT NULL,
  PRIMARY KEY (`courseid`,`sessionnumber`),
  CONSTRAINT `FK_kxovit7vv0jcmblupa16yns7h` FOREIGN KEY (`courseid`) REFERENCES `Course` (`id`),
  CONSTRAINT `FKte9f7lhbgus82kngcq5oqlmpr` FOREIGN KEY (`courseid`) REFERENCES `Course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CourseSession`
--

LOCK TABLES `CourseSession` WRITE;
/*!40000 ALTER TABLE `CourseSession` DISABLE KEYS */;
INSERT INTO `CourseSession` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(3,1),(3,2),(3,3),(3,4),(3,5),(3,6),(3,7),(3,8),(6,1),(6,2),(6,3),(6,4),(6,5),(6,6),(6,7),(6,8),(9,1),(999999,1);
/*!40000 ALTER TABLE `CourseSession` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Instructor`
--

DROP TABLE IF EXISTS `Instructor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Instructor` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `emailInst` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `firstName` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lastName` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `middleInitial` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nickname` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phoneInst` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `suffix` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `website` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Instructor`
--

LOCK TABLES `Instructor` WRITE;
/*!40000 ALTER TABLE `Instructor` DISABLE KEYS */;
INSERT INTO `Instructor` VALUES (1,'education@ogbrown.com','(anytime, allow 24 hour response time)','Oswald','Brown','G.','Oz','866-220-4777','(day/evening hours, allow 24 hour response time)','III','http://dev.ogbrown.com'),(9999,'tbd-email','(tbd)','tbb-fname','tbd-lname','','tbd-nickname','tbd-phone','(tbd)','','http://dev.ogbrown.com');
/*!40000 ALTER TABLE `Instructor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LessonPlan`
--

DROP TABLE IF EXISTS `LessonPlan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LessonPlan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `defaultSession` int(11) NOT NULL,
  `lesson` text CHARACTER SET utf8,
  `lessonOrd` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LessonPlan`
--

LOCK TABLES `LessonPlan` WRITE;
/*!40000 ALTER TABLE `LessonPlan` DISABLE KEYS */;
INSERT INTO `LessonPlan` VALUES (8,1,'lesson plan number 8',5),(9,1,'lesson plan number 9',10),(10,1,'lesson plan number 10',15),(11,1,'lesson plan number 11',20),(12,2,'lesson plan number 12',5),(13,2,'lesson plan number 13',10),(14,2,'lesson plan number 14',15),(15,2,'lesson plan number 15',20),(16,2,'lesson plan number 16',25),(17,3,'lesson plan number 17',5),(18,4,'lesson plan number 18',5),(19,5,'lesson plan number 19',5),(20,6,'lesson plan number 20',5),(21,7,'lesson plan number 21',5),(22,8,'lesson plan number 22',5),(23,1,'lesson plan number 23',5),(24,1,'lesson plan number 24',10),(25,2,'lesson plan number 25',10),(26,2,'lesson plan number 26',5),(27,3,'lesson plan number 27',5),(28,3,'lesson plan number 28',10),(29,4,'lesson plan number 29',5),(30,5,'lesson plan number 30',5),(31,5,'lesson plan number 31',10),(32,5,'lesson plan number 32',15),(33,6,'lesson plan number 33',10),(34,6,'lesson plan number 34',5),(35,7,'lesson plan number 35',5),(36,8,'lesson plan number 36',5),(37,1,'lesson plan number 37',5),(38,1,'lesson plan number 38',10),(39,1,'lesson plan number 39',15),(40,1,'lesson plan number 40',20),(41,2,'lesson plan number 41',5),(42,2,'lesson plan number 42',10),(43,3,'lesson plan number 43',5),(44,3,'lesson plan number 44',10),(45,3,'lesson plan number 45',15),(46,3,'lesson plan number 46',20),(47,4,'lesson plan number 47',5),(48,4,'lesson plan number 48',10),(49,5,'lesson plan number 49',5),(50,5,'lesson plan number 50',10),(51,5,'lesson plan number 51',15),(52,6,'lesson plan number 52',5),(53,6,'lesson plan number 53',10),(54,6,'lesson plan number 54',15),(55,6,'lesson plan number 55',20),(56,7,'lesson plan number 56',5),(57,7,'lesson plan number 57',10),(58,7,'lesson plan number 58',15),(59,8,'lesson plan number 59',5),(60,8,'lesson plan number 60',10);
/*!40000 ALTER TABLE `LessonPlan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `NoClassDate`
--

DROP TABLE IF EXISTS `NoClassDate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `NoClassDate` (
  `day` date NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`day`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NoClassDate`
--

LOCK TABLES `NoClassDate` WRITE;
/*!40000 ALTER TABLE `NoClassDate` DISABLE KEYS */;
INSERT INTO `NoClassDate` VALUES ('2017-08-18','All College Day (campuses closed)'),('2017-09-04','Labor Day Holiday (campuses closed)'),('2017-11-22','Thankgiving Holiday (campuses closed)'),('2017-11-23','Thankgiving Holiday (campuses closed)'),('2017-11-24','Thankgiving Holiday (campuses closed)'),('2017-11-25','Thankgiving Holiday (campuses closed)'),('2017-11-26','Thankgiving Holiday (campuses closed)'),('2017-12-23','Winter Break (campuses closed)'),('2017-12-24','Winter Break (campuses closed)'),('2017-12-25','Winter Break (campuses closed)'),('2017-12-26','Winter Break (campuses closed)'),('2017-12-27','Winter Break (campuses closed)'),('2017-12-28','Winter Break (campuses closed)'),('2017-12-29','Winter Break (campuses closed)'),('2017-12-30','Winter Break (campuses closed)'),('2017-12-31','Winter Break (campuses closed)'),('2018-01-01','Winter Break (campuses closed)'),('2018-01-02','Winter Break (campuses closed)'),('2018-01-15','MLK Holiday (campuses closed)'),('2018-03-16','Spring Break (campuses closed)'),('2018-03-17','Spring Break (campuses closed)'),('2018-03-18','Spring Break (campuses closed)'),('2018-03-30','Spring Holiday (campuses closed)'),('2018-03-31','Spring Holiday (campuses closed)'),('2018-04-01','Spring Holiday (campuses closed)'),('2018-05-28','Memorial Day Holiday (campuses closed)'),('2018-07-04','Independence Day Holiday (campuses closed)');
/*!40000 ALTER TABLE `NoClassDate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Objective`
--

DROP TABLE IF EXISTS `Objective`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Objective` (
  `id` bigint(20) NOT NULL,
  `objective` text CHARACTER SET utf8,
  `objord` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Objective`
--

LOCK TABLES `Objective` WRITE;
/*!40000 ALTER TABLE `Objective` DISABLE KEYS */;
INSERT INTO `Objective` VALUES (1,'<h3>Upon completion, you will:</h3>\n<ul>\n<li>Be able to develop simple Java applications,</li>\n<li>Understand elementary object-oriented programming</li>\n<li>Gain a firm foundation of Java concepts.</li>\n<li>Be able to use the Eclipse IDE to develop Java applications.</li>\n</ul>',1),(2,'<h3>Student Expectations:</h3>\n<ul>\n<li>Students will attend as many of the scheduled classes as possible. 8 Sessions required for 90% attendance.</li>\n<li>Students will be personally responsible for any assignments given in class.</li>\n<li>Students will help the instructor conduct class in a professional and mature manner <img src=\"http://dev.ogbrown.com/wrd1/wp-includes/images/smilies/simple-smile.png\" alt=\":-)\" class=\"wp-smiley\" style=\"height: 1em; max-height: 1em;\" /></li>\n<li>Students will have fun and learn.</li>\n</ul>',2),(3,'<h3>Upon completion, you will:</h3>\n<ul>\n<li>Be able to develop simple Java applications,</li>\n<li>Understand elementary object-oriented programming</li>\n<li>Gain a firm foundation of Java concepts.</li>\n<li>Be able to use the Eclipse IDE to develop Java applications.</li>\n</ul>',1),(4,'<h3>Upon completion, you will:</h3>\n<ul>\n<li>Be able to develop simple Java applications,</li>\n<li>Understand elementary object-oriented programming</li>\n<li>Gain a firm foundation of Java concepts.</li>\n<li>Be able to use the Eclipse IDE to develop Java applications.</li>\n</ul>',1);
/*!40000 ALTER TABLE `Objective` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Page`
--

DROP TABLE IF EXISTS `Page`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Page` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` text COLLATE utf8_unicode_ci,
  `contentHeader` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `metaDescription` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nextPageLinkOverride` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `notes` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pageOrd` smallint(6) DEFAULT NULL,
  `prevPageLinkOverride` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `published` bit(1) DEFAULT NULL,
  `title` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `urlSlug` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `protectedPage` bit(1) DEFAULT NULL,
  `folderUrlSlug` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_85uxlhun6n95cd6ko6mbr2ekw` (`urlSlug`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Page`
--

LOCK TABLES `Page` WRITE;
/*!40000 ALTER TABLE `Page` DISABLE KEYS */;
INSERT INTO `Page` VALUES (1,'','Instructor Led - Courses','SbOGB.com Training Site for Collin.Edu Java and Web Applications Development Courses.','',NULL,1,NULL,'','Java Web Application Development II | SbOGB.com Courses','',NULL,NULL),(2,'		<div id=\"top\" class=\"row mytopofpage\">			<h2>Terms and Conditions</h2>			<p>You can use this website only if you read and agree to the				following terms and conditions.</p>			<p>				<a href=\"#top\">Back to Top</a>			</p>		</div>','Terms of Use','SbOGB.com Terms of Use Training Site for Collin.Edu Java Web Applications Development Courses.',NULL,NULL,99,NULL,'','Terms of Use','terms-and-privacy-policy',NULL,NULL),(3,'		<div class=\"row mytopofpage\">			<h4>Contacting me...</h4>			<p>				If you have any questions please use the E-mail form linked here: <a					href=\"http://dev.ogbrown.com/contact-us/\"					title=\"Contact O.G. Brown\">Send Me A Message Today</a>			</p>		</div>','Contact Info','SbOGB.com Terms of Use Training Site for Collin.Edu Java Web Applications Development Courses.',NULL,NULL,95,NULL,'','Contact Info','contact-us',NULL,NULL),(4,NULL,'About','SbOGB.com About Us Training Site for Collin.Edu Java Web Applications Development Courses.','',NULL,98,NULL,'','About','about-us',NULL,NULL),(9,'common-content-page1','Common Page 1','SbOGB.com Training Site for Collin.Edu Java Web Applications Development Courses.',NULL,NULL,10,NULL,'','Welcome','common-page1','\0','common'),(10,'common-content-page2','Common Page 2','SbOGB.com Training Site for Collin.Edu Java Web Applications Development Courses.',NULL,NULL,11,NULL,'','Schedule','common-page2','\0','common'),(11,'common-content-page3','Common Page 3','SbOGB.com Training Site for Collin.Edu Java Web Applications Development Courses.',NULL,NULL,12,NULL,'','Prerequisites','common-page3','\0','common'),(12,'common-content-page4','Common Page 4','SbOGB.com Training Site for Collin.Edu Java Web Applications Development Courses.',NULL,NULL,13,NULL,'','TextBook','common-page4','\0','common'),(13,'common-content-page5','Common Page 5','SbOGB.com Training Site for Collin.Edu Java Web Applications Development Courses.',NULL,NULL,14,NULL,'','Objectives','common-page5','\0','common');
/*!40000 ALTER TABLE `Page` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Page_metaKeywords`
--

DROP TABLE IF EXISTS `Page_metaKeywords`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Page_metaKeywords` (
  `Page_id` bigint(20) NOT NULL,
  `metaKeywords` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  KEY `FK_qtb54gduea46mj57uw6dhjhjg` (`Page_id`),
  CONSTRAINT `FK_qtb54gduea46mj57uw6dhjhjg` FOREIGN KEY (`Page_id`) REFERENCES `Page` (`id`),
  CONSTRAINT `FKrypg1fsk9imrevo4k0fhr4sjl` FOREIGN KEY (`Page_id`) REFERENCES `Page` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Page_metaKeywords`
--

LOCK TABLES `Page_metaKeywords` WRITE;
/*!40000 ALTER TABLE `Page_metaKeywords` DISABLE KEYS */;
INSERT INTO `Page_metaKeywords` VALUES (1,'Hibernate'),(1,'Webapps'),(1,'Spring'),(1,'Courses'),(1,'Java'),(2,'Conditions'),(2,'Terms'),(2,'Spring'),(2,'Courses'),(2,'Java'),(3,'Contact'),(3,'Spring'),(3,'Contact Us'),(3,'Courses'),(3,'Java'),(4,'About Us'),(4,'Java'),(4,'Spring'),(4,'Hibernate'),(4,'Web Apps');
/*!40000 ALTER TABLE `Page_metaKeywords` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TextBook`
--

DROP TABLE IF EXISTS `TextBook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TextBook` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `edition` smallint(6) NOT NULL,
  `isbn` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `publishedDate` date DEFAULT NULL,
  `publisher` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `title` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TextBook`
--

LOCK TABLES `TextBook` WRITE;
/*!40000 ALTER TABLE `TextBook` DISABLE KEYS */;
INSERT INTO `TextBook` VALUES (1,'Some Author 1',0,'978-1-999999-99-9','2015-06-01','A Publishers','Beginning Book'),(2,'Some Author 2',3,'978-1-999999-99-9','2014-06-01','A Publishers','More Book'),(3,'Some Author 3',4,'978-1-999999-99-9','2015-06-01','A Publications','Even More Book');
/*!40000 ALTER TABLE `TextBook` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `children_parents_pages`
--

DROP TABLE IF EXISTS `children_parents_pages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `children_parents_pages` (
  `childid` bigint(20) NOT NULL,
  `parentid` bigint(20) NOT NULL,
  KEY `FKt3cdkccgf0a2ho5qct1b6ayp8` (`parentid`),
  KEY `FKsygyobwv4ku7k201ct85ul64r` (`childid`),
  CONSTRAINT `FKsygyobwv4ku7k201ct85ul64r` FOREIGN KEY (`childid`) REFERENCES `Page` (`id`),
  CONSTRAINT `FKt3cdkccgf0a2ho5qct1b6ayp8` FOREIGN KEY (`parentid`) REFERENCES `Page` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `children_parents_pages`
--

LOCK TABLES `children_parents_pages` WRITE;
/*!40000 ALTER TABLE `children_parents_pages` DISABLE KEYS */;
/*!40000 ALTER TABLE `children_parents_pages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_objectives`
--

DROP TABLE IF EXISTS `course_objectives`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_objectives` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `objective_id` bigint(20) NOT NULL,
  `course_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqrdjhe1vx6dqetng584es7mcu` (`course_id`),
  KEY `FKp3h909yv5anol9n0uo1b7n18m` (`objective_id`),
  CONSTRAINT `FK_760pqlpkml1s3fmxeluwe95b2` FOREIGN KEY (`course_id`) REFERENCES `Course` (`id`),
  CONSTRAINT `FKp3h909yv5anol9n0uo1b7n18m` FOREIGN KEY (`objective_id`) REFERENCES `Objective` (`id`),
  CONSTRAINT `FKqrdjhe1vx6dqetng584es7mcu` FOREIGN KEY (`course_id`) REFERENCES `Course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_objectives`
--

LOCK TABLES `course_objectives` WRITE;
/*!40000 ALTER TABLE `course_objectives` DISABLE KEYS */;
INSERT INTO `course_objectives` VALUES (1,1,1),(2,2,1),(3,3,3),(4,2,3),(5,4,6),(6,2,6);
/*!40000 ALTER TABLE `course_objectives` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_prereqs`
--

DROP TABLE IF EXISTS `course_prereqs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_prereqs` (
  `id` bigint(20) NOT NULL,
  `prereq_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`,`prereq_id`),
  KEY `FK4i20l7v4tcp12gixpnwnavthc` (`prereq_id`),
  CONSTRAINT `FK11ydc9cng2o21hket4wkqdih` FOREIGN KEY (`id`) REFERENCES `Course` (`id`),
  CONSTRAINT `FK4i20l7v4tcp12gixpnwnavthc` FOREIGN KEY (`prereq_id`) REFERENCES `Course` (`id`),
  CONSTRAINT `FK_meamg1vc1fmw5ip749s37etys` FOREIGN KEY (`id`) REFERENCES `Course` (`id`),
  CONSTRAINT `FK_o59br2luk5gxl9fkfqggrtejk` FOREIGN KEY (`prereq_id`) REFERENCES `Course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_prereqs`
--

LOCK TABLES `course_prereqs` WRITE;
/*!40000 ALTER TABLE `course_prereqs` DISABLE KEYS */;
INSERT INTO `course_prereqs` VALUES (3,1),(6,1),(6,3);
/*!40000 ALTER TABLE `course_prereqs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_textbooks`
--

DROP TABLE IF EXISTS `course_textbooks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_textbooks` (
  `id` bigint(20) NOT NULL,
  `textbook_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`,`textbook_id`),
  KEY `FKfrehn2ohpxv7vf6hbk733crrg` (`textbook_id`),
  CONSTRAINT `FK_ibe93akbv38sa7bi6faoj99w9` FOREIGN KEY (`id`) REFERENCES `Course` (`id`),
  CONSTRAINT `FK_rfnfomi2akelag8d62scthnbv` FOREIGN KEY (`textbook_id`) REFERENCES `TextBook` (`id`),
  CONSTRAINT `FKfrehn2ohpxv7vf6hbk733crrg` FOREIGN KEY (`textbook_id`) REFERENCES `TextBook` (`id`),
  CONSTRAINT `FKsafc8bwk8hsl1bweoq87b6n6a` FOREIGN KEY (`id`) REFERENCES `Course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_textbooks`
--

LOCK TABLES `course_textbooks` WRITE;
/*!40000 ALTER TABLE `course_textbooks` DISABLE KEYS */;
INSERT INTO `course_textbooks` VALUES (1,1),(3,1),(6,2);
/*!40000 ALTER TABLE `course_textbooks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courseoffering_instructors`
--

DROP TABLE IF EXISTS `courseoffering_instructors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `courseoffering_instructors` (
  `CourseOffering_id` bigint(20) NOT NULL,
  `instructors_id` bigint(20) NOT NULL,
  KEY `FK42lf6eco538wpisdkwylypva9` (`CourseOffering_id`),
  KEY `FK1diqxfsafqj2u37rojv8hbrl6` (`instructors_id`),
  CONSTRAINT `FK1diqxfsafqj2u37rojv8hbrl6` FOREIGN KEY (`instructors_id`) REFERENCES `Instructor` (`id`),
  CONSTRAINT `FK42lf6eco538wpisdkwylypva9` FOREIGN KEY (`CourseOffering_id`) REFERENCES `CourseOffering` (`id`),
  CONSTRAINT `FK_t8edy4vheget2o6gl4jldjr89` FOREIGN KEY (`CourseOffering_id`) REFERENCES `CourseOffering` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courseoffering_instructors`
--

LOCK TABLES `courseoffering_instructors` WRITE;
/*!40000 ALTER TABLE `courseoffering_instructors` DISABLE KEYS */;
INSERT INTO `courseoffering_instructors` VALUES (28,9999),(29,9999),(27,9999),(17,9999);
/*!40000 ALTER TABLE `courseoffering_instructors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coursesession_lessonplan`
--

DROP TABLE IF EXISTS `coursesession_lessonplan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coursesession_lessonplan` (
  `courseid` bigint(20) NOT NULL,
  `sessionnumber` smallint(6) NOT NULL,
  `lessonplanid` bigint(20) NOT NULL,
  KEY `FKl1l4obqx8mst5clsprq6l4145` (`courseid`,`sessionnumber`),
  KEY `FKqicls0rgto0ge7hu67pc04n77` (`lessonplanid`),
  CONSTRAINT `FK_g2jh4knxqrmkwip5ikvi7e7xs` FOREIGN KEY (`courseid`, `sessionnumber`) REFERENCES `CourseSession` (`courseid`, `sessionnumber`),
  CONSTRAINT `FKl1l4obqx8mst5clsprq6l4145` FOREIGN KEY (`courseid`, `sessionnumber`) REFERENCES `CourseSession` (`courseid`, `sessionnumber`),
  CONSTRAINT `FKqicls0rgto0ge7hu67pc04n77` FOREIGN KEY (`lessonplanid`) REFERENCES `LessonPlan` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coursesession_lessonplan`
--

LOCK TABLES `coursesession_lessonplan` WRITE;
/*!40000 ALTER TABLE `coursesession_lessonplan` DISABLE KEYS */;
INSERT INTO `coursesession_lessonplan` VALUES (1,1,8),(1,1,9),(1,1,10),(1,1,11),(1,2,12),(1,2,13),(1,2,14),(1,2,15),(1,2,16),(1,3,17),(1,4,18),(1,5,19),(1,6,20),(1,7,21),(1,8,22),(3,1,23),(3,1,24),(3,2,25),(3,2,26),(3,3,27),(3,3,28),(3,4,29),(3,5,30),(3,5,31),(3,5,32),(3,6,33),(3,6,34),(3,7,35),(3,8,36),(6,1,37),(6,1,38),(6,1,39),(6,1,40),(6,2,41),(6,2,42),(6,3,43),(6,3,44),(6,3,45),(6,3,46),(6,4,47),(6,4,48),(6,5,49),(6,5,50),(6,5,51),(6,6,52),(6,6,53),(6,6,54),(6,6,55),(6,7,56),(6,7,57),(6,7,58),(6,8,59),(6,8,60);
/*!40000 ALTER TABLE `coursesession_lessonplan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coursesessions_pages`
--

DROP TABLE IF EXISTS `coursesessions_pages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coursesessions_pages` (
  `pageid` bigint(20) NOT NULL,
  `courseid` bigint(20) NOT NULL,
  `sessionnumber` smallint(6) NOT NULL,
  PRIMARY KEY (`pageid`,`courseid`,`sessionnumber`),
  UNIQUE KEY `UK_k2kcvp0aik2w5aud40c1jtn9c` (`pageid`),
  KEY `FK_7igrpx1georshtc9ommi1nwl5` (`courseid`,`sessionnumber`),
  CONSTRAINT `FK6qg3jfk24sq0psg85meq75y4t` FOREIGN KEY (`courseid`, `sessionnumber`) REFERENCES `CourseSession` (`courseid`, `sessionnumber`),
  CONSTRAINT `FK_7igrpx1georshtc9ommi1nwl5` FOREIGN KEY (`courseid`, `sessionnumber`) REFERENCES `CourseSession` (`courseid`, `sessionnumber`),
  CONSTRAINT `FK_k2kcvp0aik2w5aud40c1jtn9c` FOREIGN KEY (`pageid`) REFERENCES `Page` (`id`),
  CONSTRAINT `FKi6rct4p3u3sguo1bkvb4wbgy3` FOREIGN KEY (`pageid`) REFERENCES `Page` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coursesessions_pages`
--

LOCK TABLES `coursesessions_pages` WRITE;
/*!40000 ALTER TABLE `coursesessions_pages` DISABLE KEYS */;
INSERT INTO `coursesessions_pages` VALUES (9,999999,1),(10,999999,1),(11,999999,1),(12,999999,1),(13,999999,1);
/*!40000 ALTER TABLE `coursesessions_pages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1),(1),(1),(1),(1),(1),(1),(1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parents_children_pages`
--

DROP TABLE IF EXISTS `parents_children_pages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parents_children_pages` (
  `childid` bigint(20) NOT NULL,
  `parentid` bigint(20) NOT NULL,
  KEY `FKcfu68bxrxuqu5hxcp2va4f8nk` (`parentid`),
  KEY `FKs4sa53bk5li9k21nry920kyp` (`childid`),
  CONSTRAINT `FKcfu68bxrxuqu5hxcp2va4f8nk` FOREIGN KEY (`parentid`) REFERENCES `Page` (`id`),
  CONSTRAINT `FKs4sa53bk5li9k21nry920kyp` FOREIGN KEY (`childid`) REFERENCES `Page` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parents_children_pages`
--

LOCK TABLES `parents_children_pages` WRITE;
/*!40000 ALTER TABLE `parents_children_pages` DISABLE KEYS */;
/*!40000 ALTER TABLE `parents_children_pages` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-20 12:44:03
