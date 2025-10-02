package web.app.moldunity.controller.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import web.app.moldunity.cookie.CookieHandler;
import web.app.moldunity.security.AuthRequest;
import web.app.moldunity.security.AuthResponse;
import web.app.moldunity.security.JwtTokenProvider;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/api")
public class AuthController {
    @Value("${access-token.max-age}")
    private Long accessTokenMaxAge;
    @Value("${refresh-token.max-age}")
    private Long refreshTokenMaxAge;
    private final CookieHandler cookieHandler;
    private final ReactiveUserDetailsService reactiveUserDetailsService;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @PostMapping(value = "/login")
    @Operation(summary = "Authenticate user and return JWT token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully authenticated",
                    content = @Content(schema = @Schema(implementation = AuthResponse.class))),
            @ApiResponse(responseCode = "401", description = "Invalid credentials"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Mono<ResponseEntity<AuthResponse>> login(@Valid @RequestBody AuthRequest authRequest){
        return reactiveUserDetailsService.findByUsername(authRequest.username())
                .filter(u -> passwordEncoder.matches(authRequest.password(), u.getPassword()))
                .map(userDetails -> {
                    String accessToken = jwtTokenProvider.generateToken(userDetails);
                    String refreshToken = jwtTokenProvider.generateRefreshToken(userDetails);

                    //ResponseCookie accessCookie  = cookieHandler.createCookie("access_token", accessToken, accessTokenMaxAge);
                    ResponseCookie refreshCookie = cookieHandler.createCookie("refresh_token", refreshToken, refreshTokenMaxAge);

                    return ResponseEntity.ok()
                            .header(HttpHeaders.SET_COOKIE, refreshCookie.toString())
                            //.header(HttpHeaders.SET_COOKIE, accessCookie.toString())
                            .body(new AuthResponse(accessToken));

                })
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()))
                .onErrorResume(e -> {
                    log.error("Failed to login: {}", e.getMessage(), e);
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });
    }

    @GetMapping(value = "/logout")
    public Mono<ResponseEntity<Void>> logout(ServerHttpResponse response){
        //ResponseCookie accessCookie   = cookieHandler.createCookie("access_token", "", 0L);
        ResponseCookie responseCookie = cookieHandler.createCookie("refresh_token", "", 0L);

        //response.addCookie(accessCookie);
        response.addCookie(responseCookie);

        return Mono.just(ResponseEntity.ok().build());
    }

    @Tag(name = "Authentication", description = "Endpoints for token management")
    @Operation(
            summary = "Refresh JWT access token",
            description = "Refreshes the JWT access token using a valid refresh token stored in cookies.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Access token successfully refreshed",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized - Refresh token missing or invalid"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error"
                    )
            }
    )
    @GetMapping(value = "/auth/refresh")
    public Mono<ResponseEntity<AuthResponse>> refreshToken(@CookieValue(name = "refresh_token", required = false) String refreshToken){
        if (refreshToken == null) return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());

        String username = jwtTokenProvider.extractUsername(refreshToken);

        return reactiveUserDetailsService.findByUsername(username)
                .filter(userDetails -> jwtTokenProvider.isTokenValid(refreshToken, userDetails))
                .map(jwtTokenProvider::generateToken)
                .map(accessToken -> {
                    //ResponseCookie accessCookie = cookieHandler.createCookie("access_token", accessToken, accessTokenMaxAge);
                    return ResponseEntity.ok()
                            //.header(HttpHeaders.SET_COOKIE, accessCookie.toString())
                            .body(new AuthResponse(accessToken));
                })
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()))
                .onErrorResume(e ->{
                    log.error(e.getMessage());
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });
    }

    @GetMapping("/auth/status")
    public Mono<ResponseEntity<Map<String, Object>>> checkAuthentication() {
        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .filter(auth -> !"anonymousUser".equals(auth.getPrincipal()))
                .map(auth -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("authenticated", true);
                    //response.put("username", auth.getName());
                    return ResponseEntity.ok(response);
                })
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("authenticated", false)));
    }

}
