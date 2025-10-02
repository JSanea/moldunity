package web.app.moldunity.service.password;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import web.app.moldunity.service.UserService;
import web.app.moldunity.service.email.EmailService;
import web.app.moldunity.util.Expiry;
import web.app.moldunity.util.ExpiryMap;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class ForgotPasswordService {
    @Value("${email.username}")
    private String FROM;
    private final EmailService emailService;
    private final UserService userService;
    private final ExpiryMap<Integer, ForgotPasswordRequest> expiryCodes = new ExpiryMap<>();


    public Mono<Boolean> sendCode(ForgotPasswordRequest request){
        return userService.existsEmail(request.email())
                .flatMap(exists -> {
                    if (!exists) {
                        return Mono.just(false);
                    }

                    Integer code = new Random().nextInt(900000) + 100000;
                    expiryCodes.put(code, new Expiry<>(request, 5L));

                    return emailService.sendEmail(
                            request.email(),
                            FROM,
                            "Moldunity.md | Verification Code",
                            "Verification code: " + code);
                })
                .onErrorResume(e -> {
                    log.error("Failed to send email: {}, {}", request.email(), e.getMessage());
                    return Mono.error(new RuntimeException("Failed to send email: " + request.email()));
                });
    }

    public Optional<ForgotPasswordRequest> getCredentials(Integer code){
        return expiryCodes.get(code);
    }

    public void remove(Integer code){
        expiryCodes.remove(code);
    }
}






