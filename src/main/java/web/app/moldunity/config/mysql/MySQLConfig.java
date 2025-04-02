package web.app.moldunity.config.mysql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static web.app.moldunity.util.ThreadPoolAndHikariPoolSize.getMaxPoolSize;
import static web.app.moldunity.util.ThreadPoolAndHikariPoolSize.getPoolSize;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "web.app.moldunity.repository.mysql",
        entityManagerFactoryRef = "mysqlEntityManagerFactory",
        transactionManagerRef = "mysqlTransactionManager"
)
@EntityScan(basePackages = "web.app.moldunity.entity.mysql")
public class MySQLConfig {
    @Value("${spring.datasource.username}")
    private String USERNAME;
    @Value("${spring.datasource.password}")
    private String PASSWORD;

    @Primary
    @Bean
    public HikariDataSource mysqlDataSource(){
        HikariConfig config = new HikariConfig();

        config.setUsername(USERNAME);
        config.setPassword(PASSWORD);
        config.setJdbcUrl("jdbc:mysql://localhost:3306/moldunity_test");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setTransactionIsolation("TRANSACTION_REPEATABLE_READ");
        config.setMinimumIdle(getPoolSize());
        config.setMaximumPoolSize(getMaxPoolSize());
        config.setConnectionTimeout(30_000L);
        config.setIdleTimeout(300_000L);
        config.setMaxLifetime(1_200_000L);

        return new HikariDataSource(config);
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(mysqlDataSource())
                .packages("web.app.moldunity.entity.mysql")
                .persistenceUnit("mysql")
                .build();
    }

    @Primary
    @Bean
    public JpaTransactionManager mysqlTransactionManager(EntityManagerFactory mysqlEntityManagerFactory) {
        return new JpaTransactionManager(mysqlEntityManagerFactory);
    }
}






















