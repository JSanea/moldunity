package web.app.moldunity.controller.entity.mysql.furniture;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.app.moldunity.entity.mysql.furniture.Furniture;
import web.app.moldunity.entity.mysql.furniture.FurnitureImage;
import web.app.moldunity.security.SecurityContextHelper;
import web.app.moldunity.service.async.entity.AsyncEntityService;
import web.app.moldunity.util.CompletableFutureUtil;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@Slf4j
public class FurnitureController {
    private final AsyncEntityService asyncEntityService;

    @Autowired
    public FurnitureController(AsyncEntityService asyncEntityService) {
        this.asyncEntityService = asyncEntityService;
    }

    @GetMapping(value = "/furniture/{id}")
    public ResponseEntity<Optional<Furniture>> getById(@PathVariable Long id){
        return CompletableFutureUtil.exceptionWrapper(asyncEntityService.asyncGetById(id, Furniture.class));
    }

    @GetMapping(value = "/furniture/count")
    public ResponseEntity<Long> getNumRecords(){
        return CompletableFutureUtil.exceptionWrapper(asyncEntityService.asyncGetNumRecords(Furniture.class));
    }

    @GetMapping(value = "/favorite/furniture/")
    public ResponseEntity<List<Furniture>> getFavorite(){
        return CompletableFutureUtil.exceptionWrapper(asyncEntityService.asyncGetFavorite(SecurityContextHelper.getUsername(), Furniture.class, "favoriteFurnitures"));
    }

    @PostMapping(value = "/images/furniture/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void addImages(@PathVariable Long id,
                          @RequestBody Map<Integer, FurnitureImage> images) {
    }

    @DeleteMapping(value = "/d/furniture/{id}")
    public void delete(@PathVariable Long id){
        asyncEntityService.removeByIdAndUsername(id, SecurityContextHelper.getUsername(), Furniture.class);
    }
}

















