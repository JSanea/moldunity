package web.app.moldunity.service.async.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import web.app.moldunity.entity.furniture.kitchen.KitchenArticle;
import web.app.moldunity.service.entity.furniture.KitchenService;

import java.util.concurrent.CompletableFuture;

@Service
public class AsyncKitchenService {
    private final KitchenService kitchenService;

    @Autowired
    public AsyncKitchenService(KitchenService kitchenService) {
        this.kitchenService = kitchenService;
    }

    @Async
    public CompletableFuture<KitchenArticle> asyncGetById(Long id){
        return CompletableFuture.completedFuture(kitchenService.findById(id).orElseGet(KitchenArticle::new));
    }

    @Async
    public CompletableFuture<Long> asyncAddKitchenArticle(KitchenArticle kitchenArticle){
        return CompletableFuture.completedFuture(kitchenService.save(kitchenArticle).getId());
    }
}
