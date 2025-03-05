package web.app.moldunity.service.email;


import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import web.app.moldunity.entity.user.User;
import web.app.moldunity.util.Expiry;
import web.app.moldunity.util.ExpiryMap;
import web.app.moldunity.util.KeyGenerator;

import java.util.Optional;

@Service
public class EmailConfirmationService {
    private final Long TTL_MIN = 5L;
    @Value("${email.username}")
    private String FROM;
    private final String EMAIL_SUBJECT = "Moldunity.md | Email Confirmation";
    private final EmailSenderService emailService;
    private final ExpiryMap<String, User> expiryUsers = new ExpiryMap<>();

    @Autowired
    public EmailConfirmationService(EmailSenderService emailService) {
        this.emailService = emailService;
    }

    public boolean sendEmail(@NotNull User user){
        String key = KeyGenerator.generate();

        /** Check if already is sent and remove **/
        for(String k : expiryUsers.getKeySet()){
            if(user.getEmail().equals(expiryUsers.getItem(k).getEmail())){
                expiryUsers.remove(k);
                break;
            }
        }

        expiryUsers.put(key, new Expiry<>(user, TTL_MIN));

        return emailService.send(
                user.getEmail(),
                FROM,
                EMAIL_SUBJECT,
                "Pentru a confirma email-ul accesati link-ul:\nhttp://localhost:8080/register?key=" + key
        );
    }

    public Optional<User> getUser(@NotNull String key){
        User user = expiryUsers.getItem(key);
        expiryUsers.remove(key);
        return user != null ? Optional.of(user) : Optional.empty();
    }
}























