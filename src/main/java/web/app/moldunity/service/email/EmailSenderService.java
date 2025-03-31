package web.app.moldunity.service.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailSenderService{
    @Autowired
    private final JavaMailSender emailSender;

    public EmailSenderService(@Qualifier(value = "emailSender") JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public boolean send(String to, String from, String subject, String text){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);

        try {
            emailSender.send(simpleMailMessage);
            return true;
        } catch (MailException e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
