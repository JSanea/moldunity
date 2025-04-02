package web.app.moldunity.service.entity.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class MongoTemplateService {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public MongoTemplateService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public<T> T add(T t){
        return (T) mongoTemplate.insert(t);
    }
}
