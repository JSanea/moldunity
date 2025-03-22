package web.app.moldunity.controller.furniture;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.app.moldunity.entity.furniture.Furniture;
import web.app.moldunity.entity.furniture.FurnitureImage;
import web.app.moldunity.entity.furniture.bathroom.BathroomFurniture;
import web.app.moldunity.entity.user.User;
import web.app.moldunity.service.async.AsyncUserService;
import web.app.moldunity.service.async.entity.AsyncEntityService;
import web.app.moldunity.service.entity.furniture.FurnitureService;
import web.app.moldunity.util.CompletableFutureUtil;
import web.app.moldunity.util.SecurityUtil;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@Slf4j
public class BathroomFurnitureController {
    private final AsyncEntityService asyncEntityService;
    private final AsyncUserService asyncUserService;
    private final FurnitureService furnitureService;

    @Autowired
    public BathroomFurnitureController(AsyncEntityService asyncEntityService, AsyncUserService asyncUserService, FurnitureService furnitureService) {
        this.asyncEntityService = asyncEntityService;
        this.asyncUserService = asyncUserService;
        this.furnitureService = furnitureService;
    }


    @GetMapping(value = "/furniture/bathroom")
    public ResponseEntity<List<Furniture>> getPageSortedByTimeDesc(@RequestParam Integer page)  {
        return CompletableFutureUtil.exceptionWrapper(asyncEntityService.asyncGetPageSortedByRepublishedAtDesc(page, Furniture.class, "Baie"));
    }

    @GetMapping(value = "/furniture/bathroom/count")
    public ResponseEntity<Long> getNumRecords(){
        return CompletableFutureUtil.exceptionWrapper(asyncEntityService.asyncGetNumRecords(BathroomFurniture.class));
    }

    @PostMapping(value = "/bathroom",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> add(@RequestBody Furniture furniture) {
        BathroomFurniture b = furniture.getBathroomFurnitures().get(0);
        b.setFurniture(furniture);
        furniture.setBathroomFurnitures(List.of(b));

        return furnitureService.add(furniture);
    }

    @PostMapping(value = "/furniture/{id}/images",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void addImages(@PathVariable Long id,
                          @RequestBody Map<String, FurnitureImage> images) {

    }

    @PutMapping(value = "/bathroom",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Furniture furniture){

    }

    @DeleteMapping(value = "/bathroom/{id}")
    public ResponseEntity<Boolean> remove(@PathVariable Long id){
        return CompletableFutureUtil.exceptionWrapper(asyncEntityService.asyncRemoveById(id, Furniture.class));
    }
}
















