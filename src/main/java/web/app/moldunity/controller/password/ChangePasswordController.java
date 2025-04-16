package web.app.moldunity.controller.password;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import web.app.moldunity.enums.ChangePasswordStatus;
import web.app.moldunity.security.SecurityContextHelper;
import web.app.moldunity.service.async.AsyncUserService;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

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
    public CompletableFuture<ResponseEntity<ChangePasswordStatus>> changePassword(@RequestBody Map<String, String> passwords){
        return asyncUserService.asyncChangePassword(
                SecurityContextHelper.getUsername(),
                passwords.get("current"),
                passwords.get("new")
        ).thenApply(ResponseEntity::ok);
    }
}
