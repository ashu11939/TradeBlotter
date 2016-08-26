CREATE DATABASE  IF NOT EXISTS `tradeblotter` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `tradeblotter`;
-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: localhost    Database: tradeblotter
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
-- Table structure for table `pricing_info`
--

DROP TABLE IF EXISTS `pricing_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pricing_info` (
  `TradeID` int(11) NOT NULL AUTO_INCREMENT,
   `Side` char DEFAULT NULL,
  `TimeUpdated` datetime DEFAULT NULL,
   `TradeStatus` varchar(15),
  `Product` varchar(45) DEFAULT NULL,
  `Qty` int(11) DEFAULT NULL,
  `Price` decimal(8,2) DEFAULT NULL,
  `Firm`  varchar(45) DEFAULT NULL,
  
  PRIMARY KEY (`TradeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pricing_info`
--

LOCK TABLES `pricing_info` WRITE;
/*!40000 ALTER TABLE `pricing_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `pricing_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scratchpad`
--

DROP TABLE IF EXISTS `scratchpad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scratchpad` (
  `Notes` varchar(100) DEFAULT NULL,
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
/*!40000 ALTER TABLE `scratchpad` ENABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET Cuserspricing_infoHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-08-26  9:45:49