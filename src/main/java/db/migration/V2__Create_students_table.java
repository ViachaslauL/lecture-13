package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.sql.Statement;

public class V2__Create_students_table extends BaseJavaMigration {

    private final String sql = "CREATE TABLE IF NOT EXISTS `students`(" +
            "    `id` int NOT NULL AUTO_INCREMENT," +
            "    `last_name` varchar(32) NOT NULL, " +
            "    `first_name` varchar(32) NOT NULL, " +
            "    `patronymic` varchar(32) NOT NULL, " +
            "    `class_id` int NOT NULL, " +
            "    PRIMARY KEY (`id`), " +
            "    KEY `class_id` (`class_id`)," +
            "    CONSTRAINT `students_ibfk_4` " +
            "    FOREIGN KEY (`class_id`) " +
            "    REFERENCES `classes` (`id`) " +
            "    ON DELETE NO ACTION ON UPDATE NO ACTION" +
            ") ENGINE = InnoDB " +
            "DEFAULT CHARSET = utf8mb4 " +
            "AUTO_INCREMENT = 0";

    @Override
    public void migrate(Context context) throws Exception {
        try (Statement select = context.getConnection().createStatement()) {
            select.execute(sql);
        }
    }
}
