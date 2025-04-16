package web.app.moldunity.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.app.moldunity.entity.mysql.user.User;
import web.app.moldunity.enums.ConfirmUserStatus;
import web.app.moldunity.service.async.AsyncUserService;
import web.app.moldunity.service.email.EmailConfirmationService;
import web.app.moldunity.util.CompletableFutureUtil;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@RestController
@Slf4j
public class UserController {
    private final AsyncUserService asyncUserService;
    private final EmailConfirmationService emailConfirmationService;

    @Autowired
    public UserController(AsyncUserService asyncUserService, EmailConfirmationService emailConfirmationService) {
        this.asyncUserService = asyncUserService;
        this.emailConfirmationService = emailConfirmationService;
    }

    @GetMapping(value = "/user/{username}")
    public ResponseEntity<User> getByUsername(@PathVariable String username){
        return CompletableFutureUtil.exceptionWrapper(asyncUserService.asyncGetByName(username));
    }

    @PostMapping(
            value = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public CompletableFuture<ResponseEntity<ConfirmUserStatus>> confirmUser(@RequestBody User user){
        return emailConfirmationService.sendEmail(user)
                .thenApply(i -> i ?  ResponseEntity.status(HttpStatus.OK).body(ConfirmUserStatus.SUCCESS):
                                     ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ConfirmUserStatus.ERROR));
    }

    @GetMapping(value = "/register")
    public ResponseEntity<Long> registerUser(@RequestParam String key){
        Optional<User> user = emailConfirmationService.getUser(key);
        try {
            return user.isPresent() ?
                    new ResponseEntity<>(asyncUserService.asyncAddUser(user.get()).get().getId(), HttpStatus.OK) :
                    new ResponseEntity<>(0L, HttpStatus.NOT_FOUND);
        } catch (ExecutionException | InterruptedException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(0L, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
