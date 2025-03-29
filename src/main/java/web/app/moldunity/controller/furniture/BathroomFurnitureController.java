package web.app.moldunity.controller.furniture;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.app.moldunity.entity.furniture.Furniture;
import web.app.moldunity.entity.furniture.bathroom.BathroomFurniture;
import web.app.moldunity.service.async.entity.AsyncEntityService;
import web.app.moldunity.service.entity.furniture.FurnitureService;
import web.app.moldunity.util.CompletableFutureUtil;
import web.app.moldunity.util.SecurityUtil;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@Slf4j
public class BathroomFurnitureController {
    private final AsyncEntityService asyncEntityService;
    private final FurnitureService furnitureService;

    @Autowired
    public BathroomFurnitureController(AsyncEntityService asyncEntityService, FurnitureService furnitureService) {
        this.asyncEntityService = asyncEntityService;
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
        try {
            if(asyncEntityService.asyncGetCountOfUserArticles(SecurityUtil.getUsername(), Furniture.class).get() >= 5)
                return new ResponseEntity<>(0L, HttpStatus.BAD_REQUEST);
        } catch (InterruptedException | ExecutionException e) {
           log.error(e.getMessage());
           return new ResponseEntity<>(0L, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        BathroomFurniture b = furniture.getBathroomFurnitures().get(0);
        b.setFurniture(furniture);
        furniture.setBathroomFurnitures(List.of(b));

        return furnitureService.add(furniture);
    }

    @PutMapping(value = "/bathroom",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Furniture furniture){
    }
}
















