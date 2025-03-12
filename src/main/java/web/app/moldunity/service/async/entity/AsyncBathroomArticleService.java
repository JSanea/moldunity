package web.app.moldunity.service.async.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import web.app.moldunity.entity.furniture.bathroom.BathroomArticle;
import web.app.moldunity.service.entity.furniture.BathroomArticleService;

import java.util.concurrent.CompletableFuture;

@Service
public class AsyncBathroomArticleService {
    private final BathroomArticleService bathroomService;

    @Autowired
    public AsyncBathroomArticleService(BathroomArticleService bathroomService) {
        this.bathroomService = bathroomService;
    }

    @Async
    public CompletableFuture<BathroomArticle> asyncGetById(Long id){
        return CompletableFuture.completedFuture(bathroomService.findById(id).orElseGet(BathroomArticle::new));
    }
}





















