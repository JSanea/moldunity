package web.app.moldunity.controller.furniture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import web.app.moldunity.entity.furniture.Furniture;
import web.app.moldunity.service.async.entity.AsyncEntityService;
import web.app.moldunity.util.CompletableFutureUtil;

import java.util.List;

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
}
















