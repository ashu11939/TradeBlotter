CREATE DATABASE  IF NOT EXISTS `mydatabase` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mydatabase`;
-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: mydatabase
-- ------------------------------------------------------
-- Server version	5.6.25-log

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
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categories` (
  `CategoryID` int(11) NOT NULL AUTO_INCREMENT,
  `CategoryName` varchar(15) NOT NULL,
  `Description` varchar(100) NOT NULL,
  PRIMARY KEY (`CategoryID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Sports','Sportswear and equipment'),(2,'SmallElectrical','TVs, audio/video accessories, small kitchen appliances'),(3,'Food','All food/drink products'),(4,'Furniture','All household furniture'),(6,'Auto','Cars, vans, and motor bikes');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contracts`
--

DROP TABLE IF EXISTS `contracts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contracts` (
  `EmployeeID` int(11) NOT NULL,
  `StartDate` date NOT NULL,
  `StartSalary` double NOT NULL,
  PRIMARY KEY (`EmployeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contracts`
--

LOCK TABLES `contracts` WRITE;
/*!40000 ALTER TABLE `contracts` DISABLE KEYS */;
INSERT INTO `contracts` VALUES (1,'2008-01-01',10000),(2,'2009-01-02',20000),(3,'2009-01-03',30000),(4,'2009-01-04',40000),(5,'2009-01-05',50000),(6,'2009-01-06',60000),(7,'2009-01-07',70000),(8,'2009-01-08',80000),(9,'2009-01-09',90000);
/*!40000 ALTER TABLE `contracts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employees` (
  `EmployeeID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL,
  `Salary` double NOT NULL,
  `Region` varchar(50) NOT NULL,
  PRIMARY KEY (`EmployeeID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (1,'Andy',25000,'South Wales'),(2,'Claire',37000,'Kent'),(3,'Mary',42000,'London'),(4,'Mungo',47000,'Cumbria'),(5,'Midge',72000,'Scotland'),(6,'Hayley',69000,'Northern Ireland'),(7,'Nicki',22000,'Kent'),(8,'Sara',11000,'Kent'),(9,'Fiona',88000,'Kent');
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groupdynamic`
--

DROP TABLE IF EXISTS `groupdynamic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groupdynamic` (
  `SLNo` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(45) DEFAULT NULL,
  `GroupID` int(11) DEFAULT NULL,
  PRIMARY KEY (`SLNo`),
  KEY `GroupID_idx` (`GroupID`),
  CONSTRAINT `GroupID` FOREIGN KEY (`GroupID`) REFERENCES `groupstatic` (`GroupID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groupdynamic`
--

LOCK TABLES `groupdynamic` WRITE;
/*!40000 ALTER TABLE `groupdynamic` DISABLE KEYS */;
/*!40000 ALTER TABLE `groupdynamic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groupstatic`
--

DROP TABLE IF EXISTS `groupstatic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groupstatic` (
  `GroupID` int(11) NOT NULL,
  `GroupName` varchar(45) DEFAULT NULL,
  `CreatedBy` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`GroupID`),
  KEY `CreatedBy_idx` (`CreatedBy`),
  CONSTRAINT `CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `users` (`Username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groupstatic`
--

LOCK TABLES `groupstatic` WRITE;
/*!40000 ALTER TABLE `groupstatic` DISABLE KEYS */;
/*!40000 ALTER TABLE `groupstatic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messages` (
  `SLNo` int(11) NOT NULL AUTO_INCREMENT,
  `GroupID` int(11) DEFAULT NULL,
  `Sender` varchar(45) DEFAULT NULL,
  `Message` text,
  `Time` datetime DEFAULT NULL,
  PRIMARY KEY (`SLNo`),
  KEY `GroupID_idx` (`GroupID`),
  CONSTRAINT `GroupId1` FOREIGN KEY (`GroupID`) REFERENCES `groupstatic` (`GroupID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persons`
--

DROP TABLE IF EXISTS `persons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persons` (
  `PersonID` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(50) NOT NULL,
  `LastName` varchar(50) NOT NULL,
  `Address1` varchar(50) NOT NULL,
  `Address2` varchar(50) NOT NULL,
  `Address3` varchar(50) NOT NULL,
  PRIMARY KEY (`PersonID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persons`
--

LOCK TABLES `persons` WRITE;
/*!40000 ALTER TABLE `persons` DISABLE KEYS */;
INSERT INTO `persons` VALUES (1,'John','Smith','1 Main St','Weston','Bath'),(2,'Jane','Evans','2 High St','Newton','Neath'),(3,'Bill','Jones','3 Oaks St','Denton','Leeds');
/*!40000 ALTER TABLE `persons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pricing_info`
--

DROP TABLE IF EXISTS `pricing_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pricing_info` (
  `TradeID` int(11) NOT NULL AUTO_INCREMENT,
  `Side` char(1) DEFAULT NULL,
  `TimeUpdated` datetime DEFAULT NULL,
  `TradeStatus` varchar(15) DEFAULT NULL,
  `Product` varchar(45) DEFAULT NULL,
  `Qty` int(11) DEFAULT NULL,
  `Price` decimal(8,2) DEFAULT NULL,
  `Firm` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`TradeID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pricing_info`
--

LOCK TABLES `pricing_info` WRITE;
/*!40000 ALTER TABLE `pricing_info` DISABLE KEYS */;
INSERT INTO `pricing_info` VALUES (1,'B','2016-05-20 10:20:59','Completed','Reliance',10,1001.00,'SushilKiFirm'),(2,'B','2016-05-22 10:20:59','InCompleted','Puma',15,1500.00,'Akaria'),(3,'S','2016-06-20 10:20:59','InCompleted','Reebok',100,100.00,'SolidTrades'),(4,'B','2016-04-22 10:20:59','InCompleted','Adidas',9,120.00,'Belano'),(5,'B','2016-07-20 10:20:59','InCompleted','GoldmanSachs',500,1000.00,'Heloz'),(6,'S','2016-08-17 11:20:59','Completed','Emarald',90,1120.00,'Axtria'),(7,'B','2016-07-03 10:20:59','InCompleted','DLF',50,1700.00,'Lamo');
/*!40000 ALTER TABLE `pricing_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pricing_info_with_entitlement`
--

DROP TABLE IF EXISTS `pricing_info_with_entitlement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pricing_info_with_entitlement` (
  `TradeID` int(11) NOT NULL AUTO_INCREMENT,
  `Side` char(1) DEFAULT NULL,
  `TimeUpdated` datetime DEFAULT NULL,
  `TradeStatus` varchar(15) DEFAULT NULL,
  `Product` varchar(100) DEFAULT NULL,
  `Qty` int(11) DEFAULT NULL,
  `Price` decimal(8,2) DEFAULT NULL,
  `Firm` varchar(100) DEFAULT NULL,
  `Entitlement` int(11) DEFAULT NULL,
  PRIMARY KEY (`TradeID`)
) ENGINE=InnoDB AUTO_INCREMENT=201 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pricing_info_with_entitlement`
--

LOCK TABLES `pricing_info_with_entitlement` WRITE;
/*!40000 ALTER TABLE `pricing_info_with_entitlement` DISABLE KEYS */;
INSERT INTO `pricing_info_with_entitlement` VALUES (1,'B','2016-05-20 10:20:59','Completed','Reliance',10,1001.00,'SushilKiFirm',1),(2,'B','2016-05-22 10:20:59','InCompleted','Puma',15,1500.00,'Akaria',2),(3,'S','2016-06-20 10:20:59','InCompleted','Reebok',100,100.00,'SolidTrades',1),(4,'B','2016-04-22 10:20:59','InCompleted','Adidas',9,120.00,'Belano',2),(5,'B','2016-07-20 10:20:59','InCompleted','GoldmanSachs',500,1000.00,'Heloz',3),(6,'S','2016-08-17 11:20:59','Completed','Emarald',90,1120.00,'Axtria',1),(7,'B','2016-07-03 10:20:59','InCompleted','DLF',50,1700.00,'Lamo',1),(101,'B','2015-09-02 13:18:18','Complete','Elit Inc.',391,7565.73,'A Magna Institute',3),(102,'S','2016-11-25 10:10:21','Incomplete','Nunc Ut Incorporated',727,173.25,'Ornare Industries',1),(103,'B','2017-01-14 20:05:30','Complete','Sed Pede Cum Associates',292,8953.01,'Semper Tellus Id Institute',2),(104,'S','2017-03-11 21:32:35','Incomplete','Purus In Molestie Incorporated',397,9236.45,'Nec Malesuada Foundation',2),(105,'B','2017-06-12 21:44:16','Complete','Aliquet Limited',517,5938.36,'Curabitur Incorporated',2),(106,'S','2015-11-25 01:21:03','Incomplete','Ultrices Posuere Corp.',406,773.18,'Proin Corp.',2),(107,'B','2016-07-02 16:27:26','Complete','Mollis Nec Cursus Inc.',217,6275.70,'Non Enim Commodo Industries',3),(108,'S','2016-03-28 09:58:31','Incomplete','Sem Corp.',174,4553.58,'Cursus A Enim Corp.',3),(109,'B','2017-03-30 14:30:39','Complete','Lorem Luctus Ut LLP',252,7061.73,'Sed Institute',2),(110,'S','2016-01-05 13:13:34','Incomplete','Fringilla Cursus Corporation',766,4790.07,'Erat Eget Ipsum Ltd',2),(111,'B','2016-09-20 22:00:59','Complete','Mollis Non Cursus Consulting',525,3392.23,'Faucibus Ut Corp.',1),(112,'S','2015-10-16 04:41:57','Incomplete','Suspendisse Tristique Neque Corporation',679,8745.60,'Cubilia Curae; Donec Associates',2),(113,'B','2016-12-09 17:06:46','Complete','Vitae Sodales At Ltd',153,8812.32,'Ut Incorporated',2),(114,'S','2015-12-09 02:58:02','Incomplete','Ultricies Ornare Elit Consulting',842,3981.22,'Vel Corp.',1),(115,'B','2016-04-22 22:54:04','Complete','Sapien Gravida Industries',602,6656.63,'Orci Quis Lectus Inc.',2),(116,'S','2015-09-18 04:50:17','Incomplete','Phasellus In Inc.',616,5966.95,'Vel Quam Corporation',1),(117,'B','2016-03-27 03:15:15','Complete','Lacus LLC',530,8210.05,'Orci Foundation',3),(118,'S','2017-04-16 14:43:10','Incomplete','Laoreet Ipsum Foundation',458,5450.02,'Lacinia Orci Consectetuer Ltd',1),(119,'B','2017-03-29 12:08:45','Complete','Amet Consectetuer Adipiscing LLC',764,9024.27,'Velit In Incorporated',1),(120,'S','2016-08-14 23:09:43','Incomplete','Vitae Semper Ltd',945,2385.23,'Suspendisse Corporation',2),(121,'B','2017-01-26 22:08:21','Complete','In Corp.',902,120.60,'Vitae LLP',1),(122,'S','2016-06-21 06:44:24','Incomplete','In Industries',963,733.26,'Diam Duis Mi Corporation',2),(123,'B','2016-09-01 15:03:39','Complete','Cras Vehicula Aliquet Industries',151,2207.17,'Et Commodo At Industries',1),(124,'S','2015-12-13 21:23:22','Incomplete','Est Mollis Foundation',104,1780.30,'Euismod Foundation',1),(125,'B','2015-09-18 21:56:48','Complete','Justo Associates',339,804.85,'Nisi Company',3),(126,'S','2017-03-14 17:19:49','Incomplete','Sapien Cursus In LLP',689,7288.56,'Mi Incorporated',2),(127,'B','2016-10-31 09:46:53','Complete','Libero Ltd',601,7260.38,'Pede Cras Vulputate Associates',1),(128,'S','2016-10-06 16:37:20','Incomplete','Pellentesque Consulting',914,8367.91,'In Magna Phasellus Associates',1),(129,'B','2016-10-15 09:09:06','Complete','Mauris Industries',640,1854.52,'Mauris Non LLP',2),(130,'S','2016-11-05 19:14:20','Incomplete','Mauris Associates',422,4860.91,'Blandit Congue Corporation',3),(131,'B','2017-02-02 22:39:22','Complete','Lobortis Quam A Associates',518,642.54,'Lacus Cras Interdum LLC',1),(132,'S','2017-01-06 12:28:57','Incomplete','Nulla In LLP',742,2517.14,'Pede Cras Vulputate Limited',1),(133,'B','2016-02-14 17:47:10','Complete','Nulla Eget Metus Limited',967,9744.15,'In Consulting',2),(134,'S','2017-08-22 04:34:16','Incomplete','Erat Volutpat Nulla Corporation',686,6309.41,'Nisi Sem Semper LLP',1),(135,'B','2016-05-13 03:52:31','Complete','Tincidunt Nunc Ac Institute',186,176.47,'Sit Amet Risus Limited',1),(136,'S','2016-10-19 19:29:45','Incomplete','Ut Limited',849,5600.38,'Magnis Dis LLP',1),(137,'B','2017-05-05 09:23:42','Complete','Turpis Ltd',714,8673.82,'Commodo Tincidunt Corporation',1),(138,'S','2017-08-21 01:20:17','Incomplete','Luctus Curabitur LLC',717,5242.83,'Odio Aliquam Vulputate Foundation',3),(139,'B','2016-08-26 06:04:10','Complete','Et Magnis Associates',101,5470.39,'Elit Sed Consulting',3),(140,'S','2016-04-22 12:46:12','Incomplete','Posuere At Corp.',920,5977.37,'Consectetuer Cursus Et PC',2),(141,'B','2015-10-20 15:56:03','Complete','Duis Corporation',323,175.80,'Dolor Donec Fringilla Corp.',3),(142,'S','2017-06-13 06:39:00','Incomplete','Semper PC',432,2597.17,'Scelerisque Neque Consulting',3),(143,'B','2016-06-27 03:49:02','Complete','Ut Aliquam Iaculis Inc.',762,6003.42,'Dictum Eu Eleifend Inc.',2),(144,'S','2015-09-15 01:14:08','Incomplete','Erat Neque Non Inc.',993,8908.97,'Dolor Foundation',1),(145,'B','2016-01-10 10:12:57','Complete','Ut Sagittis Consulting',396,3731.27,'Nec LLC',3),(146,'S','2016-06-28 00:45:36','Incomplete','Ac Ltd',985,8237.77,'Donec Est Nunc Institute',2),(147,'B','2016-01-21 07:50:58','Complete','Amet Lorem Associates',391,353.04,'Hendrerit Foundation',2),(148,'S','2016-05-23 03:36:14','Incomplete','Vel Limited',123,9428.17,'Purus PC',2),(149,'B','2016-01-06 18:46:40','Complete','In PC',680,9615.88,'Ligula Elit LLP',3),(150,'S','2017-05-13 18:33:26','Incomplete','In Mi Pede LLC',335,9287.55,'Pellentesque Tincidunt Tempus Ltd',3),(151,'B','2016-09-25 05:49:02','Complete','Sed Company',945,8121.06,'Tempus Non Lacinia Institute',2),(152,'S','2017-02-09 04:27:38','Incomplete','Lacus Ut Foundation',404,5130.40,'Nec Mauris Ltd',2),(153,'B','2016-09-15 05:56:02','Complete','Erat Vel Industries',523,4890.64,'Ipsum Foundation',2),(154,'S','2017-06-15 06:47:59','Incomplete','Nulla Tincidunt Neque Industries',688,9915.14,'Sem Semper Associates',2),(155,'B','2016-05-13 08:18:49','Complete','Interdum Libero LLC',487,9819.69,'Nunc Mauris Elit Company',3),(156,'S','2017-04-28 18:06:27','Incomplete','Purus In Molestie Limited',314,9709.88,'Sagittis Felis Donec Inc.',1),(157,'B','2016-02-27 22:00:05','Complete','Cubilia Curae; Inc.',674,9741.53,'Eu Associates',2),(158,'S','2016-01-06 16:17:16','Incomplete','Viverra LLP',453,9596.93,'Enim Corporation',1),(159,'B','2016-08-18 21:22:38','Complete','Ornare Lectus Ante Institute',292,8892.18,'Magnis Dis Foundation',3),(160,'S','2016-12-11 13:29:45','Incomplete','Aliquet Metus Consulting',269,9564.85,'Porttitor Company',1),(161,'B','2016-11-12 16:05:48','Complete','Erat Nonummy Ultricies Consulting',179,5897.51,'Egestas Duis LLC',3),(162,'S','2016-07-27 09:45:21','Incomplete','Hendrerit A Arcu Corp.',259,1902.92,'Volutpat Industries',1),(163,'B','2016-02-13 04:54:47','Complete','Vitae Company',681,4269.95,'Ligula Aenean Institute',1),(164,'S','2015-10-04 18:29:35','Incomplete','Tristique Pharetra Quisque Company',933,4078.22,'Arcu Imperdiet LLP',1),(165,'B','2016-05-23 02:10:03','Complete','Nunc Associates',540,1151.81,'Vitae Aliquam Eros Foundation',2),(166,'S','2016-06-15 16:38:43','Incomplete','Justo Institute',986,915.53,'Non Foundation',3),(167,'B','2017-02-19 23:51:38','Complete','Amet PC',448,1101.98,'Ultrices Sit Amet Incorporated',1),(168,'S','2016-11-09 22:10:27','Incomplete','Tempor Est LLC',309,5444.46,'Non Massa Non Corp.',1),(169,'B','2015-09-12 10:50:40','Complete','Mollis Industries',215,7051.34,'Pellentesque A Facilisis Institute',1),(170,'S','2016-08-24 19:20:29','Incomplete','Auctor Quis Corp.',394,3409.15,'Pellentesque Massa Limited',2),(171,'B','2016-06-04 19:14:06','Complete','Commodo Hendrerit Ltd',386,9961.60,'Malesuada Augue Ut Ltd',3),(172,'S','2017-02-25 11:06:29','Incomplete','Ipsum Institute',630,9801.87,'Vitae Associates',3),(173,'B','2017-08-04 11:59:53','Complete','Maecenas Mi Felis LLP',118,3752.80,'Aliquam Industries',2),(174,'S','2017-06-11 06:35:12','Incomplete','In Aliquet Lobortis Associates',638,873.95,'Eu Erat Corp.',1),(175,'B','2015-09-18 01:02:51','Complete','Proin Nisl Consulting',374,2140.72,'Cursus LLC',2),(176,'S','2017-08-26 19:31:24','Incomplete','Nostra Per Inceptos Ltd',414,4410.81,'Ullamcorper Institute',3),(177,'B','2017-06-28 09:22:02','Complete','Velit Pellentesque Company',176,6400.54,'Et Incorporated',3),(178,'S','2017-01-28 22:57:12','Incomplete','Sodales Associates',874,8422.42,'Maecenas Mi Felis Associates',1),(179,'B','2016-04-21 07:22:11','Complete','Sit Amet Consectetuer Inc.',481,4783.82,'Libero Industries',1),(180,'S','2017-04-30 15:22:24','Incomplete','Blandit Mattis Cras Foundation',631,9552.57,'Laoreet Lectus Quis PC',1),(181,'B','2016-06-07 19:39:58','Complete','Placerat Limited',865,9202.77,'Nunc Commodo LLP',1),(182,'S','2017-02-13 12:08:50','Incomplete','A Nunc In Limited',266,9070.29,'Mauris Morbi Non PC',3),(183,'B','2016-12-09 03:11:17','Complete','Nibh Vulputate LLC',307,8431.22,'Donec Egestas Incorporated',3),(184,'S','2016-02-07 12:53:36','Incomplete','Lacus Ut Nec Corporation',720,1691.86,'Auctor Institute',3),(185,'B','2016-03-02 14:54:12','Complete','Integer Id Magna Limited',733,9979.82,'Ultrices Sit Industries',3),(186,'S','2016-06-08 06:34:44','Incomplete','Pede Corporation',961,4447.02,'Lectus Pede Company',3),(187,'B','2016-11-11 15:18:04','Complete','Egestas Inc.',212,9947.93,'In Felis Nulla Company',3),(188,'S','2016-08-11 05:46:47','Incomplete','Elit Pede Corp.',201,6797.76,'Accumsan Laoreet Ipsum Limited',3),(189,'B','2016-04-29 01:50:57','Complete','Sed Pede Nec Consulting',179,8940.39,'Semper Dui Company',2),(190,'S','2016-06-04 18:58:46','Incomplete','Nec Incorporated',177,6372.69,'Mollis Nec Cursus Limited',3),(191,'B','2015-09-15 03:57:00','Complete','Sociosqu Ad Incorporated',955,2389.80,'Dignissim Magna A Institute',3),(192,'S','2015-10-28 23:09:59','Incomplete','Sapien Cursus In Industries',120,478.84,'Donec Non Corporation',2),(193,'B','2016-12-26 23:30:36','Complete','Magna Sed Associates',886,4423.97,'Duis Gravida LLP',3),(194,'S','2017-08-03 17:45:03','Incomplete','Enim Sed Nulla Ltd',863,6336.74,'Per Conubia Inc.',3),(195,'B','2016-01-16 00:57:10','Complete','Proin Ultrices Duis Consulting',180,9348.05,'Tellus Corporation',2),(196,'S','2015-12-24 07:23:11','Incomplete','Faucibus Lectus Inc.',747,1389.34,'Aliquam Adipiscing Lacus Corporation',1),(197,'B','2016-06-22 17:51:46','Complete','Libero Proin Corp.',220,1415.10,'Felis Purus Ac Ltd',1),(198,'S','2016-01-29 11:33:33','Incomplete','A Consulting',648,2876.07,'Tristique Pharetra Associates',3),(199,'B','2016-05-16 08:32:33','Complete','Eu Placerat Eget Industries',943,7978.69,'A Scelerisque Corp.',2),(200,'S','2016-02-12 22:45:34','Incomplete','Tempus Foundation',352,1905.49,'Convallis Corporation',1);
/*!40000 ALTER TABLE `pricing_info_with_entitlement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `ProductID` int(11) NOT NULL AUTO_INCREMENT,
  `CategoryID` int(11) NOT NULL,
  `ProductName` varchar(50) NOT NULL,
  `UnitPrice` double NOT NULL,
  `UnitsInStock` int(11) NOT NULL,
  `ReorderLevel` int(11) DEFAULT NULL,
  PRIMARY KEY (`ProductID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,1,'Swansea City jersey',45,1000,200),(2,1,'Cardiff City jersey',42,800,150),(3,1,'Ski boots',175,10,2),(4,2,'65 inch UHD TV',1800,5,2),(5,2,'BluRay recorder',200,10,4),(6,2,'Portable fridge',350,3,1),(7,3,'Beer',2.5,500,100),(8,3,'Crisps',1.5,2000,200),(9,3,'Ice cream',4.4,200,25),(10,4,'Sofa',750,5,1),(11,4,'Coffee table',250,5,1),(12,6,'Bugatti',1000000,2,1),(13,6,'Merc',37000,20,5);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scratchpad`
--

DROP TABLE IF EXISTS `scratchpad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scratchpad` (
  `Notes` varchar(45) DEFAULT NULL,
  `Username` varchar(45) DEFAULT NULL,
  KEY `UserName_idx` (`Username`),
  CONSTRAINT `UserName` FOREIGN KEY (`Username`) REFERENCES `users` (`Username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scratchpad`
--

LOCK TABLES `scratchpad` WRITE;
/*!40000 ALTER TABLE `scratchpad` DISABLE KEYS */;
INSERT INTO `scratchpad` VALUES ('My notes changes','nk8663'),('These are my notes.','btak');
/*!40000 ALTER TABLE `scratchpad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `skills`
--

DROP TABLE IF EXISTS `skills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `skills` (
  `SkillID` int(11) NOT NULL AUTO_INCREMENT,
  `EmployeeID` int(11) DEFAULT NULL,
  `Description` varchar(50) NOT NULL,
  `Level` int(11) DEFAULT '5',
  PRIMARY KEY (`SkillID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skills`
--

LOCK TABLES `skills` WRITE;
/*!40000 ALTER TABLE `skills` DISABLE KEYS */;
INSERT INTO `skills` VALUES (1,1,'Football',5),(2,1,'Skiing',3),(3,1,'Running',3),(4,2,'Sales',4),(5,2,'Skiing',4),(6,2,'Football',4),(7,3,'Maths',5),(8,3,'Singing',4),(9,3,'Teaching',5),(10,3,'Running',2);
/*!40000 ALTER TABLE `skills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(45) DEFAULT NULL,
  `LastName` varchar(45) DEFAULT NULL,
  `Username` varchar(45) DEFAULT NULL,
  `Password` varchar(45) DEFAULT NULL,
  `LogoutTime` datetime DEFAULT NULL,
  PRIMARY KEY (`UserID`),
  UNIQUE KEY `Username_UNIQUE` (`Username`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Bhavya','Tak','btak','bt123','2012-06-18 10:34:09'),(2,'Vijay','Meena','vmeena','vm123','2012-06-18 10:36:09'),(3,'Sushil','Singh','ssingh','ss123','2012-06-18 22:38:09'),(4,'Nitish','Kumar','nkumar','nk123','2012-08-11 22:48:09'),(5,'Vineet','V','vv','vv123','2012-06-18 07:38:09'),(6,'Ashutosh','Kumar','akumar','ak123','2016-06-18 22:38:09'),(7,'Rucha','H','rh','rh123','2015-10-18 22:38:09'),(13,'a','b','ikj','pwd','2016-08-27 12:33:26'),(14,'Nitish','Kumar','nk8663','12343','2016-08-27 12:45:25'),(15,NULL,NULL,NULL,NULL,'2016-08-27 15:59:58'),(16,NULL,NULL,NULL,NULL,'2016-08-27 16:00:03'),(17,'Vineet','vv','vv123421','abcxyz','2016-08-28 16:17:14');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_with_entitlement`
--

DROP TABLE IF EXISTS `users_with_entitlement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_with_entitlement` (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(45) DEFAULT NULL,
  `LastName` varchar(45) DEFAULT NULL,
  `Username` varchar(45) DEFAULT NULL,
  `Password` varchar(45) DEFAULT NULL,
  `LogoutTime` datetime DEFAULT NULL,
  `Entitlement` int(11) DEFAULT NULL,
  PRIMARY KEY (`UserID`),
  UNIQUE KEY `Username_UNIQUE` (`Username`)
) ENGINE=InnoDB AUTO_INCREMENT=202 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_with_entitlement`
--

LOCK TABLES `users_with_entitlement` WRITE;
/*!40000 ALTER TABLE `users_with_entitlement` DISABLE KEYS */;
INSERT INTO `users_with_entitlement` VALUES (1,'Bhavya','Tak','btak','bt123','2012-06-18 10:34:09',3),(2,'Vijay','Meena','vmeena','vm123','2012-06-18 10:36:09',3),(3,'Sushil','Singh','ssingh','ss123','2012-06-18 22:38:09',1),(4,'Nitish','Kumar','nkumar','nk123','2012-08-11 22:48:09',2),(5,'Vineet','V','vv','vv123','2012-06-18 07:38:09',1),(6,'Ashutosh','Kumar','akumar','ak123','2016-06-18 22:38:09',2),(7,'Rucha','H','rh','rh123','2015-10-18 22:38:09',1),(101,'Nicholas','Valencia','ENNED6491','KAP73DOY0LI','2016-08-22 01:11:33',3),(102,'Rashad','Noel','RCUJJ6736','RME19ZOB3ZW','2015-09-23 12:00:10',2),(103,'Kyla','Freeman','YEYJZ6770','EWO92EGQ5FP','2015-09-09 20:38:20',2),(104,'Susan','Hale','HWAHZ4239','ABZ09YDJ4DK','2016-06-03 16:21:29',1),(105,'Lewis','Whitehead','QZSEI9334','QVL85LJS0SR','2017-08-25 04:15:42',3),(106,'Ignatius','Irwin','RWBFM8443','IKB83DYE5OL','2016-07-05 12:11:10',2),(107,'Beverly','Graves','HNFYK1335','ZTM28GZE1GA','2016-08-16 20:23:14',2),(108,'Julie','Mckinney','TOEZG3275','CPM79CNW1QK','2016-04-13 04:39:52',1),(109,'Camden','Norman','BKCNU8748','WWW99IVI7BX','2017-05-26 13:07:25',2),(110,'Madeson','Marsh','DNGYJ0760','VVT34PJP4FK','2017-01-15 12:39:02',2),(111,'Wayne','Acosta','MOHIK3267','UKD06MON0GB','2015-10-11 04:27:38',3),(112,'Damon','Heath','XWGZI1384','XQO86SRA1WY','2016-09-14 18:43:11',1),(113,'Charity','Hill','MBQSI0839','SJB61AUK1XB','2017-07-25 00:02:18',2),(114,'Conan','Jones','VTIWG1366','WTK25HJX2EF','2015-10-31 05:00:51',3),(115,'Dustin','Chavez','FAODG5827','ECU46AAY2NW','2015-12-04 11:56:39',1),(116,'Elizabeth','Davis','WNNTP8275','TQP24GIH5GB','2016-07-26 06:31:28',1),(117,'Hollee','Wade','MTFSZ6370','CRN50LAQ9XF','2017-06-23 04:45:11',1),(118,'Alvin','Holman','SIAED8303','MYT69BCU5QO','2015-09-23 21:00:47',1),(119,'Lareina','Riggs','WXAOC2056','LXW78YYV5CJ','2017-03-12 13:17:11',1),(120,'Hilel','Dominguez','IYGYH0007','KXD34YDX0SE','2017-04-22 07:31:18',2),(121,'Macy','Holloway','XPVFA6446','OWI76RQG8MF','2016-12-20 01:56:38',1),(122,'Orli','Lester','XNRJK8777','EZV67VWL3HT','2016-02-08 03:14:19',3),(123,'Vladimir','Baker','YNGDR2551','ZQQ71UVA6KV','2017-01-17 18:56:57',2),(124,'Uma','Cain','PKCYC7786','CYG74TXJ2KC','2016-03-10 21:31:03',3),(125,'Lamar','Nicholson','TXYJK8905','FWR68BTH5ZF','2016-04-16 11:05:16',1),(126,'Emerald','Summers','OBKUF6406','ZNH84TJH6FI','2016-11-05 09:46:10',3),(127,'Emi','Benjamin','CSYKB6979','BRC89YKT5AB','2016-10-02 16:00:36',1),(128,'Maris','Pate','VAGVD4291','NKG94RMZ2EO','2016-09-17 18:48:27',2),(129,'Genevieve','Leonard','VRAHZ5173','DUB76STO8MB','2016-05-30 17:14:19',2),(130,'Salvador','Tanner','KBGLG6921','FET08KRH6PC','2017-02-07 18:22:28',2),(131,'Robert','Ortega','SDEWI8340','IHT77IBO8IL','2016-08-23 11:05:02',3),(132,'Aileen','Stephenson','MMUYD8852','SDZ43TJR4CF','2016-12-04 06:29:23',3),(133,'Benjamin','Howe','ZQBHY1304','VPI59JQQ4LB','2016-06-11 00:21:41',3),(134,'Nora','George','ILXLE5744','ESI69JSI1YF','2017-08-25 02:57:40',2),(135,'Cara','Acosta','JKNJV2839','DFB82IER0PZ','2017-01-20 18:47:32',2),(136,'Darrel','Hogan','OQUIB4128','RIA35KIA6XV','2016-01-19 14:19:00',2),(137,'Kirestin','Patrick','MMJGJ9728','DFQ00YGT9VQ','2017-06-17 05:13:17',3),(138,'Iona','Castillo','ZYYLI7182','UQM06MOU0GH','2017-01-16 23:50:25',3),(139,'Hillary','Fleming','TTLNC6971','IDQ48KHW5OT','2016-11-10 10:00:16',2),(140,'Amal','Holman','TCXLO9239','MUX90CVB9JF','2015-09-25 14:32:20',2),(141,'Chiquita','Bryant','PJRBZ4938','FXJ12HJS4BN','2016-12-17 18:34:06',2),(142,'Alexis','Soto','OLZOT4812','KGU18ENN7RZ','2016-02-07 07:37:54',1),(143,'Amity','Wong','UZUEO5770','XFX10TAX8II','2016-03-08 02:47:26',3),(144,'Fatima','Walter','REHZC4407','UJA96MJK2ZY','2017-02-17 00:17:31',2),(145,'Kelsey','Holt','ROJDP0599','WCS15TZA2OI','2017-03-27 09:13:17',3),(146,'Athena','Hebert','EZGQH7693','JNW27YCR4UZ','2015-12-01 19:10:22',3),(147,'Freya','Hall','MTIZT2644','HAI27CGM0RR','2016-01-16 21:01:21',3),(148,'Shad','Herring','IFFGM3572','BOR62WPU9KX','2016-01-07 11:57:41',3),(149,'Kellie','Gaines','ERRAC5757','VTO88ETF4FZ','2017-08-02 03:21:51',1),(150,'Helen','Schwartz','UCLNP9207','QFG62ZDX7QT','2016-08-30 00:28:36',1),(151,'Donovan','Foreman','OVFDN8328','RIV14KWR7QJ','2016-07-26 05:50:16',2),(152,'Rose','Booker','KWCLP4458','BGR54WCN6ZP','2015-09-16 07:15:00',2),(153,'Akeem','Watkins','OLVBL0178','NMW83BSR1CZ','2017-08-03 18:12:48',3),(154,'Howard','Briggs','PAMDO0769','WBC58RFF7XT','2017-01-26 11:17:26',1),(155,'Naomi','Chavez','BFLZK1258','QNM01TBZ8GL','2016-03-22 19:11:42',1),(156,'Risa','Conner','RLMTI6592','DNZ58HOB7MQ','2016-09-10 17:06:16',2),(157,'Blythe','Benson','AJXRB1460','ZEE43WEN0NX','2016-05-14 22:44:26',1),(158,'Nehru','Phillips','BWGVG3997','NBR02GVS1CW','2015-12-15 08:12:57',2),(159,'Benedict','Knowles','KRIDQ1346','FPP20KQC6GG','2017-03-26 19:33:37',2),(160,'Raya','Yang','UHYVZ8220','CIJ76AKO9PZ','2016-06-24 16:34:06',1),(161,'Gage','Robbins','QSMGO8875','ENP97UIS1SC','2017-07-07 23:42:46',1),(162,'Hammett','Burton','KACDF7555','DAZ01MKN5QJ','2015-10-21 10:47:01',1),(163,'Nelle','Bryant','XYEWU5146','SLD33BTK6LX','2016-10-06 19:06:36',1),(164,'Phyllis','Lindsay','QXBCE0479','SLQ80NYF1WD','2016-08-07 10:04:24',2),(165,'Nissim','Goodwin','HSEXN4706','PNM49NBD0OJ','2016-07-07 11:45:20',2),(166,'Zeph','Leonard','KWUMT6493','OOE39DXL2QX','2017-01-28 15:14:33',1),(167,'Kiona','Small','WNREU0992','PCD22YGI9VM','2016-12-08 01:04:03',2),(168,'Jana','Fowler','XDIBJ4536','NNQ37SRH1ME','2017-03-08 15:43:40',2),(169,'Lila','Drake','XMNPT4718','DXV42EXL3LP','2016-01-03 01:45:54',1),(170,'Idona','Summers','YDUBU6583','HSH95YBR6CE','2016-07-23 04:28:21',3),(171,'Joelle','Pace','PYYCW9908','GHZ49MSC6KF','2015-10-02 17:16:28',1),(172,'Alec','Morgan','MRHNA3022','DLO22XUL9JL','2016-08-30 01:06:57',1),(173,'Vanna','Bradley','KLXMS6188','VMJ34WVZ5TC','2016-03-26 11:27:58',1),(174,'Moana','Bullock','KWNLD8104','TWF15DTN9ET','2016-09-10 14:19:50',3),(175,'Gretchen','Tran','QVPXM5835','BJK04FYQ8LZ','2017-07-13 18:35:30',3),(176,'Kerry','Jordan','VCPNH2713','CEV85YMB9UP','2016-04-12 12:01:24',1),(177,'Elijah','Terrell','NSDSU7538','UEQ73VRG5OV','2016-04-23 13:06:15',2),(178,'Richard','Kim','AKXGH4695','RER78BND7GA','2017-05-21 04:03:39',3),(179,'Mufutau','Allen','OGMPQ2264','WVO09FIR6RM','2016-06-13 18:09:25',3),(180,'Grady','Delaney','HYSEG5459','EPP90JUE8QM','2017-05-02 04:48:55',3),(181,'Blair','Mccullough','RQMWL1902','KQO05BPO9VG','2015-10-03 07:47:26',2),(182,'Benjamin','Cantu','LTMHC8978','ESV17UZA2WE','2016-04-05 22:12:24',3),(183,'Melodie','William','UEEGV6339','KMZ52FXM3QO','2016-03-23 01:06:09',2),(184,'Valentine','Mcleod','XAZIY8916','YKX58RXC9MR','2016-09-24 04:26:13',2),(185,'Tanek','Dodson','EZBMA5029','TLP57SJB2LQ','2016-04-04 21:14:54',3),(186,'Marvin','Hutchinson','KYSGP7053','KNE44ICA0WB','2017-03-07 07:48:24',2),(187,'Derek','Gross','OMRVH2142','GXS56HYL3EE','2016-02-12 23:06:08',3),(188,'Adele','Suarez','SPFIS5640','LRU99SVX1GI','2017-05-29 08:36:34',1),(189,'Dennis','Ayers','OVDJK2629','MKP40ZJD2XK','2016-01-29 14:59:39',1),(190,'Jacob','Hanson','ARWSA0561','OPO54PVP4LX','2016-03-23 03:28:49',3),(191,'Freya','Blackburn','LWHKX1289','DMK13SQV9RK','2016-10-09 17:27:27',3),(192,'Karina','Miles','UEZIR6248','ZRX61NSF0MH','2016-05-10 22:44:47',1),(193,'Amber','Ford','RJNWE8893','ZWU49JWM6RH','2017-03-26 04:37:03',2),(194,'Idola','Downs','TDOLI7849','LIF34DDS4AQ','2017-01-13 02:08:32',1),(195,'Coby','Richards','QLCAM7184','WWV70YXC0OC','2016-12-04 05:44:03',2),(196,'Emerson','Martinez','KSTXB5260','HTI25ALR7WF','2016-05-19 05:08:04',1),(197,'Sydnee','Page','MPAKO8594','PIS70XAG3TN','2017-08-13 09:36:19',1),(198,'Brennan','Rush','LEFXL0422','JQZ76VEH0YB','2016-09-28 18:36:09',1),(199,'Carissa','Russo','JUJJG0305','GEX51QDK8RR','2017-02-25 22:09:09',1),(200,'Gavin','Bradshaw','BSFGU2692','HAR32AVA4HY','2017-02-23 00:00:14',3),(201,'Vineet','vv','vv12342','abcxyz','2016-08-28 16:21:41',2);
/*!40000 ALTER TABLE `users_with_entitlement` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-08-29  9:14:43
