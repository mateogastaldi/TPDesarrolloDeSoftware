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
-- Table structure for table `itemmenu`
--

DROP TABLE IF EXISTS `itemmenu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `itemmenu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `aptoVegano` tinyint(1) DEFAULT NULL,
  `aptoCeliaco` tinyint(1) DEFAULT NULL,
  `id_categoria` int DEFAULT NULL,
  `id_vendedor` int DEFAULT NULL,
  `calorias` double DEFAULT NULL,
  `peso` double DEFAULT NULL,
  `gradAlcoholica` double DEFAULT NULL,
  `tamanio` double DEFAULT NULL,
  `tipo` enum('Plato','Bebida') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_categoria` (`id_categoria`),
  CONSTRAINT `itemmenu_ibfk_1` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itemmenu`
--

LOCK TABLES `itemmenu` WRITE;
/*!40000 ALTER TABLE `itemmenu` DISABLE KEYS */;
INSERT INTO `itemmenu` VALUES (1,'Parrillada para 2','Parrilla completa para dos personas',15600,0,1,3,1,500,1000,NULL,NULL,'Plato'),(2,'Milanesa con papas fritas','Clásica milanesa de carne con papas fritas doradas',12000,0,0,3,1,850,700,NULL,NULL,'Plato'),(3,'Ensalada César','Lechuga, pollo grillado, croutones y aderezo césar',8900,1,1,3,1,250,350,NULL,NULL,'Plato'),(4,'Sopa de verduras','Sopa caliente con vegetales frescos',5600,1,1,4,1,120,300,NULL,NULL,'Plato'),(5,'Pizza Margarita','Pizza con salsa de tomate, mozzarella y albahaca',15000,1,0,3,1,1200,900,NULL,NULL,'Plato'),(6,'Empanadas de carne','Media docena de empanadas al horno',7200,0,0,4,1,450,600,NULL,NULL,'Plato'),(7,'Cerveza Quilmes','Botella de cerveza Quilmes 1L',2000,1,1,6,1,NULL,NULL,4.5,1000,'Bebida'),(8,'Fanta Naranja','Botella de Fanta de 500ml',1400,1,1,2,1,NULL,NULL,0,500,'Bebida'),(9,'Vino Malbec','Botella de vino Malbec de 750ml',3500,1,1,5,1,NULL,NULL,13.5,750,'Bebida'),(10,'Jugo de naranja','Vaso de jugo de naranja exprimido',1200,1,1,8,1,NULL,NULL,0,300,'Bebida'),(11,'CocaCola chica','Botella de CocaCola de 500ml',1500,1,1,2,1,NULL,NULL,0,500,'Bebida'),(12,'Agua mineral','Botella de agua mineral de 500ml',800,1,1,9,1,NULL,NULL,0,500,'Bebida'),(13,'Medialunas de manteca','Medialunas frescas y esponjosas con un toque de manteca',1000,0,0,7,2,120,50,NULL,NULL,'Plato'),(14,'Torta de chocolate','Bizcochuelo de chocolate con relleno de dulce de leche',4500,1,0,1,2,420,1200,NULL,NULL,'Plato'),(15,'Facturas surtidas','Media docena de facturas variadas',3500,0,0,7,2,250,600,NULL,NULL,'Plato'),(16,'Pan de campo','Pan artesanal horneado al estilo tradicional',1800,1,0,7,2,150,500,NULL,NULL,'Plato'),(17,'Cookies con chips de chocolate','Galletas caseras con chips de chocolate',2200,1,0,7,2,300,300,NULL,NULL,'Plato'),(18,'Tarta de frutilla','Tarta con base de masa sablée, crema pastelera y frutillas frescas',5200,1,0,1,2,380,800,NULL,NULL,'Plato'),(19,'Helado de chocolate','Helado artesanal de chocolate con cacao puro',1500,1,1,1,3,250,200,NULL,NULL,'Plato'),(20,'Helado de vainilla','Helado cremoso de vainilla con un toque de esencia natural',1400,1,1,1,3,220,200,NULL,NULL,'Plato'),(21,'Helado de dulce de leche','Helado artesanal de dulce de leche con salsa extra',1600,1,1,1,3,270,200,NULL,NULL,'Plato'),(22,'Helado de frutilla','Helado fresco de frutilla elaborado con fruta natural',1400,1,1,1,3,190,200,NULL,NULL,'Plato'),(23,'Copa helada combinada','Copa de helado con 3 sabores a elección, salsa y topping',2800,1,0,1,3,380,400,NULL,NULL,'Plato'),(24,'Helado vegano de almendras','Helado vegano elaborado con leche de almendras',1900,1,1,1,3,200,200,NULL,NULL,'Plato'),(25,'Helado sin azúcar de limón','Helado apto para diabéticos con sabor a limón',1800,1,1,1,3,90,200,NULL,NULL,'Plato'),(26,'Batido de frutilla','Batido frío de frutilla con leche',2000,0,0,1,3,NULL,NULL,0,500,'Bebida'),(27,'Batido de vainilla','Batido de helado de vainilla con leche',2000,0,0,1,3,NULL,NULL,0,500,'Bebida'),(28,'Batido vegano de chocolate','Batido helado vegano de chocolate con leche de almendras',2200,1,1,1,3,NULL,NULL,0,500,'Bebida');
/*!40000 ALTER TABLE `itemmenu` ENABLE KEYS */;
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
