package web.app.moldunity.service.async.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import web.app.moldunity.service.entity.mongo.MongoTemplateService;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class AsyncMongoTemplateService {
    private final MongoTemplateService mongoTemplateService;

    @Autowired
    public AsyncMongoTemplateService(MongoTemplateService mongoTemplateService) {
        this.mongoTemplateService = mongoTemplateService;
    }

    @Async
    public <T> CompletableFuture<Optional<T>> findById(String id, Class<T> entity){
        return CompletableFuture.completedFuture(mongoTemplateService.findById(id, entity));
    }

    @Async
    public <T> CompletableFuture<T> asyncAdd(T t){
        return CompletableFuture.completedFuture(mongoTemplateService.add(t));
    }
}
