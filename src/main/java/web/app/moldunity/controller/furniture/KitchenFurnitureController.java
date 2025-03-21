package web.app.moldunity.controller.furniture;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.app.moldunity.entity.furniture.Furniture;
import web.app.moldunity.entity.furniture.bathroom.BathroomFurniture;
import web.app.moldunity.entity.furniture.kitchen.KitchenFurniture;
import web.app.moldunity.entity.user.User;
import web.app.moldunity.service.async.AsyncUserService;
import web.app.moldunity.service.async.entity.AsyncEntityService;
import web.app.moldunity.util.CompletableFutureUtil;
import web.app.moldunity.util.SecurityUtil;

import java.util.List;
import java.util.concurrent.ExecutionException;


@RestController
@Slf4j
public class KitchenFurnitureController {
    private final AsyncEntityService asyncEntityService;
    private final AsyncUserService asyncUserService;

    @Autowired
    public KitchenFurnitureController(AsyncEntityService asyncEntityService, AsyncUserService asyncUserService) {
        this.asyncEntityService = asyncEntityService;
        this.asyncUserService = asyncUserService;
    }

    @GetMapping(value = "/furniture/kitchen/{id}")
    public ResponseEntity<Furniture> getById(@PathVariable Long id){
        return CompletableFutureUtil.exceptionWrapper(asyncEntityService.asyncGetById(id, Furniture.class));
    }

    @PostMapping(value = "/kitchen",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> add(@RequestBody Furniture furniture){
        try {
            //**** get username form security context ****
            String username = SecurityUtil.getUsername();
            if (null == username) return new ResponseEntity<>(0L, HttpStatus.UNAUTHORIZED);

            //**** set user for furniture entity ****
            User u = new User();
            u.setId(asyncUserService.asyncGetIdByUsername(username).get());

            //**** add furniture entity ****
            KitchenFurniture k = furniture.getKitchenFurnitures().get(0);
            furniture.setUser(u);
            k.setFurniture(furniture);
            k.setDateTimeFields();
            furniture.setKitchenFurnitures(List.of(k));

            Furniture f = asyncEntityService.asyncAdd(furniture, Furniture.class).get();
            if (null == f || null == f.getId()) return new ResponseEntity<>(0L, HttpStatus.INTERNAL_SERVER_ERROR);

            return new ResponseEntity<>(f.getId(), HttpStatus.OK);
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(0L, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


















