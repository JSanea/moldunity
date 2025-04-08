package web.app.moldunity.controller.entity.mysql.furniture;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.app.moldunity.entity.mysql.furniture.Furniture;
import web.app.moldunity.entity.mysql.furniture.bathroom.BathroomFurniture;
import web.app.moldunity.service.async.entity.AsyncEntityService;
import web.app.moldunity.service.entity.furniture.FurnitureService;
import web.app.moldunity.util.CompletableFutureUtil;
import web.app.moldunity.security.SecurityContextHelper;

import java.util.List;
import java.util.concurrent.ExecutionException;

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
        return CompletableFutureUtil.exceptionWrapper(asyncEntityService.asyncGetPageSortedByRepublishedAtDesc(page, Furniture.class, "Baie"));
    }

    @GetMapping(value = "/furniture/bathroom/count")
    public ResponseEntity<Long> getNumRecords(){
        return CompletableFutureUtil.exceptionWrapper(asyncEntityService.asyncGetNumRecords(BathroomFurniture.class));
    }

    @PostMapping(value = "/bathroom",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> add(@RequestBody Furniture furniture, @Value("${articles.limit}") Long limit) {
        try {
            if(asyncEntityService.asyncGetCountOfUserArticles(SecurityContextHelper.getUsername(), Furniture.class).get() >= limit)
                return new ResponseEntity<>(-1L, HttpStatus.BAD_REQUEST);
        } catch (InterruptedException | ExecutionException e) {
           log.error(e.getMessage());
           return new ResponseEntity<>(0L, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        BathroomFurniture b = furniture.getBathroomFurniture();
        b.setFurniture(furniture);
        furniture.setBathroomFurniture(b);

        return furnitureService.add(furniture);
    }

    @PutMapping(value = "/bathroom",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Furniture furniture){
    }
}
















