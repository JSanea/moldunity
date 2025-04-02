package web.app.moldunity.config.mongo;

import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import web.app.moldunity.util.ThreadPoolAndHikariPoolSize;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableMongoRepositories
public class MongoConfig {
    @Bean
    public MongoClient mongoClient() {
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyToClusterSettings(builder -> builder.hosts(
                        List.of(new ServerAddress("localhost", 27017))
                ))
                .applyToConnectionPoolSettings(builder -> builder
                        .maxSize(ThreadPoolAndHikariPoolSize.getMaxPoolSize())
                        .minSize(ThreadPoolAndHikariPoolSize.getPoolSize())
                        .maxWaitTime(1000, TimeUnit.MILLISECONDS)
                )
                .applyToSocketSettings(builder -> builder
                        .connectTimeout(3000, TimeUnit.MILLISECONDS)
                        .readTimeout(3000, TimeUnit.MILLISECONDS)
                )
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public SimpleMongoClientDatabaseFactory mongoDbFactory(MongoClient mongoClient) {
        return new SimpleMongoClientDatabaseFactory(mongoClient, "moldunity");
    }

    @Bean
    public MongoTemplate mongoTemplate(SimpleMongoClientDatabaseFactory mongoDbFactory) {
        return new MongoTemplate(mongoDbFactory);
    }
}
