-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: tpdesarrollo
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `cuit` mediumtext,
  `email` varchar(50) DEFAULT NULL,
  `calle` varchar(50) DEFAULT NULL,
  `altura` int DEFAULT NULL,
  `ciudad` varchar(50) DEFAULT NULL,
  `pais` varchar(50) DEFAULT NULL,
  `lat` double DEFAULT NULL,
  `lng` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'Mateo Gastaldi','2454893433','mateogastaldi@gmail.com','Llerena',2240,'Santa Fe','Argentina',31.37,60.45),(2,'Sofía Rodríguez','3104567890','sofia.rodriguez@gmail.com','Mitre',125,'Rosario','Argentina',32.94,60.64),(3,'Lucas Martínez','2709876543','lucas.martinez@gmail.com','San Martín',3500,'Córdoba','Argentina',31.41,64.18),(4,'Martina López','2901234567','martina.lopez@gmail.com','Alem',987,'Mendoza','Argentina',32.88,68.84),(5,'Julián Fernández','3009871234','julian.fernandez@gmail.com','Belgrano',452,'Buenos Aires','Argentina',-34.61,-58.38),(6,'Camila Sánchez','2804567891','camila.sanchez@gmail.com','Rivadavia',789,'Salta','Argentina',-24.79,-65.41),(7,'Emilia Pérez','3106785432','emilia.perez@gmail.com','Sarmiento',2345,'Neuquén','Argentina',-38.95,-68.06),(8,'Joaquín Gómez','2954321098','joaquin.gomez@gmail.com','Independencia',456,'Tucumán','Argentina',-26.82,-65.22),(9,'Valentina Herrera','3056789012','valentina.herrera@gmail.com','Italia',102,'Santa Cruz','Argentina',-50.33,-72.27),(10,'Francisco Romero','3158901234','francisco.romero@gmail.com','España',678,'La Pampa','Argentina',-36.62,-64.29);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-17 16:01:53
