package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.sql.Statement;

public class V3__Create_parents_table extends BaseJavaMigration {

    private final String sql = "CREATE TABLE IF NOT EXISTS `parents`(" +
            "    `id`         int NOT NULL AUTO_INCREMENT," +
            "    `student_id` int NOT NULL," +
            "    `last_name`  varchar(32) NOT NULL," +
            "    `first_name` varchar(32) NOT NULL," +
            "    `patronymic` varchar(32) NOT NULL," +
            "    PRIMARY KEY (`id`)," +
            "    KEY `student_id` (`student_id`)," +
            "    CONSTRAINT `parents_ibfk_1` " +
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
