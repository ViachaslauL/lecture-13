CREATE DATABASE IF NOT EXISTS `school_diary`;
USE `school_diary`;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

CREATE TABLE IF NOT EXISTS `classes`(
    `id`         int NOT NULL AUTO_INCREMENT,
    `class_code` varchar(6) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 32
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE IF NOT EXISTS `marks`
(
    `id`         int NOT NULL AUTO_INCREMENT,
    `student_id` int NOT NULL,
    `mark`       int  NOT NULL,
    `subject_id` int NOT NULL,
    `date`       date NOT NULL,
    PRIMARY KEY (`id`),
    KEY `subject_id` (`subject_id`),
    KEY `student_id` (`student_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 32
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE IF NOT EXISTS `parents`
(
    `id`         int NOT NULL AUTO_INCREMENT,
    `student_id` int NOT NULL,
    `last_name`  varchar(32) NOT NULL,
    `first_name` varchar(32) NOT NULL,
    `patronymic` varchar(32) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `student_id` (`student_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 32
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE IF NOT EXISTS `students`
(
    `id`         int NOT NULL AUTO_INCREMENT,
    `last_name`  varchar(32) NOT NULL,
    `first_name` varchar(32) NOT NULL,
    `patronymic` varchar(32) NOT NULL,
    `class_id`   int NOT NULL,
    PRIMARY KEY (`id`),
    KEY `class_id` (`class_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  AUTO_INCREMENT = 32;

CREATE TABLE IF NOT EXISTS `subjects`
(
    `id`           int NOT NULL AUTO_INCREMENT,
    `subject_name` varchar(32) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 32
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE IF NOT EXISTS `teachers`
(
    `id`         int NOT NULL AUTO_INCREMENT,
    `last_name`  varchar(32) NOT NULL,
    `first_name` varchar(32) NOT NULL,
    `patronymic` varchar(32) NOT NULL,
    `subject_id` int NOT NULL,
    PRIMARY KEY (`id`),
    KEY `subject_id` (`subject_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 32
  DEFAULT CHARSET = utf8mb4;

ALTER TABLE `marks`
  ADD CONSTRAINT `marks_ibfk_1`
  FOREIGN KEY (`subject_id`)
  REFERENCES `subjects` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION,

  ADD CONSTRAINT `marks_ibfk_2`
  FOREIGN KEY (`student_id`)
  REFERENCES `students` (`id`)
  ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `parents`
  ADD CONSTRAINT `parents_ibfk_1`
  FOREIGN KEY (`student_id`)
  REFERENCES `students` (`id`)
  ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `students`
  ADD CONSTRAINT `students_ibfk_4`
  FOREIGN KEY (`class_id`)
  REFERENCES `classes` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `teachers`
  ADD CONSTRAINT `teachers_ibfk_1`
  FOREIGN KEY (`subject_id`)
  REFERENCES `subjects` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;
