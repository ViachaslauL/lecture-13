package by.itacademy.javaenterprise.lepnikau.app.dao.implement;

import by.itacademy.javaenterprise.lepnikau.app.dao.MarkDAO;
import by.itacademy.javaenterprise.lepnikau.app.dao.implement.util.DAOUtil;
import by.itacademy.javaenterprise.lepnikau.app.entity.Mark;
import by.itacademy.javaenterprise.lepnikau.app.sql.MarkSQLRequests;
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
public class MarkDAOImpl implements MarkDAO {

    private static final Logger log = LoggerFactory.getLogger(MarkDAOImpl.class);

    private DataSource dataSource;

    @Override
    public Mark save(Mark mark) {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(MarkSQLRequests.INSERT);

            stmt.setInt(1, mark.getStudentId());
            stmt.setInt(2, mark.getMark());
            stmt.setInt(3, mark.getSubjectId());
            stmt.setDate(4, mark.getDate());
            if (stmt.executeUpdate() > 0) {
                connection.commit();
                return mark;
            }
        } catch (SQLException e) {
            log.error(e.toString());
            DAOUtil.rollbackConnection(connection);
        } finally {
            DAOUtil.closePrepareStatement(stmt);
            DAOUtil.closeConnection(connection);
        }
        return null;
    }

    @Override
    public Mark get(int id) {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = dataSource.getConnection();
            stmt = connection.prepareStatement(MarkSQLRequests.SELECT_BY_ID);

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return createMarkEntity(rs);
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
    public List<Mark> getAllPageByPage(int pageSize, int pageNumber) {
        int offset = pageSize * (pageNumber - 1);
        List<Mark> marks = new ArrayList<>();
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = dataSource.getConnection();
            stmt = connection.prepareStatement(MarkSQLRequests.SELECT_ALL);

            stmt.setInt(1, pageSize);
            stmt.setInt(2, offset);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                marks.add(createMarkEntity(rs));
            }
        } catch (SQLException e) {
            log.error(e.toString());
        } finally {
            DAOUtil.closePrepareStatement(stmt);
            DAOUtil.closeConnection(connection);
        }
        return marks;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Mark createMarkEntity(ResultSet rs) throws SQLException {
        Mark mark = new Mark();
        mark.setId(rs.getInt("id"));
        mark.setStudentId(rs.getInt("student_id"));
        mark.setMark(rs.getInt("mark"));
        mark.setSubjectId(rs.getInt("subject_id"));
        mark.setDate(rs.getDate("date"));
        return mark;
    }
}
