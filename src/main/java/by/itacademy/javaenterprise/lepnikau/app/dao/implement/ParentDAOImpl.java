package by.itacademy.javaenterprise.lepnikau.app.dao.implement;

import by.itacademy.javaenterprise.lepnikau.app.dao.ParentDAO;
import by.itacademy.javaenterprise.lepnikau.app.dao.util.DAOServant;
import by.itacademy.javaenterprise.lepnikau.app.entity.Parent;
import by.itacademy.javaenterprise.lepnikau.app.connection.DSCreator;
import by.itacademy.javaenterprise.lepnikau.app.sql.ParentSQLRequests;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParentDAOImpl implements ParentDAO {

    private static final Logger log = LoggerFactory.getLogger(ParentDAOImpl.class.getSimpleName());

    @Override
    public Parent save(Parent parent) {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = DSCreator.getDataSource().getConnection();
            stmt = connection.prepareStatement(ParentSQLRequests.INSERT);

            stmt.setInt(1, parent.getStudentId());
            stmt.setString(2, parent.getLastName());
            stmt.setString(3, parent.getFirstName());
            stmt.setString(4, parent.getPatronymic());
            if (stmt.executeUpdate() > 0) {
                return parent;
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
    public Parent get(int id) {
        Connection connection = null;
        PreparedStatement stmt = null;
        Parent parent;

        try {
            connection = DSCreator.getDataSource().getConnection();
            stmt = connection.prepareStatement(ParentSQLRequests.SELECT_BY_ID);

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                parent = new Parent();
                parent.setId(rs.getInt("id"));
                parent.setStudentId(rs.getInt("student_id"));
                parent.setLastName(rs.getString("last_name"));
                parent.setFirstName(rs.getString("first_name"));
                parent.setPatronymic(rs.getString("patronymic"));
                return parent;
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
    public List<Parent> getAllPageByPage(int limit, int offset) {
        List<Parent> parents = new ArrayList<>();
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = DSCreator.getDataSource().getConnection();
            stmt = connection.prepareStatement(ParentSQLRequests.SELECT_ALL);

            ResultSet rs = stmt.executeQuery();
            stmt.setInt(1, limit);
            stmt.setInt(2, offset);
            while (rs.next()) {
                Parent parent = new Parent();
                parent.setId(rs.getInt("id"));
                parent.setStudentId(rs.getInt("student_id"));
                parent.setLastName(rs.getString("last_name"));
                parent.setFirstName(rs.getString("first_name"));
                parent.setPatronymic(rs.getString("patronymic"));
                parents.add(parent);
            }
        } catch (SQLException e) {
            log.error(e.toString());
        } finally {
            DAOServant.closePrepareStatement(stmt);
            DAOServant.closeConnection(connection);
        }
        return parents;
    }
}
