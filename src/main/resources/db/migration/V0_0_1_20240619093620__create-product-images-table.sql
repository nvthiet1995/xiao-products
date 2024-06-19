CREATE TABLE `product_db`.`product_images` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  `link` TINYTEXT NOT NULL,
  `product_id` INT NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(50) NOT NULL,
  `updated_at` DATETIME NULL,
  `updated_by` VARCHAR(50) NULL,
  PRIMARY KEY (`id`))