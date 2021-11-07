package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.sql.Statement;

public class V5__Create_teachers_table extends BaseJavaMigration {

    private final String sql = "CREATE TABLE IF NOT EXISTS `teachers`(" +
            "    `id`         int NOT NULL AUTO_INCREMENT," +
            "    `last_name`  varchar(32) NOT NULL," +
            "    `first_name` varchar(32) NOT NULL," +
            "    `patronymic` varchar(32) NOT NULL," +
            "    `subject_id` int NOT NULL," +
            "    PRIMARY KEY (`id`)," +
            "    KEY `subject_id` (`subject_id`)," +
            "CONSTRAINT `teachers_ibfk_1` " +
            "FOREIGN KEY (`subject_id`) " +
            "REFERENCES `subjects` (`id`) " +
            "ON DELETE NO ACTION ON UPDATE NO ACTION" +
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
