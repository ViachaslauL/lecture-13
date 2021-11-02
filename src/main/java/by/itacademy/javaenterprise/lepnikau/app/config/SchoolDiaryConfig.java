package by.itacademy.javaenterprise.lepnikau.app.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("by.itacademy.javaenterprise.lepnikau.app")
@PropertySource("classpath:db.properties")
public class SchoolDiaryConfig {

    @Bean
    public DataSource getDataSource(@Value("${DRIVER_CLASS}") String driver,
                                    @Value("${DB_URL}") String url,
                                    @Value("${DB_USER}") String user,
                                    @Value("${DB_PASSWORD}") String password,
                                    @Value("${INITIAL_POOL_SIZE}") int initPoolSize,
                                    @Value("${MAX_POOL_SIZE}") int maxPoolSize) {

        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setInitialSize(initPoolSize);
        dataSource.setMaxTotal(maxPoolSize);

        return dataSource;
    }
}
