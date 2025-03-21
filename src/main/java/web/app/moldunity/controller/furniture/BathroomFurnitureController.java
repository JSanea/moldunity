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

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@Slf4j
public class BathroomFurnitureController {
    private final AsyncEntityService asyncEntityService;
    private final AsyncUserService asyncUserService;

    @Autowired
    public BathroomFurnitureController(AsyncEntityService asyncEntityService, AsyncUserService asyncUserService) {
        this.asyncEntityService = asyncEntityService;
        this.asyncUserService = asyncUserService;
    }

    @GetMapping(value = "/furniture/bathroom/{id}")
    public ResponseEntity<Furniture> getById(@PathVariable Long id){
        return CompletableFutureUtil.exceptionWrapper(asyncEntityService.asyncGetById(id, Furniture.class));
    }

    @GetMapping(value = "/furniture/bathroom")
    public ResponseEntity<List<Furniture>> getPageSortedByTimeDesc(@RequestParam Integer page)  {
        return CompletableFutureUtil.exceptionWrapper(asyncEntityService.asyncGetPageSortedByRepublishedAtDesc(page, Furniture.class, "bathroomFurnitures"));
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
            //**** get username form security context ****
            String username = SecurityUtil.getUsername();
            if (null == username) return new ResponseEntity<>(0L, HttpStatus.UNAUTHORIZED);

            //**** set user for furniture entity ****
            User u = new User();
            u.setId(asyncUserService.asyncGetIdByUsername(username).get());

            //**** add furniture entity ****
            BathroomFurniture b = furniture.getBathroomFurnitures().get(0);
            furniture.setUser(u);
            b.setFurniture(furniture);
            b.setDateTimeFields();
            furniture.setBathroomFurnitures(List.of(b));

            Furniture f = asyncEntityService.asyncAdd(furniture, Furniture.class).get();
            if (null == f || null == f.getId()) return new ResponseEntity<>(0L, HttpStatus.INTERNAL_SERVER_ERROR);

            return new ResponseEntity<>(f.getId(), HttpStatus.OK);
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(0L, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
















