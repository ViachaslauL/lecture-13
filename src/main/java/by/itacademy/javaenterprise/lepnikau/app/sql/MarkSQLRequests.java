package by.itacademy.javaenterprise.lepnikau.app.sql;

public class MarkSQLRequests {

    public static final String INSERT =
            "INSERT INTO `marks` (`student_id`,`mark`,`subject_id`,`date`) VALUES (?,?,?,?)";
    public static final String SELECT_BY_ID =
            "SELECT * FROM `marks` WHERE `id` = ?";
    public static final String SELECT_ALL =
            "SELECT * FROM `marks` LIMIT ? OFFSET ?";
}
