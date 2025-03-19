package web.app.moldunity.controller.furniture;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import web.app.moldunity.entity.furniture.bathroom.BathroomFurniture;
import web.app.moldunity.entity.furniture.kitchen.KitchenFurniture;
import web.app.moldunity.entity.user.User;
import web.app.moldunity.service.async.entity.AsyncEntityService;
import web.app.moldunity.util.CompletableFutureUtil;
import web.app.moldunity.util.UserUtil;

import java.util.concurrent.ExecutionException;


@RestController
@Slf4j
public class KitchenFurnitureController {
    private final AsyncEntityService<KitchenFurniture> asyncEntityService;

    @Autowired
    public KitchenFurnitureController(AsyncEntityService<KitchenFurniture> asyncEntityService) {
        this.asyncEntityService = asyncEntityService;
    }

    @GetMapping(value = "/furniture/kitchen/{id}")
    public ResponseEntity<KitchenFurniture> getById(@PathVariable Long id){
        return CompletableFutureUtil.exceptionWrapper(asyncEntityService.asyncGetById(id, KitchenFurniture.class));
    }

    @PostMapping(value = "/furniture/kitchen/",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> add(KitchenFurniture kitchenFurniture){
        return new ResponseEntity<>(0L, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


















