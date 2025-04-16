package web.app.moldunity.service.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class EmailSenderService{
    @Autowired
    private final JavaMailSender emailSender;

    public EmailSenderService(@Qualifier(value = "emailSender") JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Async
    public CompletableFuture<Boolean> asyncSend(String to, String from, String subject, String text){
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(to);
            simpleMailMessage.setFrom(from);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(text);

            emailSender.send(simpleMailMessage);
            return CompletableFuture.completedFuture(true);
        } catch (MailException e) {
            log.error(e.getMessage());
            return CompletableFuture.completedFuture(false);
        }
    }
}
