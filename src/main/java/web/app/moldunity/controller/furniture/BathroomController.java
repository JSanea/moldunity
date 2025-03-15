package web.app.moldunity.controller.furniture;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import web.app.moldunity.entity.furniture.bathroom.BathroomFurniture;
import web.app.moldunity.service.async.entity.AsyncBathroomArticleService;
import web.app.moldunity.util.CompletableFutureUtil;

@RestController
@Slf4j
public class BathroomController {
    private final AsyncBathroomArticleService asyncBathroomService;

    @Autowired
    public BathroomController(AsyncBathroomArticleService asyncBathroomService) {
        this.asyncBathroomService = asyncBathroomService;
    }

    @GetMapping(value = "/mobila/baie/{id}")
    public ResponseEntity<BathroomFurniture> getById(@PathVariable Long id){
        return CompletableFutureUtil.exceptionWrapper(asyncBathroomService.asyncGetById(id));
    }
}
















