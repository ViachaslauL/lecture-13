package by.itacademy.javaenterprise.lepnikau.app.connection;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class DSCreator {

    private static BasicDataSource basicDataSource;

    private DSCreator() {
    }

    public static DataSource getDataSource() {
        return basicDataSource;
    }

    public void setBasicDataSource(BasicDataSource basicDataSource) {
        DSCreator.basicDataSource = basicDataSource;
    }
}
