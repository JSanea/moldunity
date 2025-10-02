package web.app.moldunity.config.postgres;

import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.reactive.TransactionalOperator;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.time.Duration;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@Configuration
@EnableTransactionManagement
//@EnableR2dbcRepositories(basePackages = "web.app.moldunity.repository.postgres")
public class PostgresConfig extends AbstractR2dbcConfiguration {
    @Value("${postgresql.host}")
    private String host;

    @Value("${postgresql.port}")
    private int port;

    @Value("${postgresql.name}")
    private String database;

    @Value("${postgresql.username}")
    private String username;

    @Value("${postgresql.password}")
    private String password;

    @Override
    @NonNull
    @Bean
    public ConnectionFactory connectionFactory() {
        ConnectionFactory connectionFactory = ConnectionFactories.get(
                ConnectionFactoryOptions.builder()
                        .option(DRIVER, "postgresql")
                        .option(HOST, host)
                        .option(PORT, port)
                        .option(DATABASE, database)
                        .option(USER, username)
                        .option(PASSWORD, password)
                        .build());

        ConnectionPoolConfiguration connectionPoolConfiguration = ConnectionPoolConfiguration.builder()
                .connectionFactory(connectionFactory)
                .initialSize(Runtime.getRuntime().availableProcessors())
                .maxSize(32)
                .minIdle(Runtime.getRuntime().availableProcessors())
                .maxIdleTime(Duration.ofSeconds(30))
                .maxLifeTime(Duration.ofSeconds(60))
                .build();

        return new ConnectionPool(connectionPoolConfiguration);
    }

    @Primary
    @Bean
    public TransactionalOperator transactionalOperator(ConnectionFactory connectionFactory) {
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
        return TransactionalOperator.create(new R2dbcTransactionManager(connectionFactory), definition);
    }
}










