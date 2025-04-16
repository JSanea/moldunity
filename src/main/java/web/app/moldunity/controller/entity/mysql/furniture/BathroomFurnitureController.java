package web.app.moldunity.controller.entity.mysql.furniture;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.app.moldunity.entity.mysql.furniture.Furniture;
import web.app.moldunity.entity.mysql.furniture.bathroom.BathroomFurniture;
import web.app.moldunity.service.async.entity.AsyncEntityService;
import web.app.moldunity.service.entity.furniture.FurnitureService;
import web.app.moldunity.util.CompletableFutureUtil;

import java.util.List;

@RestController
@Slf4j
public class BathroomFurnitureController {
    private final AsyncEntityService<Long> asyncEntityService;
    private final FurnitureService furnitureService;

    @Autowired
    public BathroomFurnitureController(AsyncEntityService<Long> asyncEntityService, FurnitureService furnitureService) {
        this.asyncEntityService = asyncEntityService;
        this.furnitureService = furnitureService;
    }

    @GetMapping(value = "/furniture/bathroom")
    public ResponseEntity<List<Furniture>> getPageSortedByTimeDesc(@RequestParam Integer page)  {
        return CompletableFutureUtil.exceptionWrapper(asyncEntityService.asyncGetPageSortedByRepublishedAtDesc(
                page,
                Furniture.class,
                "Baie")
        );
    }

    @GetMapping(value = "/furniture/bathroom/count")
    public ResponseEntity<Long> getNumRecords(){
        return CompletableFutureUtil.exceptionWrapper(asyncEntityService.asyncGetNumRecords(BathroomFurniture.class));
    }

    @PostMapping(value = "/bathroom",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> add(@RequestBody Furniture furniture, @Value("${articles.limit}") Long limit) {
        return null;
    }

    @PutMapping(value = "/bathroom",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Furniture furniture){
    }
}
















