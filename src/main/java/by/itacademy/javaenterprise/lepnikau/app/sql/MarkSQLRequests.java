package by.itacademy.javaenterprise.lepnikau.app.sql;

public class MarkSQLRequests {

    public static final String INSERT =
            "INSERT INTO `marks` (`student_id`,`mark`,`subject_id`,`date`) " +
                    "VALUES (?,?,?,?)";
    public static final String SELECT_BY_ID =
            "SELECT `id`, `student_id`, `mark`, `subject_id`, `date` " +
                    "FROM `marks` " +
                    "WHERE `id` = ? " +
                    "ORDER BY `date`";
    public static final String SELECT_ALL =
            "SELECT `id`, `student_id`, `mark`, `subject_id`, `date` " +
                    "FROM `marks` " +
                    "ORDER BY `date` " +
                    "LIMIT ? OFFSET ? ";
}
