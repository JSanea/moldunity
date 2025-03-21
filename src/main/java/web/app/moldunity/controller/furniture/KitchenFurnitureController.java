package web.app.moldunity.controller.furniture;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.app.moldunity.entity.furniture.Furniture;
import web.app.moldunity.entity.furniture.kitchen.KitchenFurniture;
import web.app.moldunity.service.async.AsyncUserService;
import web.app.moldunity.service.async.entity.AsyncEntityService;
import web.app.moldunity.service.entity.furniture.FurnitureService;
import web.app.moldunity.util.CompletableFutureUtil;

import java.util.List;


@RestController
@Slf4j
public class KitchenFurnitureController {
    private final AsyncEntityService asyncEntityService;
    private final AsyncUserService asyncUserService;

    private final FurnitureService furnitureService;

    @Autowired
    public KitchenFurnitureController(AsyncEntityService asyncEntityService, AsyncUserService asyncUserService, FurnitureService furnitureService) {
        this.asyncEntityService = asyncEntityService;
        this.asyncUserService = asyncUserService;
        this.furnitureService = furnitureService;
    }

    @PostMapping(value = "/kitchen",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> add(@RequestBody Furniture furniture){
        KitchenFurniture k = furniture.getKitchenFurnitures().get(0);
        k.setFurniture(furniture);
        k.setDateTimeFields();
        furniture.setKitchenFurnitures(List.of(k));

        return furnitureService.add(furniture);
    }
}


















