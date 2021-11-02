package by.itacademy.javaenterprise.lepnikau.app.dao.implement;

import by.itacademy.javaenterprise.lepnikau.app.dao.StudentDAO;
import by.itacademy.javaenterprise.lepnikau.app.dao.implement.util.DAOUtil;
import by.itacademy.javaenterprise.lepnikau.app.entity.Student;
import by.itacademy.javaenterprise.lepnikau.app.sql.StudentSQLRequests;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class StudentDAOImpl implements StudentDAO {

    private static final Logger log = LoggerFactory.getLogger(StudentDAOImpl.class);

    private DataSource dataSource;

    @Override
    public Student save(Student student) {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = dataSource.getConnection();
            stmt = connection.prepareStatement(StudentSQLRequests.INSERT);

            stmt.setString(1, student.getLastName());
            stmt.setString(2, student.getFirstName());
            stmt.setString(3, student.getPatronymic());
            stmt.setInt(4, student.getClassId());
            if (stmt.executeUpdate() > 0) {
                return student;
            }
        } catch (SQLException e) {
            log.error(e.toString());
        } finally {
            DAOUtil.closePrepareStatement(stmt);
            DAOUtil.closeConnection(connection);
        }
        return null;
    }

    @Override
    public Student get(int id) {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = dataSource.getConnection();
            stmt = connection.prepareStatement(StudentSQLRequests.SELECT_BY_ID);

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return createStudentEntity(rs);
            }
        } catch (SQLException e) {
            log.error(e.toString());
        } finally {
            DAOUtil.closePrepareStatement(stmt);
            DAOUtil.closeConnection(connection);
        }
        return null;
    }

    @Override
    public List<Student> getAllPageByPage(int limit, int offset) {
        List<Student> students = new ArrayList<>();
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = dataSource.getConnection();
            stmt = connection.prepareStatement(StudentSQLRequests.SELECT_ALL);

            ResultSet rs = stmt.executeQuery();
            stmt.setInt(1, limit);
            stmt.setInt(2, offset);
            while (rs.next()) {
                students.add(createStudentEntity(rs));
            }
        } catch (SQLException e) {
            log.error(e.toString());
        } finally {
            DAOUtil.closePrepareStatement(stmt);
            DAOUtil.closeConnection(connection);
        }
        return students;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Student createStudentEntity(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setId(rs.getInt("id"));
        student.setLastName(rs.getString("last_name"));
        student.setFirstName(rs.getString("first_name"));
        student.setPatronymic(rs.getString("patronymic"));
        student.setClassId(rs.getInt("class_id"));
        return student;
    }
}
