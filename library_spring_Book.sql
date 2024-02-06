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
-- Table structure for table `Book`
--

DROP TABLE IF EXISTS `Book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Book` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `quality` varchar(22) NOT NULL,
  `authorId` int DEFAULT NULL,
  `customerId` int DEFAULT NULL,
  `departmentId` int DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `rentalDate` datetime DEFAULT NULL,
  `daysSinceRental` int DEFAULT NULL,
  `extended` int DEFAULT NULL,
  `daysToReturn` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Book_fk0` (`authorId`),
  KEY `Book_fk1` (`customerId`),
  KEY `departmentId` (`departmentId`),
  CONSTRAINT `Book_fk0` FOREIGN KEY (`authorId`) REFERENCES `Author` (`id`),
  CONSTRAINT `Book_fk1` FOREIGN KEY (`customerId`) REFERENCES `Customer` (`id`),
  CONSTRAINT `book_ibfk_1` FOREIGN KEY (`departmentId`) REFERENCES `Department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Book`
--

LOCK TABLES `Book` WRITE;
/*!40000 ALTER TABLE `Book` DISABLE KEYS */;
INSERT INTO `Book` VALUES (1,'Dziady cz.3','Dramat poetycki','bardzo dobry',1,NULL,1,'dostępna',NULL,NULL,NULL,NULL),(2,'Pan Tadeusz','Epopeja narodowa','bardzo zły',1,NULL,2,'zniszczona',NULL,NULL,NULL,NULL),(3,'Wesele','Epopeja wiejska','bardzo zły',6,NULL,2,'zniszczona',NULL,NULL,NULL,NULL),(4,'Lalka','Powieść społeczno-obyczajowa','bardzo zły',2,NULL,1,'zniszczona',NULL,NULL,NULL,NULL),(5,'Chłopi',' powieść społeczno-obyczajowa','zły',5,NULL,2,'dostępna',NULL,NULL,NULL,NULL),(9,'Balladyna','dramat romantyczny','idealny',8,8,1,'wypożyczona','2024-01-12 00:00:00',16,0,74),(10,'Konrad Wallenrod','Powieść historyczna','zły',1,8,1,'wypożyczona','2024-01-12 00:00:00',16,0,74);
/*!40000 ALTER TABLE `Book` ENABLE KEYS */;
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
