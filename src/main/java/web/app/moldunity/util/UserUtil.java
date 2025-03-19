package web.app.moldunity.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.app.moldunity.entity.user.User;
import web.app.moldunity.service.async.AsyncUserService;

import java.util.concurrent.ExecutionException;

@Component
@Slf4j
public class UserUtil {
    @Autowired
    private final AsyncUserService asyncUserService;

    public UserUtil(AsyncUserService asyncUserService) {
        this.asyncUserService = asyncUserService;
    }

    public User getUserByPrincipalName() {
        User empty = new User();

        String username = SecurityUtil.getUsername();
        if(null == username) return empty;

        User user;
        try {
            user = asyncUserService.asyncGetByName(username).get();
            if (null == user || null == user.getId()) return empty;
        } catch (InterruptedException | ExecutionException e) {
           log.error(e.getMessage());
           return empty;
        }

        return user;
    }
}
