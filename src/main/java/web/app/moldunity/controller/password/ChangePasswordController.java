package web.app.moldunity.controller.password;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import web.app.moldunity.enums.ChangePasswordStatus;
import web.app.moldunity.service.async.AsyncUserService;
import web.app.moldunity.security.SecurityContextHelper;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@Slf4j
public class ChangePasswordController {
    private final AsyncUserService asyncUserService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ChangePasswordController(AsyncUserService asyncUserService, PasswordEncoder passwordEncoder) {
        this.asyncUserService = asyncUserService;
        this.passwordEncoder = passwordEncoder;
    }

    @PutMapping(value = "/change-password")
    public ChangePasswordStatus changePassword(@RequestBody Map<String, String> passwords){
        try {
            String username = SecurityContextHelper.getUsername();
            String password = asyncUserService.asyncGetPasswordByUsername(username).get();
            if(!passwordEncoder.matches(passwords.get("current"), password))
                return ChangePasswordStatus.INVALID_CURRENT_PASSWORD;

            asyncUserService.asyncChangePassword(username, passwordEncoder.encode(passwords.get("new")));
            return ChangePasswordStatus.SUCCESS;
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage());
            return ChangePasswordStatus.ERROR;
        }
    }
}
