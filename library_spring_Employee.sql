-- MySQL dump 10.13  Distrib 8.0.29, for macos12 (x86_64)
--
-- Host: 127.0.0.1    Database: library_spring
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Employee`
--

DROP TABLE IF EXISTS `Employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `dateOfBirth` date NOT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `position` varchar(30) NOT NULL,
  `departmentId` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_department` (`departmentId`),
  CONSTRAINT `fk_department` FOREIGN KEY (`departmentId`) REFERENCES `Department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Employee`
--

LOCK TABLES `Employee` WRITE;
/*!40000 ALTER TABLE `Employee` DISABLE KEYS */;
INSERT INTO `Employee` VALUES (1,'Adam','Stefanik','Polska','Rzeszów','1985-08-08','M','kierownik',1),(2,'Kamil','Markowski','Polska','Strzyżów','1999-08-25','M','administrator',1),(3,'Beata','Kowalska','Polska','Warszawa','1999-09-06','K','kierownik',2),(4,'Adam','Nowak','Polska','Poznań','1976-09-09','M','pracownik',3),(6,'Jarosław','Bobrov','Ukraina','Kijów','1991-11-11','M','kierownik',7),(7,'Agnieszka','Sikora','Polska','Wrocław','1985-01-30','K','pracownik',7),(8,'Anna','Stefanik','Polska','Wrocław','2000-12-28','K','pracownik',1),(9,'Jarosław','Milik','Polska','Krosno','1971-05-30','M','kierownik',3),(10,'Katarzyna','Matuś','Polska','Jasło','1998-08-09','K','pracownik',2),(11,'Marek','Kopnik','Polska','Trzciana','2001-07-16','M','pracownik',2),(12,'Tomasz','Pyzik','Polska','Warszawa','1968-08-09','M','pracownik',3),(13,'Dariusz','Stefanik','Polska','Gdańsk','1987-07-01','M','pracownik',7),(14,'Zygmunt','Fąfara','Polska','Gdynia','1977-07-03','M','kierownik',8),(15,'Jadwiga','Biernacka','Polska','Gdańsk','1999-09-13','K','pracownik',8),(16,'Marek','Łavrov','Ukraina','Kijów','1989-08-08','M','pracownik',8),(17,'Dominik','Duda','Polska','Krosno','1998-08-09','M','pracownik',7);
/*!40000 ALTER TABLE `Employee` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-06 20:13:37
