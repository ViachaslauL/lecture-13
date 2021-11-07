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
  AUTO_INCREMENT = 0;

ALTER TABLE `students`
    ADD CONSTRAINT `students_ibfk_4`
    FOREIGN KEY (`class_id`)
    REFERENCES `classes` (`id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION;
