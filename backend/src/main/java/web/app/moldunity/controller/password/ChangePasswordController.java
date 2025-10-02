package web.app.moldunity.controller.password;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import web.app.moldunity.cookie.CookieHandler;
import web.app.moldunity.enums.ChangePasswordStatus;
import web.app.moldunity.service.UserService;
import web.app.moldunity.service.password.ChangePasswordRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/api")
public class ChangePasswordController {
    private final UserService userService;
    private final CookieHandler cookieHandler;

    @PutMapping(value = "/change-password")
    public Mono<ResponseEntity<ChangePasswordStatus>> changePassword(@Valid @RequestBody ChangePasswordRequest request,
                                                                     ServerHttpResponse response){
        return ReactiveSecurityContextHolder.getContext()
                .map(ctx -> (String)ctx.getAuthentication().getPrincipal())
                .flatMap(username -> userService.changePassword(username, request.current(), request.fresh())
                        .map(status -> {
                            if(status == ChangePasswordStatus.SUCCESS){
                                //ResponseCookie accessCookie   = cookieHandler.createCookie("access_token", "", 0L);
                                ResponseCookie responseCookie = cookieHandler.createCookie("refresh_token", "", 0L);

                                //response.addCookie(accessCookie);
                                response.addCookie(responseCookie);
                            }
                            return status;
                        }))
                .map(ResponseEntity::ok)
                .onErrorResume(e -> {
                    log.error("Error to change password: {}", e.getMessage(), e);
                    return Mono.just(ResponseEntity.internalServerError().build());
                });
    }
}


























