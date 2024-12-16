CREATE TABLE `product_db`.`products` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` TINYTEXT NOT NULL,
  `description` LONGTEXT NOT NULL,
  `left_in_stock` INT NOT NULL,
  `specifications` TEXT NULL,
  `branch_id` INT NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(50) NOT NULL,
  `updated_at` DATETIME NULL,
  `updated_by` VARCHAR(50) NULL,
  PRIMARY KEY (`id`))