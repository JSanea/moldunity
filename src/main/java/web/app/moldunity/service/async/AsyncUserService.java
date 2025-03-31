package web.app.moldunity.service.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import web.app.moldunity.entity.user.User;
import web.app.moldunity.service.user.UserService;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
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
    public CompletableFuture<User> asyncGetById(Long id){
        return CompletableFuture.completedFuture(userService.findById(id).orElseGet(User::new));
    }

    @Async
    public CompletableFuture<User> asyncGetByName(String username){
        return CompletableFuture.completedFuture(userService.getByName(username));
    }

    @Async
    public CompletableFuture<String[]> asyncGetPasswordAndRoleByUsername(String username){
        return CompletableFuture.completedFuture(userService.getPasswordAndRoleByUsername(username));
    }

    @Async
    public CompletableFuture<Boolean> asyncExistUsername(String username){
        return CompletableFuture.completedFuture(userService.existUsername(username));
    }

    @Async
    public CompletableFuture<Boolean> asyncExistEmail(String email){
        return CompletableFuture.completedFuture(userService.existEmail(email));
    }

    @Async
    public CompletableFuture<Long> asyncGetIdByUsername(String username){
        return CompletableFuture.completedFuture(userService.getIdByUsername(username));
    }

    @Async
    public void asyncUpdatePassword(String email, String password){
        userService.updatePassword(email, password);
    }
}
