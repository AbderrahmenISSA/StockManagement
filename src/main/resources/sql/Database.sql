-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema stockmanagement
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema stockmanagement
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `stockmanagement` DEFAULT CHARACTER SET utf8 ;
USE `stockmanagement` ;

-- -----------------------------------------------------
-- Table `stockmanagement`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `stockmanagement`.`category` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `category_name` VARCHAR(45) NOT NULL,
  `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `category_name_UNIQUE` (`category_name` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `stockmanagement`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `stockmanagement`.`product` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `product_name` VARCHAR(100) NOT NULL,
  `product_code` VARCHAR(45) NOT NULL,
  `stock` INT(11) NULL DEFAULT NULL,
  `price_label` VARCHAR(45) NOT NULL,
  `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `category` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `product_category_idx` (`category` ASC),
  CONSTRAINT `product_category`
    FOREIGN KEY (`category`)
    REFERENCES `stockmanagement`.`category` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
