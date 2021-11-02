package by.itacademy.javaenterprise.lepnikau.app.dao.implement.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAOUtil {

    private static final Logger log = LoggerFactory.getLogger(DAOUtil.class);

    public static void closePrepareStatement(PreparedStatement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                log.error(e.toString());
            }
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error(e.toString());
            }
        }
    }

    public static void rollbackConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            log.error(e.toString());
        }
    }
}
