package web.app.moldunity.controller.entity;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import web.app.moldunity.entity.furniture.kitchen.KitchenArticle;
import web.app.moldunity.service.async.entity.AsyncKitchenService;
import web.app.moldunity.util.CompletableFutureUtil;


@RestController
@Slf4j
public class KitchenController {
    private final AsyncKitchenService asyncKitchenService;

    @Autowired
    public KitchenController(AsyncKitchenService asyncKitchenService) {
        this.asyncKitchenService = asyncKitchenService;
    }

    @GetMapping(value = "/mobila/bucatarie/{id}")
    public ResponseEntity<KitchenArticle> getById(@PathVariable Long id){
        return CompletableFutureUtil.exceptionWrapper(asyncKitchenService.asyncGetById(id));
    }

    @PostMapping(value = "/mobila/bucatarie/",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> addKitchenArticle(KitchenArticle kitchenArticle){
        return CompletableFutureUtil.exceptionWrapper(asyncKitchenService.asyncAddKitchenArticle(kitchenArticle));
    }
}
