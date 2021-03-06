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

DROP PROCEDURE IF EXISTS `payOrder`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `payOrder` (IN `order_id` INT(11))  NO SQL
BEGIN 
	UPDATE barorder.order SET pay = 1 WHERE barorder.order.id = order_id; 
END$$

DROP PROCEDURE IF EXISTS `deleteOrder`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteOrder` (IN `ORDER_ID` INT(11))  NO SQL
BEGIN
DECLARE ended INT;
DECLARE product VARCHAR(255);
DECLARE cursore CURSOR FOR SELECT name FROM barorder.has_products WHERE id = ORDER_ID;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET ended = 1;
OPEN cursore;
SET ended = 0;

WHILE ended = 0 DO
	FETCH cursore INTO product;
    DELETE FROM barorder.has_products WHERE id = ORDER_ID AND name = product;
END WHILE;
CLOSE cursore;
DELETE FROM barorder.order WHERE id = ORDER_ID;
END$$

DROP PROCEDURE IF EXISTS `deleteProduct`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteProduct` (IN `product_name` VARCHAR(255))  NO SQL
BEGIN
  DELETE FROM barorder.product WHERE name = product_name;
END$$

DROP PROCEDURE IF EXISTS `deleteProductAll`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteProductAll` ()  NO SQL
BEGIN
  DELETE FROM barorder.product;
END$$

DROP PROCEDURE IF EXISTS `deleteOrderAll`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteOrderAll` ()  NO SQL
BEGIN
  DELETE FROM barorder.has_products;
  DELETE FROM barorder.order;
END$$

DROP PROCEDURE IF EXISTS `insertNewOrder`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertNewOrder` (IN `id` INT(11), IN `table_n` VARCHAR(255), IN `name` VARCHAR(255), IN `quantity` INT(11))  NO SQL
BEGIN
	INSERT INTO barorder.order VALUES (id, table_n, '0', '0', '0.00');
	INSERT INTO barorder.has_products VALUES (id, name, quantity);
END$$

DROP PROCEDURE IF EXISTS `insertProduct`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertProduct` (IN `NAME` VARCHAR(255), IN `PRICE` DOUBLE(5,2))  NO SQL
BEGIN
  INSERT INTO barorder.product VALUES (NAME, PRICE);
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
-- Triggers `has_products`
--
DROP TRIGGER IF EXISTS `update_price`;
DELIMITER $$
CREATE TRIGGER `update_price` AFTER INSERT ON `has_products` FOR EACH ROW BEGIN
  DECLARE new_price DOUBLE(5,2);
    SET new_price = (SELECT SUM(has_products.quantity * product.price) AS price FROM has_products, product WHERE has_products.name = product.name AND id = NEW.id);
    UPDATE barorder.order SET price = new_price WHERE id = NEW.id;
END
$$
DELIMITER ;

--
-- Struttura della tabella `order`
--

DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(11) NOT NULL,
  `table` varchar(255) NOT NULL,
  `done` tinyint(1) NOT NULL DEFAULT '0',
  `pay` tinyint(1) NOT NULL DEFAULT '0',
  `price` double(5,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `name` varchar(255) NOT NULL,
  `price` double(5,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `product`
--

INSERT INTO `product` (`name`, `price`) VALUES
('acqua', '0.50'),
('bibite', '1.00'),
('caffe al ginseng', '1.50'),
('caffe americano', '1.50'),
('caffe d\'orzo', '1.00'),
('caffe decaffeinato', '1.20'),
('caffe decaffeinato shakerato', '1.30'),
('caffe espresso', '1.00'),
('caffe freddo', '2.00'),
('caffe latte', '2.50'),
('caffe shakerato', '1.50'),
('cappuccino', '2.50'),
('cappuccino al ginseng', '2.80'),
('cappuccino d\'orzo', '2.80'),
('cappuccino decaffeinato', '2.80'),
('cappuccino freddo', '2.80'),
('ciambella', '3.00'),
('cioccolata', '3.50'),
('cioccolata con panna', '4.00'),
('crema di caffe', '3.50'),
('crostata', '2.50'),
('frullati vari', '3.00'),
('insalatone', '5.00'),
('latte bianco', '2.00'),
('latte macchiato', '2.50'),
('panini assortiti', '4.50'),
('spremuta', '3.50'),
('succhi di frutta', '3.00'),
('the ed infusi', '2.50'),
('the freddo', '2.50'),
('toast', '5.00'),
('tramezzini assortiti', '4.50');

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
  ADD CONSTRAINT `has_products_ibfk_2` FOREIGN KEY (`name`) REFERENCES `product` (`name`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
