package by.itacademy.javaenterprise.lepnikau.app.dao.implement;

import by.itacademy.javaenterprise.lepnikau.app.dao.StudentDAO;
import by.itacademy.javaenterprise.lepnikau.app.dao.util.DAOServant;
import by.itacademy.javaenterprise.lepnikau.app.entity.Parent;
import by.itacademy.javaenterprise.lepnikau.app.entity.Student;
import by.itacademy.javaenterprise.lepnikau.app.connection.DSCreator;
import by.itacademy.javaenterprise.lepnikau.app.sql.ParentSQLRequests;
import by.itacademy.javaenterprise.lepnikau.app.sql.StudentSQLRequests;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    private static final Logger log = LoggerFactory.getLogger(StudentDAOImpl.class.getSimpleName());

    @Override
    public boolean saveStudentAndParent(Student student, Parent parent) {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = DSCreator.getDataSource().getConnection();
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(StudentSQLRequests.INSERT);

            stmt.setString(1, student.getLastName());
            stmt.setString(2, student.getFirstName());
            stmt.setString(3, student.getPatronymic());
            stmt.setInt(4, student.getClassId());

            if (stmt.executeUpdate() > 0) {
                DAOServant.closePrepareStatement(stmt);
            }

            int studentId = 0;
            stmt = connection.prepareStatement(StudentSQLRequests.LAST_ID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                studentId = rs.getInt("id");
                DAOServant.closePrepareStatement(stmt);
            }

            stmt = connection.prepareStatement(ParentSQLRequests.INSERT);

            stmt.setInt(1, studentId);
            stmt.setString(2, parent.getLastName());
            stmt.setString(3, parent.getFirstName());
            stmt.setString(4, parent.getPatronymic());
            if (stmt.executeUpdate() > 0) {
                connection.commit();
                return true;
            }
        } catch (SQLException e) {
            log.error(e.toString());
            DAOServant.rollbackConnection(connection);
        } finally {
            DAOServant.closePrepareStatement(stmt);
            DAOServant.closeConnection(connection);
        }
        return false;
    }

    @Override
    public Student save(Student student) {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = DSCreator.getDataSource().getConnection();
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
            DAOServant.closePrepareStatement(stmt);
            DAOServant.closeConnection(connection);
        }
        return null;
    }

    @Override
    public Student get(int id) {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = DSCreator.getDataSource().getConnection();
            stmt = connection.prepareStatement(StudentSQLRequests.SELECT_BY_ID);

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return getStudent(rs);
            }
        } catch (SQLException e) {
            log.error(e.toString());
        } finally {
            DAOServant.closePrepareStatement(stmt);
            DAOServant.closeConnection(connection);
        }
        return null;
    }

    @Override
    public List<Student> getAllPageByPage(int limit, int offset) {
        List<Student> students = new ArrayList<>();
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = DSCreator.getDataSource().getConnection();
            stmt = connection.prepareStatement(StudentSQLRequests.SELECT_ALL);

            ResultSet rs = stmt.executeQuery();
            stmt.setInt(1, limit);
            stmt.setInt(2, offset);
            while (rs.next()) {
                students.add(getStudent(rs));
            }
        } catch (SQLException e) {
            log.error(e.toString());
        } finally {
            DAOServant.closePrepareStatement(stmt);
            DAOServant.closeConnection(connection);
        }
        return students;
    }

    private Student getStudent(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setId(rs.getInt("id"));
        student.setLastName(rs.getString("last_name"));
        student.setFirstName(rs.getString("first_name"));
        student.setPatronymic(rs.getString("patronymic"));
        student.setClassId(rs.getInt("class_id"));
        return student;
    }
}
