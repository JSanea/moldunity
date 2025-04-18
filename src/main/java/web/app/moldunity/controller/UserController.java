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

import java.util.Optional;
import java.util.concurrent.CompletableFuture;


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
    public CompletableFuture<User> getByUsername(@PathVariable String username){
        return asyncUserService.asyncGetByName(username);
    }

    @PostMapping(
            value = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public CompletableFuture<ResponseEntity<ConfirmUserStatus>> confirmUser(@RequestBody User user){
        var existsNameFuture = asyncUserService.asyncExistUsername(user.getUsername());
        var existsEmailFuture =  asyncUserService.asyncExistEmail(user.getEmail());

        return existsNameFuture.thenCombine(existsEmailFuture, (usernameExists, emailExists) -> {
            if (usernameExists) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                        .body(ConfirmUserStatus.USERNAME_EXISTS);
            }

            if (emailExists) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                        .body(ConfirmUserStatus.EMAIL_EXISTS);
            }
            return null;
        }).thenCompose(result -> {
            if (result != null) {
                return CompletableFuture.completedFuture(result);
            }

            return emailConfirmationService.sendEmail(user)
                    .thenApply(success -> success
                            ? ResponseEntity.status(HttpStatus.OK).body(ConfirmUserStatus.SUCCESS)
                            : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ConfirmUserStatus.ERROR));
        });
    }

    @GetMapping(value = "/register")
    public CompletableFuture<User> registerUser(@RequestParam String key){
        Optional<User> user = emailConfirmationService.getUser(key);
        return user.map(asyncUserService::asyncAddUser).orElse(CompletableFuture.completedFuture(null));
    }
}




















