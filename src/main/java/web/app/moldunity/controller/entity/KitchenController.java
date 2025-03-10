package web.app.moldunity.controller.entity;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import web.app.moldunity.entity.furniture.kitchen.KitchenArticle;
import web.app.moldunity.service.async.entity.AsyncKitchenService;

import java.util.concurrent.ExecutionException;

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
        try {
            return new ResponseEntity<>(asyncKitchenService.asyncGetById(id).get(), HttpStatus.OK);
        } catch (ExecutionException | InterruptedException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(new KitchenArticle(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
