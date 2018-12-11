-- MySQL Workbench Forward Engineering

-- -----------------------------------------------------
-- Schema stockmanagement
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema stockmanagement
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `stockmanagement` DEFAULT CHARACTER SET utf8;
USE `stockmanagement` ;

-- -----------------------------------------------------
-- Table `stockmanagement`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `stockmanagement`.`category` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `category_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `stockmanagement`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `stockmanagement`.`product` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `product_name` VARCHAR(100) NOT NULL,
  `price` DOUBLE NULL DEFAULT NULL,
  `category` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `product_category_idx` (`category` ASC) VISIBLE,
  CONSTRAINT `product_category`
    FOREIGN KEY (`category`)
    REFERENCES `stockmanagement`.`category` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;
