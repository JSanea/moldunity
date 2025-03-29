package web.app.moldunity.service.async.entity;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import web.app.moldunity.service.entity.EntityService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

;

@Service
public class AsyncEntityService {
    private final EntityService entityService;

    public AsyncEntityService(EntityService entityService) {
        this.entityService = entityService;
    }

    @Async
    public <T> CompletableFuture<T> asyncGetById(Long id, Class<T> entity) {
        return CompletableFuture.completedFuture(entityService.getById(id, entity));
    }

    @Async
    public <T> CompletableFuture<T>  asyncGetByEId(String eId, Class<T> entity){
        return CompletableFuture.completedFuture(entityService.getByEId(eId, entity));
    }

    @Async
    public <T> CompletableFuture<List<T>> asyncGetAll(Class<T> entity) {
        return CompletableFuture.completedFuture(entityService.getAll(entity));
    }

    @Async
    public <T> CompletableFuture<List<T>> asyncGetPageSortedByRepublishedAtDesc(Integer page, Class<T> entity, String subcategory) {
        if (page < 1) page = 1;
        return CompletableFuture.completedFuture(entityService.getPageSortedByRepublishedAtDesc(50, 50 * (page - 1), entity, subcategory));
    }

    @Async
    public <T> CompletableFuture<Long> asyncGetNumRecords(Class<T> entity) {
        return CompletableFuture.completedFuture(entityService.getNumRecords(entity));
    }

    @Async
    public <T> CompletableFuture<List<T>> asyncGetFavorite(String username, Class<T> entity, String favoriteField){
        return CompletableFuture.completedFuture(entityService.getFavorite(username, entity, favoriteField));
    }

    @Async
    public <T> CompletableFuture<T> asyncAdd(T t, String eId, Class<T> entity) {
        return CompletableFuture.completedFuture(entityService.add(t, eId, entity));
    }

    @Async
    public <T> CompletableFuture<Boolean> asyncRemoveById(Long id, Class<T> entity) {
        return CompletableFuture.completedFuture(entityService.removeById(id, entity));
    }

    @Async
    public <T> void asyncRemove(T t, Class<T> entity){
        entityService.remove(t, entity);
    }
}







