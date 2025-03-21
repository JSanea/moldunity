package web.app.moldunity.service.async.entity;

import org.springframework.scheduling.annotation.Async;;
import org.springframework.stereotype.Service;
import web.app.moldunity.service.entity.EntityService;


import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class AsyncEntityService {
    private final Integer ELEMENTS = 50;
    private final EntityService entityService;

    public AsyncEntityService(EntityService entityService) {
        this.entityService = entityService;
    }

    @Async
    public <T> CompletableFuture<T> asyncGetById(Long id, Class<T> entity) {
        return CompletableFuture.completedFuture(entityService.getById(id, entity));
    }

    @Async
    public <T> CompletableFuture<List<T>> asyncGetAll(Class<T> entity) {
        return CompletableFuture.completedFuture(entityService.getAll(entity));
    }

    @Async
    public <T> CompletableFuture<T> asyncAdd(T t, Class<T> entity) {
        return CompletableFuture.completedFuture(entityService.add(t, entity));
    }

    @Async
    public <T> CompletableFuture<List<T>> asyncGetPageSortedByRepublishedAtDesc(Integer page, Class<T> entity) {
        if (page < 1) page = 1;
        return CompletableFuture.completedFuture(entityService.getPageSortedByRepublishedAtDesc(ELEMENTS, ELEMENTS * (page - 1), entity));
    }

    @Async
    public <T> CompletableFuture<Long> asyncGetNumRecords(Class<T> entity) {
        return CompletableFuture.completedFuture(entityService.getNumRecords(entity));
    }

    @Async
    public <T> void removeById(Long id, Class<T> entity) {
        entityService.removeById(id, entity);
    }
}







