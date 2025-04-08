package web.app.moldunity.controller.entity.mysql.furniture;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import web.app.moldunity.entity.mysql.furniture.Furniture;
import web.app.moldunity.entity.mysql.furniture.kitchen.KitchenFurniture;
import web.app.moldunity.service.async.AsyncUserService;
import web.app.moldunity.service.async.entity.AsyncEntityService;
import web.app.moldunity.service.entity.furniture.FurnitureService;


@RestController
@Slf4j
public class KitchenFurnitureController {
    private final AsyncEntityService<Long> asyncEntityService;
    private final AsyncUserService asyncUserService;
    private final FurnitureService furnitureService;

    @Autowired
    public KitchenFurnitureController(AsyncEntityService<Long> asyncEntityService, AsyncUserService asyncUserService, FurnitureService furnitureService) {
        this.asyncEntityService = asyncEntityService;
        this.asyncUserService = asyncUserService;
        this.furnitureService = furnitureService;
    }

    @PostMapping(value = "/kitchen",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> add(@RequestBody Furniture furniture){
        KitchenFurniture k = furniture.getKitchenFurniture();
        k.setFurniture(furniture);
        furniture.setKitchenFurniture(k);

        return furnitureService.add(furniture);
    }
}


















