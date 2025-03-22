package web.app.moldunity.service.entity.furniture;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.app.moldunity.entity.furniture.Furniture;
import web.app.moldunity.entity.user.User;
import web.app.moldunity.service.async.AsyncUserService;
import web.app.moldunity.service.async.entity.AsyncEntityService;
import web.app.moldunity.util.SecurityUtil;

import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class FurnitureService {
    private final AsyncEntityService asyncEntityService;
    private final AsyncUserService asyncUserService;

    @Autowired
    public FurnitureService(AsyncEntityService asyncEntityService, AsyncUserService asyncUserService) {
        this.asyncEntityService = asyncEntityService;
        this.asyncUserService = asyncUserService;
    }

    @Transactional
    public ResponseEntity<Long> add(Furniture furniture){
        try {
            //**** get username form security context ****
            String username = SecurityUtil.getUsername();
            if (null == username) return new ResponseEntity<>(0L, HttpStatus.UNAUTHORIZED);

            //**** set user for furniture entity ****
            User u = new User();
            u.setId(asyncUserService.asyncGetIdByUsername(username).get());
            furniture.setUser(u);
            furniture.setUsername(username);
            furniture.setDateTimeFields();

            Furniture f = asyncEntityService.asyncAdd(furniture, Furniture.class).get();
            if (null == f || null == f.getId()) return new ResponseEntity<>(0L, HttpStatus.INTERNAL_SERVER_ERROR);

            return new ResponseEntity<>(f.getId(), HttpStatus.OK);
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(0L, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
