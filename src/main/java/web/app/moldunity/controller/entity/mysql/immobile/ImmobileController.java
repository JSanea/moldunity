package web.app.moldunity.controller.entity.mysql.immobile;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import web.app.moldunity.entity.mysql.immobile.Immobile;
import web.app.moldunity.service.async.AsyncUserService;
import web.app.moldunity.service.async.entity.AsyncEntityService;
import web.app.moldunity.util.CompletableFutureUtil;
import web.app.moldunity.security.SecurityContextHelper;

import java.util.List;

@RestController
@Slf4j
public class ImmobileController {
    private final AsyncEntityService asyncEntityService;
    private final AsyncUserService asyncUserService;

    @Autowired
    public ImmobileController(AsyncEntityService asyncEntityService, AsyncUserService asyncUserService) {
        this.asyncEntityService = asyncEntityService;
        this.asyncUserService = asyncUserService;
    }

    @GetMapping(value = "/immobile/{id}")
    public ResponseEntity<Immobile> getById(@PathVariable Long id){
        return CompletableFutureUtil.exceptionWrapper(asyncEntityService.asyncGetById(id, Immobile.class));
    }

    @GetMapping(value = "/immobile/count")
    public ResponseEntity<Long> getNumRecords(){
        return CompletableFutureUtil.exceptionWrapper(asyncEntityService.asyncGetNumRecords(Immobile.class));
    }

    @GetMapping(value = "/favorite/immobile/")
    public ResponseEntity<List<Immobile>> getFavorite(){
        return CompletableFutureUtil.exceptionWrapper(asyncEntityService.asyncGetFavorite(SecurityContextHelper.getUsername(), Immobile.class, "favoriteImmobiles"));
    }
}
