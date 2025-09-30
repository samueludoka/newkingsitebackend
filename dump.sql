-- MySQL dump 10.13  Distrib 8.0.43, for Linux (x86_64)
--
-- Host: localhost    Database: newkingsite
-- ------------------------------------------------------
-- Server version	8.0.43-0ubuntu0.22.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'kingsline@gmail.com','Kings Oman','##1234');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `amount_to_deposit`
--

DROP TABLE IF EXISTS `amount_to_deposit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `amount_to_deposit` (
  `id` bigint NOT NULL,
  `amount` decimal(38,2) DEFAULT NULL,
  `coin_type` tinyint DEFAULT NULL,
  `customer_wallet_address` varchar(255) DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `wallet_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKf1i70urgunmdrlrck3seqwq6k` (`wallet_id`),
  CONSTRAINT `FKd1mrqhydo8fr43hiihomxyhhm` FOREIGN KEY (`wallet_id`) REFERENCES `wallet` (`id`),
  CONSTRAINT `amount_to_deposit_chk_1` CHECK ((`coin_type` between 0 and 15)),
  CONSTRAINT `amount_to_deposit_chk_2` CHECK ((`status` between 0 and 1))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `amount_to_deposit`
--

LOCK TABLES `amount_to_deposit` WRITE;
/*!40000 ALTER TABLE `amount_to_deposit` DISABLE KEYS */;
/*!40000 ALTER TABLE `amount_to_deposit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `amount_to_deposit_seq`
--

DROP TABLE IF EXISTS `amount_to_deposit_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `amount_to_deposit_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `amount_to_deposit_seq`
--

