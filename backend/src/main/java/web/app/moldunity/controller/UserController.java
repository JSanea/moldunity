package web.app.moldunity.controller;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import web.app.moldunity.model.dto.Profile;
import web.app.moldunity.service.UserService;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(value = "/api")
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/profile/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Profile>> getProfile(@PathVariable String username){
        log.info("Received request to fetch profile for username: {}", username);
        return userService.getProfileByName(username)
                .doOnSuccess(profile -> {
                    if (profile != null) {
                        log.info("Successfully fetched profile for username: {}", username);
                    }
                })
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.defer(() -> {
                    log.warn("Profile not found for username: {}", username);
                    return Mono.just(ResponseEntity.notFound().build());
                }))
                .onErrorResume(e -> {
                    log.error("Error fetching profile for username: {}: {}", username, e.getMessage(), e);
                    return Mono.just(ResponseEntity.internalServerError().build());
                });
    }
}







