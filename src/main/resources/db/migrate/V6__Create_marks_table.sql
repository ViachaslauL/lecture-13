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
  AUTO_INCREMENT = 0
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