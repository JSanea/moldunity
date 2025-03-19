package web.app.moldunity.controller.furniture;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.app.moldunity.entity.furniture.bathroom.BathroomFurniture;
import web.app.moldunity.entity.furniture.bathroom.BathroomFurnitureImage;
import web.app.moldunity.entity.user.User;
import web.app.moldunity.service.async.AsyncUserService;
import web.app.moldunity.service.async.entity.AsyncEntityService;
import web.app.moldunity.util.CompletableFutureUtil;
import web.app.moldunity.util.UserUtil;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class BathroomFurnitureController {
    private final AsyncEntityService<BathroomFurniture> asyncEntityService;
    private final UserUtil userUtil;

    @Autowired
    public BathroomFurnitureController(AsyncEntityService<BathroomFurniture> asyncEntityService, UserUtil userUtil) {
        this.asyncEntityService = asyncEntityService;
        this.userUtil = userUtil;
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

    @PostMapping(value = "/bathroom",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BathroomFurniture> add(@RequestBody BathroomFurniture bathroomFurniture){
        User user = userUtil.getUserByPrincipalName();

        if(null == user.getId()){
            log.error("User is not authenticated");
            return new ResponseEntity<>(new BathroomFurniture(), HttpStatus.UNAUTHORIZED);
        }

        bathroomFurniture.setDateTimeFields();
        bathroomFurniture.setUser(user);

        return CompletableFutureUtil.exceptionWrapper(asyncEntityService.asyncAdd(bathroomFurniture, BathroomFurniture.class));
    }

    @PostMapping(value = "/images/bathroom/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void addImages(@PathVariable Long id,
                          @RequestBody Map<String, BathroomFurnitureImage> images) {

    }

    @PutMapping(value = "/bathroom",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody BathroomFurniture bathroomFurniture){

    }

    @DeleteMapping(value = "/bathroom/{id}")
    public ResponseEntity<Boolean> remove(@PathVariable Long id){
        asyncEntityService.removeById(id, BathroomFurniture.class);
        return CompletableFutureUtil.removeExceptionWrapper(asyncEntityService.asyncGetById(id, BathroomFurniture.class));
    }
}
















