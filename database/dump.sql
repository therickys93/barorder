-- phpMyAdmin SQL Dump
-- version 4.6.5.1
-- https://www.phpmyadmin.net/
--
-- Host: mysql
-- Creato il: Dic 19, 2016 alle 09:43
-- Versione del server: 5.7.16
-- Versione PHP: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `barorder`
--
DROP DATABASE IF EXISTS `barorder`;
CREATE DATABASE IF NOT EXISTS `barorder` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `barorder`;

DELIMITER $$
--
-- Procedure
--
DROP PROCEDURE IF EXISTS `completeOrder`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `completeOrder` (IN `order_id` INT(11))  NO SQL
BEGIN 
	UPDATE barorder.order SET done = 1 WHERE barorder.order.id = order_id; 
END$$

DROP PROCEDURE IF EXISTS `deleteOrder`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteOrder` (IN `ID` INT(11))  NO SQL
BEGIN
DECLARE ended INT;
DECLARE product VARCHAR(255);
DECLARE cursore CURSOR FOR SELECT name FROM barorder.has_products WHERE id = ID;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET ended = 1;
OPEN cursore;
SET ended = 0;

WHILE ended = 0 DO
	FETCH cursore INTO product;
    DELETE FROM barorder.has_products WHERE id = ID AND name = product;
END WHILE;
CLOSE cursore;
DELETE FROM barorder.order WHERE id = ID;
END$$

DROP PROCEDURE IF EXISTS `insertNewOrder`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertNewOrder` (IN `id` INT(11), IN `table_n` VARCHAR(255), IN `name` VARCHAR(255), IN `quantity` INT(11))  NO SQL
BEGIN
	INSERT INTO barorder.order VALUES (id, table_n, '0');
	INSERT INTO barorder.has_products VALUES (id, name, quantity);
END$$

DROP PROCEDURE IF EXISTS `updateOrder`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateOrder` (IN `id` INT(11), IN `name` VARCHAR(255), IN `quantity` INT(11))  NO SQL
BEGIN
	INSERT INTO barorder.has_products VALUES (id, name, quantity);
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Struttura della tabella `has_products`
--

DROP TABLE IF EXISTS `has_products`;
CREATE TABLE `has_products` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `order`
--

DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(11) NOT NULL,
  `table` varchar(255) NOT NULL,
  `done` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `product`
--

INSERT INTO `product` (`name`) VALUES
('prova'),
('test');

--
-- Indici per le tabelle `order`
--
ALTER TABLE `order`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`name`);

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `has_products`
--
ALTER TABLE `has_products`
  ADD CONSTRAINT `has_products_ibfk_1` FOREIGN KEY (`id`) REFERENCES `order` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `has_products_ibfk_2` FOREIGN KEY (`name`) REFERENCES `product` (`name`) ON DELETE NO ACTION ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
