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
import web.app.moldunity.util.CompletableFutureUtil;
import web.app.moldunity.util.SecurityUtil;
import web.app.moldunity.util.UserUtil;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@Slf4j
public class BathroomFurnitureController {
    private final AsyncEntityService asyncEntityService;
    private final AsyncUserService asyncUserService;
    private final UserUtil userUtil;

    @Autowired
    public BathroomFurnitureController(AsyncEntityService asyncEntityService, AsyncUserService asyncUserService, UserUtil userUtil) {
        this.asyncEntityService = asyncEntityService;
        this.asyncUserService = asyncUserService;
        this.userUtil = userUtil;
    }

    @GetMapping(value = "/furniture/bathroom/{id}")
    public ResponseEntity<Furniture> getById(@PathVariable Long id){
        return CompletableFutureUtil.exceptionWrapper(asyncEntityService.asyncGetById(id, Furniture.class));
    }

//    @GetMapping(value = "/furniture/bathroom")
//    public ResponseEntity<List<BathroomFurniture>> getPageSortedByTimeDesc(@RequestParam Integer page)  {
//        return CompletableFutureUtil.exceptionWrapper(asyncEntityService.asyncGetPageSortedByRepublishedAtDesc(page, BathroomFurniture.class));
//    }

//    @GetMapping(value = "/furniture/bathroom/count")
//    public ResponseEntity<Long> getNumRecords(){
//        return CompletableFutureUtil.exceptionWrapper(asyncEntityService.asyncGetNumRecords(BathroomFurniture.class));
//    }

    @PostMapping(value = "/bathroom",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> add(@RequestBody Furniture furniture) throws ExecutionException, InterruptedException {
        //User user = userUtil.getUserByPrincipalName();

//        if(null == user.getId()){
//            log.error("User is not authenticated");
//            return new ResponseEntity<>(0L, HttpStatus.UNAUTHORIZED);
//        }

        String username = SecurityUtil.getUsername();
        Long uid = asyncUserService.asyncGetIdByUsername(username).get();

        User u = new User();
        u.setId(uid);
        furniture.setUser(u);

        Furniture f = asyncEntityService.asyncAdd(furniture, Furniture.class).get();
        if (null == f || null == f.getId()) return new ResponseEntity<>(0L, HttpStatus.INTERNAL_SERVER_ERROR);

        for(BathroomFurniture b : f.getBathroomFurnitures()){
            b.setFurniture(f);
            b.setDateTimeFields();
            BathroomFurniture b2 = asyncEntityService.asyncAdd(b, BathroomFurniture.class).get();

            if (null == b2 || null == b2.getId()) return new ResponseEntity<>(0L, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(f.getId(), HttpStatus.OK);
        // 1. furniture - set user - ret  furniture
        // 2. bathroom - set furniture - ret bathroom
        // return furniture id

        //bathroomFurniture.setDateTimeFields();
        //bathroomFurniture.setUser(user);

        //return CompletableFutureUtil.exceptionWrapper(asyncEntityService.asyncAdd(bathroomFurniture, BathroomFurniture.class));
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
    public void update(@RequestBody BathroomFurniture bathroomFurniture){

    }

    @DeleteMapping(value = "/bathroom/{id}")
    public ResponseEntity<Boolean> remove(@PathVariable Long id){
        asyncEntityService.removeById(id, BathroomFurniture.class);
        return CompletableFutureUtil.removeExceptionWrapper(asyncEntityService.asyncGetById(id, BathroomFurniture.class));
    }
}
















