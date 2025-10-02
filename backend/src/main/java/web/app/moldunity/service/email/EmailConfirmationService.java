package web.app.moldunity.service.email;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import web.app.moldunity.entity.postgres.user.User;
import web.app.moldunity.util.Expiry;
import web.app.moldunity.util.ExpiryMap;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailConfirmationService {
    @Value("${email.username}")
    private String FROM;
    private final EmailService emailService;
    private final ExpiryMap<String, User> expiryUsers = new ExpiryMap<>();

    public Mono<Boolean> sendEmail(@NotNull User user) {
        return Mono.defer(() -> {
            String key = UUID.randomUUID().toString();

            for (String k : expiryUsers.getKeySet()) {
                var v = expiryUsers.get(k);
                if (v.isPresent() && user.getEmail().equals(v.get().getEmail())) {
                    expiryUsers.remove(k);
                    break;
                }
            }

            expiryUsers.put(key, new Expiry<>(user, 5L));

            return emailService.sendEmail(
                    user.getEmail(),
                    FROM,
                    "Moldunity.md | Email Confirmation",
                    "Pentru a confirma email-ul accesati link-ul:\nhttp://localhost:8080/register?key=" + key
            );
        })
        .onErrorResume(e -> {
            log.error("Failed to send email: {}, {}", user.getEmail(), e.getMessage());
            return Mono.error(new RuntimeException("Failed to send email: " + user.getEmail()));
        });
    }

    public Optional<User> getUser(String key){
        var u = expiryUsers.get(key);
        expiryUsers.remove(key);
        return u;
    }
}












