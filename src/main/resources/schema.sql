CREATE TABLE IF NOT EXISTS `product`
( `id` INT UNSIGNED NOT NULL AUTO_INCREMENT , `name` VARCHAR(10) NOT NULL , `price` FLOAT NOT NULL , PRIMARY KEY (`id`), UNIQUE `name_unique` (`name`)) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `user`
( `id` INT UNSIGNED NOT NULL AUTO_INCREMENT , `username` VARCHAR(10) NOT NULL , `email` VARCHAR(10) NOT NULL , `password` VARCHAR(255) NOT NULL, `roles` VARCHAR(25) NOT NULL, PRIMARY KEY (`id`), UNIQUE `username_unique` (`username`), UNIQUE `email_unique` (`email`)) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS tipos_cambio (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `monto` DOUBLE,
    `monedaorigen` VARCHAR(255),
    `monedadestino` VARCHAR(255),
    `montocontipocambio` DOUBLE,
    `tipocambio` DOUBLE,
    `solicitante` VARCHAR(255),
    `fechasolicitud` DATETIME);
