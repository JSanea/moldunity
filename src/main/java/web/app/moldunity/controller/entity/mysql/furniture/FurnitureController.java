package web.app.moldunity.controller.entity.mysql.furniture;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.app.moldunity.entity.mysql.furniture.Furniture;
import web.app.moldunity.entity.mysql.furniture.FurnitureImage;
import web.app.moldunity.security.SecurityContextHelper;
import web.app.moldunity.service.async.entity.AsyncEntityService;
import web.app.moldunity.service.entity.furniture.FurnitureService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@Slf4j
public class FurnitureController {
    private final AsyncEntityService<Long> asyncEntityService;
    private final FurnitureService furnitureService;

    @Autowired
    public FurnitureController(AsyncEntityService<Long> asyncEntityService, FurnitureService furnitureService) {
        this.asyncEntityService = asyncEntityService;
        this.furnitureService = furnitureService;
    }

    @GetMapping(value = "/furniture/{id}")
    public CompletableFuture<ResponseEntity<Furniture>> getById(@PathVariable Long id){
        return asyncEntityService.asyncGetById(id, Furniture.class)
                .thenApply(f -> f.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()))
                .exceptionally(ex -> {
                    log.error(ex.getMessage());
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Furniture());
                });
    }

    @GetMapping(value = "/furniture/count")
    public CompletableFuture<ResponseEntity<Long>> getNumRecords(){
        return asyncEntityService.asyncGetNumRecords(Furniture.class)
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> {
                    log.error(ex.getMessage());
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0L);
                });
    }

    @GetMapping(value = "/favorite/furniture/")
    public CompletableFuture<ResponseEntity<List<Furniture>>> getFavorite(){
        return asyncEntityService.asyncGetFavorite(SecurityContextHelper.getUsername(), Furniture.class, "favoriteFurnitures")
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> {
                    log.error(ex.getMessage());
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
                });
    }

    @PostMapping(value = "/add/furniture",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public CompletableFuture<ResponseEntity<Furniture>> add(@RequestBody Furniture furniture) {
        return furnitureService.add(furniture)
                .thenApply(f -> null == f ? ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Furniture()):
                        ResponseEntity.status(HttpStatus.CREATED).body(f))
                .exceptionally(ex -> {
                    log.error(ex.getMessage());
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Furniture());
                });
    }

    @PostMapping(value = "/images/furniture/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void addImages(@PathVariable Long id,
                          @RequestBody Map<Integer, FurnitureImage> images) {
    }

    @DeleteMapping(value = "/d/furniture/{id}")
    public CompletableFuture<ResponseEntity<Boolean>> delete(@PathVariable Long id){
        return asyncEntityService.asyncRemoveByIdAndUsername(id, SecurityContextHelper.getUsername(), Furniture.class)
                .thenApply(r -> r ? ResponseEntity.status(HttpStatus.OK).body(true) :
                                    ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false))
                .exceptionally(ex -> {
                    log.error(ex.getMessage());
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
                });
    }
}

















