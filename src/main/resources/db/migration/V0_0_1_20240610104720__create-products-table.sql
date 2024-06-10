CREATE TABLE `product_db`.`product_details` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `value` VARCHAR(255) NOT NULL,
    `product_id` INT NOT NULL,
    `created_at` DATETIME NOT NULL,
    `created_by` VARCHAR(50) NOT NULL,
    `updated_at` DATETIME NULL,
    `updated_by` VARCHAR(50) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)