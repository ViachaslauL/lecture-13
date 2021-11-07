package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.sql.Statement;

public class V1__Create_classes_table extends BaseJavaMigration {

    private final String sql = "CREATE TABLE IF NOT EXISTS `classes`(" +
            "`id` int NOT NULL AUTO_INCREMENT, " +
            "`class_code` varchar(6) NOT NULL, " +
            "PRIMARY KEY (`id`)" +
            ")ENGINE = InnoDB AUTO_INCREMENT = 0 DEFAULT CHARSET = utf8mb4";

    @Override
    public void migrate(Context context) throws Exception {
        try (Statement select = context.getConnection().createStatement()) {
            select.execute(sql);
        }
    }
}
