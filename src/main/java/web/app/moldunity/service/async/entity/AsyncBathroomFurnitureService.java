package web.app.moldunity.service.async.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import web.app.moldunity.entity.furniture.bathroom.BathroomFurniture;
import web.app.moldunity.service.entity.furniture.BathroomArticleService;

import java.util.concurrent.CompletableFuture;

@Service
public class AsyncBathroomFurnitureService {
    private final BathroomArticleService bathroomService;

    @Autowired
    public AsyncBathroomFurnitureService(BathroomArticleService bathroomService) {
        this.bathroomService = bathroomService;
    }

    @Async
    public CompletableFuture<BathroomFurniture> asyncGetById(Long id){
        return CompletableFuture.completedFuture(bathroomService.getById(id).orElseGet(BathroomFurniture::new));
    }

    @Async
    public CompletableFuture<Long> asyncAdd(BathroomFurniture bathroomFurniture){
        return CompletableFuture.completedFuture(bathroomService.add(bathroomFurniture));
    }
}





















