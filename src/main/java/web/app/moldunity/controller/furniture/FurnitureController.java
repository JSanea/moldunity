package web.app.moldunity.controller.furniture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.app.moldunity.entity.furniture.Furniture;
import web.app.moldunity.entity.furniture.FurnitureImage;
import web.app.moldunity.service.async.entity.AsyncEntityService;
import web.app.moldunity.util.CompletableFutureUtil;

import java.util.List;
import java.util.Map;

@RestController
public class FurnitureController {
    private final AsyncEntityService asyncEntityService;

    @Autowired
    public FurnitureController(AsyncEntityService asyncEntityService) {
        this.asyncEntityService = asyncEntityService;
    }


    @GetMapping(value = "/furniture/{id}")
    public ResponseEntity<Furniture> getById(@PathVariable Long id){
        return CompletableFutureUtil.exceptionWrapper(asyncEntityService.asyncGetById(id, Furniture.class));
    }

    @GetMapping(value = "/furniture/count")
    public ResponseEntity<Long> getNumRecords(){
        return CompletableFutureUtil.exceptionWrapper(asyncEntityService.asyncGetNumRecords(Furniture.class));
    }

    @GetMapping(value = "/furniture/favorite/user/{id}")
    public ResponseEntity<List<Furniture>> getFavorite(@PathVariable Long id){
       return CompletableFutureUtil.exceptionWrapper(asyncEntityService.getFavorite(id, Furniture.class, "favoriteFurnitures"));
    }

    @PostMapping(value = "/furniture/{id}/images",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void addImages(@PathVariable Long id,
                          @RequestBody Map<Integer, FurnitureImage> images) {
    }

    @DeleteMapping(value = "/furniture/{id}")
    public ResponseEntity<Boolean> remove(@PathVariable Long id){
        return CompletableFutureUtil.exceptionWrapper(asyncEntityService.asyncRemoveById(id, Furniture.class));
    }
}
















