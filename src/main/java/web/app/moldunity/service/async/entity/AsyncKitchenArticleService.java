package web.app.moldunity.service.async.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import web.app.moldunity.entity.furniture.kitchen.KitchenArticle;
import web.app.moldunity.service.entity.furniture.KitchenArticleService;

import java.util.concurrent.CompletableFuture;

@Service
public class AsyncKitchenArticleService {
    private final KitchenArticleService kitchenService;

    @Autowired
    public AsyncKitchenArticleService(KitchenArticleService kitchenService) {
        this.kitchenService = kitchenService;
    }

    @Async
    public CompletableFuture<KitchenArticle> asyncGetById(Long id){
        return CompletableFuture.completedFuture(kitchenService.findById(id).orElseGet(KitchenArticle::new));
    }

    @Async
    public CompletableFuture<Long> asyncAdd(KitchenArticle kitchenArticle){
        return CompletableFuture.completedFuture(kitchenService.add(kitchenArticle).getId());
    }
}
