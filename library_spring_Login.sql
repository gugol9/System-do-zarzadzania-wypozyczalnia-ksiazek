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
-- Table structure for table `Login`
--

DROP TABLE IF EXISTS `Login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Login` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employeeId` int NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `roles` varchar(45) NOT NULL DEFAULT 'manager',
  PRIMARY KEY (`id`),
  KEY `Login_fk0` (`employeeId`),
  CONSTRAINT `Login_fk0` FOREIGN KEY (`employeeId`) REFERENCES `Employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Login`
--

LOCK TABLES `Login` WRITE;
/*!40000 ALTER TABLE `Login` DISABLE KEYS */;
INSERT INTO `Login` VALUES (12,2,'kamar','$2a$10$JgRC2fModLYNBZJZR/TZo.vkHZ5k7aaIt21.Oco4ZV0wwBUJspS/6','administrator'),(16,9,'jarek11','$2a$10$tuV6hE21RxW1bS4AjTLgAuz2sFCOhtHWr5FQpsWTWE5Eigjxnf7iO','kierownik'),(17,4,'jarek12','$2a$10$vFK3ygm2jUBd/T4oz8xD3.Md3LaawoPr2ik8.fZ1d3yVGj2VU1h2a','administrator'),(18,1,'gugol9','$2a$10$9bK1i8NB0bdixYdqen57LeNy.pu8PF9VWszRhqMNg/RNNXbIAhi9u','kierownik'),(28,3,'BeataKowalska34','$2a$10$PSeMsM/soWvrPd8QQtXEz.ZZKDwPo56rOFfOUxeNeggyUGvyqGcQS','kierownik'),(30,6,'jAraBobrov','$2a$10$meo9KqNUU5uqBdAq3Ru29eUA7w07c.mQfXXoa4gV10UxSwNjQA1Qa','kierownik'),(31,14,'Zigi','$2a$10$QTu8/TezC68gwiI6iyVqguDpldQ001fvY7e.2DWe6RdNojc.TD3iy','kierownik'),(33,1,'zz','$2a$10$BA/aQuasjiYR5MpM5BbBiOikPiIE2FMNMOpMDWQGsgHe8VPPyE8eC','kierownik'),(34,14,'filary44','$2a$10$vDxoyxTLZSBKyaMsjxN8nuCeFc/E2F95DQ9RAEozVypXKdZTgb4jy','kierownik'),(35,3,'adamski99','$2a$10$4PL0PKOlkbZj3iGXkI7fdeufSYPur8EYcwG2E4veGYgmggTEyFgf2','kierownik');
/*!40000 ALTER TABLE `Login` ENABLE KEYS */;
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
