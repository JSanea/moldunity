package web.app.moldunity.controller.furniture;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import web.app.moldunity.entity.furniture.bathroom.BathroomFurniture;
import web.app.moldunity.entity.user.User;
import web.app.moldunity.service.async.AsyncUserService;
import web.app.moldunity.service.async.entity.AsyncEntityService;
import web.app.moldunity.util.CompletableFutureUtil;
import web.app.moldunity.util.DateTimeUtil;
import web.app.moldunity.util.UserUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@Slf4j
public class BathroomFurnitureController {
    private final AsyncEntityService<BathroomFurniture> asyncEntityService;
    private final AsyncUserService asyncUserService;

    @Autowired
    public BathroomFurnitureController(AsyncEntityService<BathroomFurniture> asyncEntityService, AsyncUserService asyncUserService) {
        this.asyncEntityService = asyncEntityService;
        this.asyncUserService = asyncUserService;
    }

    @GetMapping(value = "/furniture/bathroom/{id}")
    public ResponseEntity<BathroomFurniture> getById(@PathVariable Long id){
        return CompletableFutureUtil.exceptionWrapper(asyncEntityService.asyncGetById(id, BathroomFurniture.class));
    }

    @GetMapping(value = "/furniture/bathroom")
    public ResponseEntity<List<BathroomFurniture>> getPageSortedByTimeDesc(@RequestParam Integer page)  {
        return CompletableFutureUtil.exceptionWrapper(asyncEntityService.asyncGetPageSortedByRepublishedAtDesc(page, BathroomFurniture.class));
    }

    @GetMapping(value = "/furniture/bathroom/count")
    public ResponseEntity<Long> getNumRecords(){
        return CompletableFutureUtil.exceptionWrapper(asyncEntityService.asyncGetNumRecords(BathroomFurniture.class));
    }

    @PostMapping(value = "/furniture/bathroom",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> add(@RequestBody BathroomFurniture bathroomFurniture){
        try {
            User user = UserUtil.getUserByPrincipalName();

            if(null == user.getId()){
                log.error("User is not authenticated");
                return new ResponseEntity<>(0L, HttpStatus.UNAUTHORIZED);
            }

            bathroomFurniture.setDateTimeFields();
            bathroomFurniture.setUser(user);

            return new ResponseEntity<>(asyncEntityService.asyncAdd(bathroomFurniture, BathroomFurniture.class)
                    .get().getId(), HttpStatus.OK);

        } catch (InterruptedException | ExecutionException e) {
           log.error(e.getMessage());
           return new ResponseEntity<>(0L, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
















