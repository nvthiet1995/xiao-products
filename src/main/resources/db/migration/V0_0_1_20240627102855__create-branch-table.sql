CREATE TABLE `product_db`.`branches` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  `logo` TINYTEXT NOT NULL,
  `description` TEXT,
  `country` VARCHAR(100),
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(50) NOT NULL,
  `updated_at` DATETIME NULL,
  `updated_by` VARCHAR(50) NULL,
  PRIMARY KEY (`id`))