LOCK TABLES `amount_to_deposit_seq` WRITE;
/*!40000 ALTER TABLE `amount_to_deposit_seq` DISABLE KEYS */;
INSERT INTO `amount_to_deposit_seq` VALUES (1);
/*!40000 ALTER TABLE `amount_to_deposit_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `country` enum('AFGHANISTAN','ALBANIA','ALGERIA','ANDORRA','ANGOLA','ANTIGUA_AND_BARBUDA','ARGENTINA','ARMENIA','AUSTRALIA','AUSTRIA','AZERBAIJAN','BAHAMAS','BAHRAIN','BANGLADESH','BARBADOS','BELARUS','BELGIUM','BELIZE','BENIN','BHUTAN','BOLIVIA','BOSNIA_AND_HERZEGOVINA','BOTSWANA','BRAZIL','BRUNEI','BULGARIA','BURKINA_FASO','BURUNDI','CABO_VERDE','CAMBODIA','CAMEROON','CANADA','CENTRAL_AFRICAN_REPUBLIC','CHAD','CHILE','CHINA','COLOMBIA','COMOROS','CONGO_DEMOCRATIC_REPUBLIC','CONGO_REPUBLIC','COSTA_RICA','CROATIA','CUBA','CYPRUS','CZECHIA','DENMARK','DJIBOUTI','DOMINICA','DOMINICAN_REPUBLIC','ECUADOR','EGYPT','EL_SALVADOR','EQUATORIAL_GUINEA','ERITREA','ESTONIA','ESWATINI','ETHIOPIA','FIJI','FINLAND','FRANCE','GABON','GAMBIA','GEORGIA','GERMANY','GHANA','GREECE','GRENADA','GUATEMALA','GUINEA','GUINEA_BISSAU','GUYANA','HAITI','HONDURAS','HUNGARY','ICELAND','INDIA','INDONESIA','IRAN','IRAQ','IRELAND','ISRAEL','ITALY','JAMAICA','JAPAN','JORDAN','KAZAKHSTAN','KENYA','KIRIBATI','KOREA_NORTH','KOREA_SOUTH','KOSOVO','KUWAIT','KYRGYZSTAN','LAOS','LATVIA','LEBANON','LESOTHO','LIBERIA','LIBYA','LIECHTENSTEIN','LITHUANIA','LUXEMBOURG','MADAGASCAR','MALAWI','MALAYSIA','MALDIVES','MALI','MALTA','MAROCCO','MAURITANIA','MAURITIUS','MEXICO','MICRONESIA','MOLDOVA','MONACO','MONGOLIA','MONTENEGRO','MOROCCO','MOZAMBIQUE','MYANMAR','NAMIBIA','NAURU','NEPAL','NETHERLANDS','NEW_ZEALAND','NICARAGUA','NIGER','NIGERIA','NORTH_MACEDONIA','NORWAY','OMAN','PAKISTAN','PALAU','PANAMA','PAPUA_NEW_GUINEA','PARAGUAY','PERU','PHILIPPINES','POLAND','PORTUGAL','QATAR','ROMANIA','RUSSIA','RWANDA','SAINT_KITTS_AND_NEVIS','SAINT_LUCIA','SAINT_VINCENT_AND_THE_GRENADINES','SAMOA','SAN_MARINO','SAO_TOME_AND_PRINCIPE','SAUDI_ARABIA','SENEGAL','SERBIA','SEYCHELLES','SIERRA_LEONE','SINGAPORE','SLOVAKIA','SLOVENIA','SOLOMON_ISLANDS','SOMALIA','SOUTH_AFRICA','SOUTH_SUDAN','SPAIN','SRI_LANKA','SUDAN','SURINAME','SWEDEN','SWITZERLAND','SYRIA','TAIWAN','TAJIKISTAN','TANZANIA','THAILAND','TOGO','TONGA','TRINIDAD_AND_TOBAGO','TUNISIA','TURKEY','TURKMENISTAN','TUVALU','UGANDA','UKRAINE','UNITED_ARAB_EMIRATES','UNITED_KINGDOM','UNITED_STATES','URUGUAY','UZBEKISTAN','VANUATU','VATICAN_CITY','VENEZUELA','VIETNAM','YEMEN','ZAMBIA','ZIMBABWE') DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `forgot_password_fpid` bigint DEFAULT NULL,
  `wallet_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKq8pn8uxqffxkik4921awgf7ut` (`forgot_password_fpid`),
  UNIQUE KEY `UKnd41s1hb6o5535y6nq7obt0qb` (`wallet_id`),
  CONSTRAINT `FKgadeaynu91oljdr3ofje0jt4y` FOREIGN KEY (`wallet_id`) REFERENCES `wallet` (`id`),
  CONSTRAINT `FKhfb64i97x1ycbb8hf32v7ldil` FOREIGN KEY (`forgot_password_fpid`) REFERENCES `forgot_password` (`fpid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_roles`
--

DROP TABLE IF EXISTS `customer_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_roles` (
  `customer_id` bigint NOT NULL,
  `roles` tinyint DEFAULT NULL,
  KEY `FK5mxc61l5u87g0rjsselvw7dk3` (`customer_id`),
  CONSTRAINT `FK5mxc61l5u87g0rjsselvw7dk3` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `customer_roles_chk_1` CHECK ((`roles` between 0 and 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_roles`
--

LOCK TABLES `customer_roles` WRITE;
/*!40000 ALTER TABLE `customer_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_transactions`
--

DROP TABLE IF EXISTS `customer_transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_transactions` (
  `customer_id` bigint NOT NULL,
  `transactions_id` bigint NOT NULL,
  UNIQUE KEY `UK1vskh29saveqya2u0tbymh26x` (`transactions_id`),
  KEY `FKmaae9pb34ym9gsllyv3h029ye` (`customer_id`),
  CONSTRAINT `FKmaae9pb34ym9gsllyv3h029ye` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `FKok6f8kifjcb0l6bg5m93ythi` FOREIGN KEY (`transactions_id`) REFERENCES `transaction` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_transactions`
--

LOCK TABLES `customer_transactions` WRITE;
/*!40000 ALTER TABLE `customer_transactions` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer_transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `forgot_password`
--

DROP TABLE IF EXISTS `forgot_password`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `forgot_password` (
  `fpid` bigint NOT NULL AUTO_INCREMENT,
  `expiration_time` datetime(6) NOT NULL,
  `otp` int NOT NULL,
  `customer_id` bigint DEFAULT NULL,
  PRIMARY KEY (`fpid`),
  UNIQUE KEY `UK56rxg2ou2wtthv5v46ng8764h` (`customer_id`),
  CONSTRAINT `FKcrl2m5grthwb4m78742v50g5x` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forgot_password`
--

LOCK TABLES `forgot_password` WRITE;
/*!40000 ALTER TABLE `forgot_password` DISABLE KEYS */;
/*!40000 ALTER TABLE `forgot_password` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `investment`
--

DROP TABLE IF EXISTS `investment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `investment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount` decimal(38,2) DEFAULT NULL,
  `coin_type` enum('ADA','BCH','Binance','Bitcoin','DOT','Dogecoin','Ethereum','Gold','Platinum','Shiba_Inu_Avax','Silver','Solace','Ton','USDC','USDT','XRP') DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `plan_type` enum('BASIC','EXECUTIVE','GOLD','MASTER','PLATINUM','PREMIUM','SILVER') DEFAULT NULL,
  `customer_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlo1q646qh5qw2vuxmral8ar75` (`customer_id`),
  CONSTRAINT `FKlo1q646qh5qw2vuxmral8ar75` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `investment`
--

LOCK TABLES `investment` WRITE;
/*!40000 ALTER TABLE `investment` DISABLE KEYS */;
/*!40000 ALTER TABLE `investment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trade`
--

DROP TABLE IF EXISTS `trade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trade` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount_after` decimal(38,2) DEFAULT NULL,
  `amount_before` decimal(38,2) DEFAULT NULL,
  `trade_status` tinyint DEFAULT NULL,
  `trade_time` datetime(6) DEFAULT NULL,
  `investment_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKs4wndvgrib75sp1selgeyi9fh` (`investment_id`),
  CONSTRAINT `FKs4wndvgrib75sp1selgeyi9fh` FOREIGN KEY (`investment_id`) REFERENCES `investment` (`id`),
  CONSTRAINT `trade_chk_1` CHECK ((`trade_status` between 0 and 3))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trade`
--

LOCK TABLES `trade` WRITE;
/*!40000 ALTER TABLE `trade` DISABLE KEYS */;
/*!40000 ALTER TABLE `trade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL,
  `coin_type` enum('ADA','BCH','Binance','Bitcoin','DOT','Dogecoin','Ethereum','Gold','Platinum','Shiba_Inu_Avax','Silver','Solace','Ton','USDC','USDT','XRP') DEFAULT NULL,
  `transaction_date` datetime(6) DEFAULT NULL,
  `transaction_type` enum('INVEST','SOLD') DEFAULT NULL,
  `customer_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnbpjofb5abhjg5hiovi0t3k57` (`customer_id`),
  CONSTRAINT `FKnbpjofb5abhjg5hiovi0t3k57` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,10,NULL,'2025-09-29 18:51:15.703594','INVEST',NULL);
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wallet`
--

DROP TABLE IF EXISTS `wallet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wallet` (
  `id` bigint NOT NULL,
  `balance` decimal(38,2) DEFAULT NULL,
  `amount_to_deposit_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKf9dh9vtvvfug1783n2k735lbg` (`amount_to_deposit_id`),
  CONSTRAINT `FKoum0c8lbplcabdu4yonu4n7b9` FOREIGN KEY (`amount_to_deposit_id`) REFERENCES `amount_to_deposit` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wallet`
--

LOCK TABLES `wallet` WRITE;
/*!40000 ALTER TABLE `wallet` DISABLE KEYS */;
/*!40000 ALTER TABLE `wallet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wallet_seq`
--

DROP TABLE IF EXISTS `wallet_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wallet_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wallet_seq`
--

LOCK TABLES `wallet_seq` WRITE;
/*!40000 ALTER TABLE `wallet_seq` DISABLE KEYS */;
INSERT INTO `wallet_seq` VALUES (1);
/*!40000 ALTER TABLE `wallet_seq` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-09-30 11:52:04
