package web.app.moldunity.controller.entity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import web.app.moldunity.entity.furniture.bathroom.BathroomArticle;
import web.app.moldunity.service.async.entity.AsyncBathroomService;

import java.util.concurrent.ExecutionException;

@RestController
@Slf4j
public class BathroomController {
    private final AsyncBathroomService asyncBathroomService;

    @Autowired
    public BathroomController(AsyncBathroomService asyncBathroomService) {
        this.asyncBathroomService = asyncBathroomService;
    }

    @GetMapping(value = "/mobila/baie/{id}")
    public ResponseEntity<BathroomArticle> getById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(asyncBathroomService.asyncGetById(id).get(), HttpStatus.OK);
        } catch (ExecutionException | InterruptedException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(new BathroomArticle(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
















