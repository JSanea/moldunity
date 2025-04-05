package web.app.moldunity.service.entity.mongo;

import com.mongodb.client.result.DeleteResult;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MongoTemplateService {
    @PersistenceContext
    private final MongoTemplate mongoTemplate;

    @Autowired
    public MongoTemplateService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public <T> Optional<T> findById(String id, Class<T> entity){
        return Optional.ofNullable(mongoTemplate.findById(id, entity));
    }

    public <T> List<T> findAll(Class<T> entity){
        return mongoTemplate.findAll(entity);
    }

    public <T> T saveOrUpdate(T t){
       return mongoTemplate.save(t);
    }

    public <T> T add(T t){
        return (T) mongoTemplate.insert(t);
    }

    public <T> DeleteResult remove(T t, String collectionName){
        return mongoTemplate.remove(t, collectionName);
    }

    public <T> Optional<DeleteResult> removeById(String id, String collectionName, Class<T> entity){
        Optional<T> t = findById(id, entity);
        if(t.isPresent())
            return Optional.ofNullable(remove(t, collectionName));
        return Optional.empty();
    }
}
