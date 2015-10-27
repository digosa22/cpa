CREATE DATABASE IF NOT EXISTS `cpa_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE `cpa_dbdatos_plantilla`;

CREATE TABLE `datos_plantilla` (
`id` VARCHAR(10) NOT NULL PRIMARY KEY,
`hoja` int(2) NOT NULL,
`campo` VARCHAR(30) NOT NULL,
`valor` VARCHAR(30),
`tipo` int(2) NOT NULL,
`posicion` VARCHAR(30) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;