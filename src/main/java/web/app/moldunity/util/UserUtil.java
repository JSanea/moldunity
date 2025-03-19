package web.app.moldunity.util;

import org.springframework.beans.factory.annotation.Autowired;
import web.app.moldunity.entity.user.User;
import web.app.moldunity.service.async.AsyncUserService;

import java.util.concurrent.ExecutionException;


public class UserUtil {
    @Autowired
    private static AsyncUserService asyncUserService;

    public static User getUserByPrincipalName() throws ExecutionException, InterruptedException {
        User empty = new User();

        String username = SecurityUtil.getUsername();
        if(null == username) return empty;

        User user = asyncUserService.asyncGetByName(username).get();
        if (null == user || null == user.getId()) return empty;

        return user;
    }
}
