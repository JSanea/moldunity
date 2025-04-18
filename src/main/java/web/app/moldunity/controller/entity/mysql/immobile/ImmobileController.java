package web.app.moldunity.controller.entity.mysql.immobile;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import web.app.moldunity.entity.mysql.immobile.Immobile;
import web.app.moldunity.security.SecurityContextHelper;
import web.app.moldunity.service.async.AsyncUserService;
import web.app.moldunity.service.async.entity.AsyncEntityService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@Slf4j
public class ImmobileController {
    private final AsyncEntityService<Long> asyncEntityService;
    private final AsyncUserService asyncUserService;

    @Autowired
    public ImmobileController(AsyncEntityService<Long> asyncEntityService, AsyncUserService asyncUserService) {
        this.asyncEntityService = asyncEntityService;
        this.asyncUserService = asyncUserService;
    }

    @GetMapping(value = "/immobile/{id}")
    public CompletableFuture<ResponseEntity<Immobile>> getById(@PathVariable Long id){
        return asyncEntityService.asyncGetById(id, Immobile.class)
                .thenApply(m -> m.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()));
    }

    @GetMapping(value = "/immobile/count")
    public CompletableFuture<ResponseEntity<Long>> getNumRecords(){
        return asyncEntityService.asyncGetNumRecords(Immobile.class)
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping(value = "/favorite/immobile/")
    public CompletableFuture<ResponseEntity<List<Immobile>>> getFavorite(){
        return asyncEntityService.asyncGetFavorite(
                SecurityContextHelper.getUsername(),
                Immobile.class,
                "favoriteImmobiles").thenApply(ResponseEntity::ok);
    }
}





















