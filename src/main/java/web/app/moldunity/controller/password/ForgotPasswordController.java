package web.app.moldunity.controller.password;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.app.moldunity.enums.ForgotPasswordStatus;
import web.app.moldunity.service.password.ForgotPasswordService;

import java.util.Map;

@RestController
public class ForgotPasswordController {
    private final ForgotPasswordService forgotPasswordService;

    @Autowired
    public ForgotPasswordController(ForgotPasswordService forgotPasswordService) {
        this.forgotPasswordService = forgotPasswordService;
    }

    @GetMapping(value = "/verification-code")
    public ForgotPasswordStatus getVerificationCode(@RequestParam("email") String email){
        return forgotPasswordService.sendCode(email);
    }

    @PutMapping(value = "/update-password")
    public ForgotPasswordStatus updatePassword(@RequestBody Map<String, String> credentials){
        return forgotPasswordService.updatePassword(
                credentials.get("email"),
                credentials.get("password"),
                Integer.valueOf(credentials.get("code"))
        );
    }
}
