package by.itacademy.javaenterprise.lepnikau.app.sql;

public class StudentSQLRequests {
    public static final String INSERT =
        "INSERT INTO `students` (`last_name`,`first_name`,`patronymic`,`class_id`) VALUE (?,?,?,?)";
    public static final String SELECT_BY_ID =
            "SELECT * FROM `students` WHERE `id` = ?";
    public static final String SELECT_ALL =
            "SELECT * FROM `students` LIMIT ? OFFSET ?";
    public static final String LAST_ID =
            "SELECT id FROM `students` ORDER BY id DESC LIMIT 1";
}
