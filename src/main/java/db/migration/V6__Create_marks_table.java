package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.sql.Statement;

public class V6__Create_marks_table extends BaseJavaMigration {

    private final String sql = "CREATE TABLE IF NOT EXISTS `marks`(" +
            "    `id`         int NOT NULL AUTO_INCREMENT," +
            "    `student_id` int NOT NULL," +
            "    `mark`       int  NOT NULL," +
            "    `subject_id` int NOT NULL," +
            "    `date`       date NOT NULL," +
            "    PRIMARY KEY (`id`)," +
            "    KEY `subject_id` (`subject_id`)," +
            "    KEY `student_id` (`student_id`)," +
            "    CONSTRAINT `marks_ibfk_1` " +
            "    FOREIGN KEY (`subject_id`) " +
            "    REFERENCES `subjects` (`id`) " +
            "    ON DELETE NO ACTION ON UPDATE NO ACTION," +
            "    CONSTRAINT `marks_ibfk_2` " +
            "    FOREIGN KEY (`student_id`) " +
            "    REFERENCES `students` (`id`) " +
            "    ON DELETE CASCADE ON UPDATE CASCADE" +
            ") ENGINE = InnoDB" +
            "  AUTO_INCREMENT = 0" +
            "  DEFAULT CHARSET = utf8mb4";

    @Override
    public void migrate(Context context) throws Exception {
        try (Statement select = context.getConnection().createStatement()) {
            select.execute(sql);
        }
    }
}
