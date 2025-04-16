package web.app.moldunity.service.entity.furniture;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import web.app.moldunity.entity.mysql.furniture.Furniture;
import web.app.moldunity.entity.mysql.user.User;
import web.app.moldunity.security.SecurityContextHelper;
import web.app.moldunity.service.async.AsyncUserService;
import web.app.moldunity.service.async.entity.AsyncEntityService;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class FurnitureService {
    private final AsyncEntityService<Long> asyncEntityService;
    private final AsyncUserService asyncUserService;

    @Value("${articles.limit}")
    Long limit;

    @Autowired
    public FurnitureService(AsyncEntityService<Long> asyncEntityService,AsyncUserService asyncUserService) {
        this.asyncEntityService = asyncEntityService;
        this.asyncUserService = asyncUserService;
    }

    public CompletableFuture<Furniture> add(Furniture furniture){
        //**** get username from security context ****
        String username = SecurityContextHelper.getUsername();
        if (null == username || username.equals("anonymousUser")) return CompletableFuture.completedFuture(new Furniture());

        return asyncUserService.asyncGetIdByUsername(username)
                .thenCompose(id ->{
                    String eId = UUID.randomUUID().toString();
                    User u = new User();
                    u.setId(id);
                    furniture.setUser(u);
                    furniture.setEId(eId);
                    furniture.setUsername(username);
                    furniture.setDateTimeFields();
                    return asyncEntityService.asyncAdd(furniture, furniture.getEId(), username, limit, Furniture.class);
                });
    }
}




