-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 29-09-2023 a las 20:06:20
-- Versión del servidor: 8.0.31
-- Versión de PHP: 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `usuarios-meli`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usu_meli`
--

DROP TABLE IF EXISTS `usu_meli`;
CREATE TABLE IF NOT EXISTS `usu_meli` (
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `permissions` varchar(30) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `usu_meli`
--

INSERT INTO `usu_meli` (`username`, `password`, `permissions`) VALUES
('Secops21_MercL', 'MercL21', 'ALL'),
('ME2JK', 'Mercado2021', 'Insert - Edit'),
('MerCSL', 'MerC23', 'Select'),
('MEJS75', 'Meli80', 'Delete');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
