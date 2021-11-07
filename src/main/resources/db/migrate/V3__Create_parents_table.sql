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
  AUTO_INCREMENT = 0
  DEFAULT CHARSET = utf8mb4;

ALTER TABLE `parents`
    ADD CONSTRAINT `parents_ibfk_1`
    FOREIGN KEY (`student_id`)
    REFERENCES `students` (`id`)
    ON DELETE CASCADE ON UPDATE CASCADE;

