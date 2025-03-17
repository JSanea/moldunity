package web.app.moldunity.controller.furniture;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import web.app.moldunity.entity.furniture.bathroom.BathroomFurniture;
import web.app.moldunity.service.async.entity.AsyncBathroomFurnitureService;
import web.app.moldunity.util.CompletableFutureUtil;

@RestController
@Slf4j
public class BathroomController {
    private final AsyncBathroomFurnitureService asyncBathroomService;

    @Autowired
    public BathroomController(AsyncBathroomFurnitureService asyncBathroomService) {
        this.asyncBathroomService = asyncBathroomService;
    }

    @GetMapping(value = "/furniture/bathroom/{id}")
    public ResponseEntity<BathroomFurniture> getById(@PathVariable Long id){
        return CompletableFutureUtil.exceptionWrapper(asyncBathroomService.asyncGetById(id));
    }

    @PostMapping(value = "/furniture/bathroom",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> add(@RequestBody BathroomFurniture bathroomFurniture){
        return CompletableFutureUtil.exceptionWrapper(asyncBathroomService.asyncAdd(bathroomFurniture));
    }
}
















