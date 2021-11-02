package by.itacademy.javaenterprise.lepnikau.app.sql;

public class ParentSQLRequests {

    public static final String INSERT =
            "INSERT INTO `parents` (`student_id`,`last_name`,`first_name`,`patronymic`) " +
                    "VALUE (?,?,?,?)";
    public static final String SELECT_BY_ID =
            "SELECT `id`, `student_id`, `last_name`, `first_name`, `patronymic` " +
                    "FROM `parents` " +
                    "ORDER BY `last_name`" +
                    "WHERE `id` = ?";
    public static final String SELECT_ALL =
            "SELECT `id`, `student_id`, `last_name`, `first_name`, `patronymic` " +
                    "FROM `parents` " +
                    "ORDER BY `last_name` " +
                    "LIMIT ? OFFSET ?";
}
