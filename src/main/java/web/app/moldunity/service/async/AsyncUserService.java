package web.app.moldunity.service.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import web.app.moldunity.entity.user.User;
import web.app.moldunity.service.UserService;

import java.util.concurrent.CompletableFuture;

@Service
public class AsyncUserService {
    private final UserService userService;

    @Autowired
    public AsyncUserService(UserService userService) {
        this.userService = userService;
    }

    @Async
    public CompletableFuture<User> asyncAddUser(User user){
        return CompletableFuture.completedFuture(userService.addUser(user));
    }

    @Async
    public CompletableFuture<Boolean> existUsername(String username){
        return CompletableFuture.completedFuture(userService.existUsername(username));
    }

    @Async
    public CompletableFuture<Boolean> existEmail(String email){
        return CompletableFuture.completedFuture(userService.existEmail(email));
    }
}
