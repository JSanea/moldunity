package web.app.moldunity.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.app.moldunity.entity.user.User;
import web.app.moldunity.service.async.AsyncUserService;
import web.app.moldunity.service.email.EmailConfirmationService;

import java.util.Optional;
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

    @PostMapping(
            value = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> confirmUser(@RequestBody User user){
        try {
            if(asyncUserService.asyncExistUsername(user.getUsername()).get())
                return new ResponseEntity<>("Username Already Exist", HttpStatus.NOT_ACCEPTABLE);
            if(asyncUserService.asyncExistEmail(user.getEmail()).get())
                return new ResponseEntity<>("Email Already Exist", HttpStatus.NOT_ACCEPTABLE);

        } catch (ExecutionException | InterruptedException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return emailConfirmationService.sendEmail(user) ?
                new ResponseEntity<>("OK", HttpStatus.OK):
                new ResponseEntity<>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
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
