package by.itacademy.javaenterprise.lepnikau.app.sql;

public class ParentSQLRequests {

    public static final String INSERT =
            "INSERT INTO `parents` (`student_id`,`last_name`,`first_name`,`patronymic`) VALUE (?,?,?,?)";
    public static final String SELECT_BY_ID =
            "SELECT * FROM `parents` WHERE `id` = ?";
    public static final String SELECT_ALL =
            "SELECT * FROM `parents` LIMIT ? OFFSET ?";
}
