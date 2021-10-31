package by.itacademy.javaenterprise.lepnikau.app.dao.implement;

import by.itacademy.javaenterprise.lepnikau.app.dao.MarkDAO;
import by.itacademy.javaenterprise.lepnikau.app.dao.util.DAOServant;
import by.itacademy.javaenterprise.lepnikau.app.entity.Mark;
import by.itacademy.javaenterprise.lepnikau.app.connection.DSCreator;
import by.itacademy.javaenterprise.lepnikau.app.sql.MarkSQLRequests;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MarkDAOImpl implements MarkDAO {

    private static final Logger log = LoggerFactory.getLogger(MarkDAOImpl.class.getSimpleName());

    @Override
    public Mark save(Mark mark) {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = DSCreator.getDataSource().getConnection();
            stmt = connection.prepareStatement(MarkSQLRequests.INSERT);

            stmt.setInt(1, mark.getStudentId());
            stmt.setInt(2, mark.getMark());
            stmt.setInt(3, mark.getSubjectId());
            stmt.setDate(4, mark.getDate());
            if (stmt.executeUpdate() > 0) {
                return mark;
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
    public Mark get(int id) {
        Connection connection = null;
        PreparedStatement stmt = null;
        Mark mark;

        try {
            connection = DSCreator.getDataSource().getConnection();
            stmt = connection.prepareStatement(MarkSQLRequests.SELECT_BY_ID);

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                mark = new Mark();
                mark.setId(rs.getInt("id"));
                mark.setStudentId(rs.getInt("student_id"));
                mark.setMark(rs.getInt("mark"));
                mark.setSubjectId(rs.getInt("subject_id"));
                mark.setDate(rs.getDate("date"));
                return mark;
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
    public List<Mark> getAllPageByPage(int limit, int offset) {
        List<Mark> marks = new ArrayList<>();
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = DSCreator.getDataSource().getConnection();
            stmt = connection.prepareStatement(MarkSQLRequests.SELECT_ALL);

            stmt.setInt(1, limit);
            stmt.setInt(2, offset);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Mark mark = new Mark();
                mark.setId(rs.getInt("id"));
                mark.setStudentId(rs.getInt("student_id"));
                mark.setMark(rs.getInt("mark"));
                mark.setSubjectId(rs.getInt("subject_id"));
                mark.setDate(rs.getDate("date"));
                marks.add(mark);
            }
        } catch (SQLException e) {
            log.error(e.toString());
        } finally {
            DAOServant.closePrepareStatement(stmt);
            DAOServant.closeConnection(connection);
        }
        return marks;
    }
}
