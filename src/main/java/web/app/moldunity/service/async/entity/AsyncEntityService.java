package web.app.moldunity.service.async.entity;

import org.springframework.scheduling.annotation.Async;;
import org.springframework.stereotype.Service;
import web.app.moldunity.service.entity.EntityService;


import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class AsyncEntityService<T> {
    private final EntityService entityService;

    public AsyncEntityService(EntityService entityService) {
        this.entityService = entityService;
    }

    @Async
    public CompletableFuture<T> asyncGetById(Long id, Class<T> entity){
        return CompletableFuture.completedFuture(entityService.getById(id, entity));
    }

    @Async
    public CompletableFuture<List<T>> asyncGetAll(Class<T> entity){
        return CompletableFuture.completedFuture(entityService.getAll(entity));
    }

    @Async
    public CompletableFuture<T> asyncAdd(T t, Class<T> entity){
        return CompletableFuture.completedFuture(entityService.add(t, entity));
    }

    @Async
    public CompletableFuture<List<T>> asyncGetPageSortedByRepublishedAtDesc(Integer page, Class<T> entity){
        return CompletableFuture.completedFuture(entityService.getPageSortedByRepublishedAtDesc(50, 50 * page, entity));
    }

    @Async
    public CompletableFuture<Long> asyncGetNumRecords(Class<T> entity){
        return CompletableFuture.completedFuture(entityService.getNumRecords(entity));
    }


}








