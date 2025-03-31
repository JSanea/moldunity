package web.app.moldunity.controller.furniture;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.app.moldunity.entity.furniture.Furniture;
import web.app.moldunity.entity.furniture.FurnitureImage;
import web.app.moldunity.service.async.entity.AsyncEntityService;
import web.app.moldunity.util.CompletableFutureUtil;
import web.app.moldunity.util.SecurityUtil;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@Slf4j
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

    @GetMapping(value = "/favorite/furniture/")
    public ResponseEntity<List<Furniture>> getFavorite(){
        return CompletableFutureUtil.exceptionWrapper(asyncEntityService.asyncGetFavorite(SecurityUtil.getUsername(), Furniture.class, "favoriteFurnitures"));
    }

    @PostMapping(value = "/images/furniture/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void addImages(@PathVariable Long id,
                          @RequestBody Map<Integer, FurnitureImage> images) {
    }

    @DeleteMapping(value = "/d/furniture/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        try {
            Furniture f = asyncEntityService.asyncGetById(id, Furniture.class).get();
            if(null == f || !(f.getUsername().equals(SecurityUtil.getUsername()) || "ROLE_ADMIN".equals(SecurityUtil.getRole())))
                return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return CompletableFutureUtil.exceptionWrapper(asyncEntityService.asyncRemoveById(id, Furniture.class));
    }
}

















