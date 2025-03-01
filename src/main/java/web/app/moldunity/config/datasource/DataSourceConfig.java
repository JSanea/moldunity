package web.app.moldunity.config.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static web.app.moldunity.util.ThreadPoolAndHikariPoolSize.*;

@Configuration
public class DataSourceConfig {
    @Value("${spring.datasource.username}")
    private String USERNAME;
    @Value("${spring.datasource.password}")
    private String PASSWORD;
    private final String JDBC_URL = "jdbc:mysql://localhost:3306/anonim_test";
    private final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private final String TRANSACTION_ISOLATION = "TRANSACTION_REPEATABLE_READ";
    private final Long CONNECTION_TIMEOUT = 30_000L;
    private final Long IDLE_TIMEOUT = 300_000L;
    private final Long MAX_LIFETIME = 1_200_000L;

    @Bean(value = "dataSource")
    public HikariDataSource getDataSource(){
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(JDBC_URL);
        config.setUsername(USERNAME);
        config.setPassword(PASSWORD);
        config.setDriverClassName(DRIVER_CLASS_NAME);
        config.setMinimumIdle(getPoolSize());
        config.setMaximumPoolSize(getMaxPoolSize());
        config.setTransactionIsolation(TRANSACTION_ISOLATION);
        config.setConnectionTimeout(CONNECTION_TIMEOUT);
        config.setIdleTimeout(IDLE_TIMEOUT);
        config.setMaxLifetime(MAX_LIFETIME);

        return new HikariDataSource(config);
    }
}






















