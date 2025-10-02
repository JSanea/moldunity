package web.app.moldunity.service.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@Slf4j
public class EmailService {
    private final JavaMailSender emailSender;

    public EmailService(JavaMailSender mailSender) {
        this.emailSender = mailSender;
    }

    public Mono<Boolean> sendEmail(String to, String from, String subject, String body) {
        return Mono.defer(() -> Mono.fromCallable(() -> {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setFrom(from);
            message.setSubject(subject);
            message.setText(body);
            try {
                emailSender.send(message);
                return true;
            } catch (Exception e) {
                log.error("Error to send email: {}, {}", to, e.getMessage());
                return false;
            }
        }).subscribeOn(Schedulers.boundedElastic()));
    }
}
