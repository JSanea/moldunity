package web.app.moldunity.service.password;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web.app.moldunity.enums.ForgotPasswordStatus;
import web.app.moldunity.service.async.AsyncUserService;
import web.app.moldunity.service.email.EmailSenderService;
import web.app.moldunity.util.Expiry;
import web.app.moldunity.util.ExpiryMap;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class ForgotPasswordService {
    @Value("${email.username}")
    private String FROM;
    private final ExpiryMap<String, Integer> codes = new ExpiryMap<>();
    private final EmailSenderService emailSenderService;
    private final AsyncUserService asyncUserService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ForgotPasswordService(EmailSenderService emailSenderService, AsyncUserService asyncUserService, PasswordEncoder passwordEncoder) {
        this.emailSenderService = emailSenderService;
        this.asyncUserService = asyncUserService;
        this.passwordEncoder = passwordEncoder;
    }

    public CompletableFuture<ForgotPasswordStatus> sendCode(String email) {
            return asyncUserService.asyncExistEmail(email)
                    .thenCompose(exist -> {
                        if(!exist)
                            return CompletableFuture.completedFuture(ForgotPasswordStatus.INVALID_EMAIL);

                        Integer code = new Random().nextInt(900000) + 100000;
                        codes.remove(email);
                        codes.put(email, new Expiry<>(code, 5L));

                        return emailSenderService.asyncSend(
                                email,
                                FROM,
                                "Moldunity.md | Verification Code",
                                "Verification code: " + code
                        ).thenApply(success -> success
                                ? ForgotPasswordStatus.SUCCESS
                                : ForgotPasswordStatus.ERROR);
                    })
                    .exceptionally(ex -> {
                        log.error(ex.getMessage());
                        return ForgotPasswordStatus.ERROR;
                    });
    }

    public ForgotPasswordStatus updatePassword(String email, String password, Integer code){
        var c = codes.get(email);
        if (c.isPresent() && !code.equals(c.get()))
            return ForgotPasswordStatus.INVALID_CODE;

        codes.remove(email);

        try {
            asyncUserService.asyncUpdatePassword(email, passwordEncoder.encode(password));
            return ForgotPasswordStatus.SUCCESS;
        } catch (Exception e) {
            log.error(e.getMessage());
            return ForgotPasswordStatus.ERROR;
        }
    }
}

















