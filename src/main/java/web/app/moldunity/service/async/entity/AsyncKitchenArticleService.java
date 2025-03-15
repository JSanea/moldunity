package web.app.moldunity.service.async.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import web.app.moldunity.entity.furniture.kitchen.KitchenFurniture;
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
    public CompletableFuture<KitchenFurniture> asyncGetById(Long id){
        return CompletableFuture.completedFuture(kitchenService.findById(id).orElseGet(KitchenFurniture::new));
    }

    @Async
    public CompletableFuture<Long> asyncAdd(KitchenFurniture kitchenArticle){
        return CompletableFuture.completedFuture(kitchenService.add(kitchenArticle).getId());
    }
}
