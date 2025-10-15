package web.app.moldunity.config.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

@Configuration
//@EnableReactiveMongoRepositories(basePackages = "web.app.moldunity.repository.mongo")
public class MongoConfig {
    @Bean
    public MongoClient mongoClient(){
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString("mongodb://localhost:27017"))
                .applyToConnectionPoolSettings(builder -> builder
                                .maxSize(100)
                                .minSize(4))
                .build();

        return MongoClients.create(settings);
    }

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(mongoClient(), "moldunity");
    }
}
