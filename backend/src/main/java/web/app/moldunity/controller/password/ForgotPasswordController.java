package web.app.moldunity.controller.password;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import web.app.moldunity.service.UserService;
import web.app.moldunity.service.password.ForgotPasswordRequest;
import web.app.moldunity.service.password.ForgotPasswordService;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/api")
public class ForgotPasswordController {
    private final ForgotPasswordService forgotPasswordService;
    private final UserService userService;

    @PostMapping(value = "/forgot-password", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<String>> sendVerificationCode(@Valid @RequestBody ForgotPasswordRequest request) {
        return forgotPasswordService.sendCode(request)
                .map(r -> r
                        ? ResponseEntity.ok("Verification code sent")
                        : ResponseEntity.internalServerError().body("Failed to send code")
                )
                .switchIfEmpty(Mono.just(ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send code")));
    }

    @PostMapping(value = "/reset-password", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<String>> resetPassword(@RequestBody Map<String, Integer> code){
        if (code == null || code.isEmpty()){
            return Mono.just(ResponseEntity.badRequest().body("Wrong request structure"));
        }

        var c = code.get("code");
        var x = forgotPasswordService.getCredentials(c);

        return Mono.justOrEmpty(x)
                .flatMap(u -> userService.resetPassword(u.email(), u.password()))
                .map(result -> {
                    if(result){
                        forgotPasswordService.remove(c);
                        return ResponseEntity.ok("Ok");
                    }
                    return ResponseEntity.internalServerError().body("Error");
                })
                .switchIfEmpty(Mono.just(ResponseEntity.badRequest().body("Code is expired or wrong")))
                .onErrorResume(e -> {
                    log.error("Error to reset password: {}", e.getMessage(), e);
                    return Mono.just(ResponseEntity.internalServerError().body("Error"));
                });
    }
}
