package web.app.moldunity.controller.auth;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import web.app.moldunity.model.entity.postgres.user.User;
import web.app.moldunity.service.UserService;
import web.app.moldunity.service.email.EmailConfirmationService;

import java.util.Set;


@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(value = "/api")
public class SignUpController {
    private final EmailConfirmationService emailConfirmationService;
    private final UserService userService;
    private static final Set<String> RESERVED_USERNAMES = Set.of(
            "anonymoususer", "admin", "moldunity"
    );

    @PostMapping(value = "/register",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<String>> confirmUserEmail(@Valid @RequestBody User user){
        String normalizedUsername = user.getUsername().toLowerCase();

        if (RESERVED_USERNAMES.contains(normalizedUsername)) {
            return Mono.just(ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body("The username '" + user.getUsername() + "' is reserved and cannot be used."));
        }

        String name = user.getUsername();
        String mail = user.getEmail();
        user.setRole("USER");

        return userService.findByUsernameOrEmail(name, mail)
                .map(u -> {
                    if(name.equals(u.getUsername()))
                        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Username already exists");

                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Email already exists");
                })
                .switchIfEmpty(emailConfirmationService.sendEmail(user)
                                .map(success -> success
                                ? ResponseEntity.ok("Email sent successfully")
                                : ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                                .body("Failed to send email. Please try again later"))
                ).onErrorResume(e -> {
                    log.error("Send email confirmation error: {}", e.getMessage(), e);
                    return Mono.just(ResponseEntity.internalServerError().build());
                });
    }

    @GetMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<User>> register(@RequestParam String key){
        return Mono.justOrEmpty(emailConfirmationService.getUser(key))
                .flatMap(user -> {
                    user.setDateTimeFields();
                    return userService.save(user)
                            .map(ResponseEntity::ok);
                })
                .switchIfEmpty(Mono.just(ResponseEntity.badRequest().build()))
                .onErrorResume(e -> {
                    log.error("Registration error: {}", e.getMessage(), e);
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });
    }
}
