package web.app.moldunity.service.email;


import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import web.app.moldunity.entity.mysql.user.User;
import web.app.moldunity.util.Expiry;
import web.app.moldunity.util.ExpiryMap;
import web.app.moldunity.util.KeyGenerator;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class EmailConfirmationService {
    @Value("${email.username}")
    private String FROM;
    private final EmailSenderService emailService;
    private final ExpiryMap<String, User> expiryUsers = new ExpiryMap<>();

    @Autowired
    public EmailConfirmationService(EmailSenderService emailService) {
        this.emailService = emailService;
    }

    public CompletableFuture<Boolean> sendEmail(@NotNull User user){
        String key = KeyGenerator.generate(32);

        /** Check if already is sent and remove **/
        for(String k : expiryUsers.getKeySet()){
            var v = expiryUsers.get(k);
            if(v.isPresent() && user.getEmail().equals(v.get().getEmail())){
                expiryUsers.remove(k);
                break;
            }
        }

        expiryUsers.put(key, new Expiry<>(user, 5L));

        return emailService.asyncSend(
                user.getEmail(),
                FROM,
                "Moldunity.md | Email Confirmation",
                "Pentru a confirma email-ul accesati link-ul:\nhttp://localhost:8080/register?key=" + key
        );
    }

    public Optional<User> getUser(@NotNull String key){
        var u = expiryUsers.get(key);
        expiryUsers.remove(key);
        return u;
    }
}























