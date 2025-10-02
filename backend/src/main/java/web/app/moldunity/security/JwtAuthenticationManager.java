package web.app.moldunity.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import web.app.moldunity.exception.JwtAuthenticationException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationManager implements ReactiveAuthenticationManager {
    private final UserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String token = authentication.getCredentials().toString();

        if(token == null) {
            return Mono.error(new JwtAuthenticationException("Token is missing"));
        }

        String username = jwtTokenProvider.extractUsername(token);

        return userDetailsService.findByUsername(username)
                .filter(userDetails -> jwtTokenProvider.isTokenValid(token, userDetails))
                .map(userDetails -> (Authentication)new JwtAuthenticationToken(token, userDetails))
                .switchIfEmpty(Mono.error(new JwtAuthenticationException("Invalid token or token expired")));
    }
}
